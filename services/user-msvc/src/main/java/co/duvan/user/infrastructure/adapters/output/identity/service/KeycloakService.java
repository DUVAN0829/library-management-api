package co.duvan.user.infrastructure.adapters.output.identity.service;

import co.duvan.user.domain.model.User;
import co.duvan.user.infrastructure.adapters.output.identity.config.KeycloakProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.core.ParameterizedTypeReference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KeycloakService {

    private final WebClient webClient;
    private final KeycloakProperties properties;

    public String getAdminToken() {

        return webClient.post()
                .uri(properties.getServerUrl() +
                        "/realms/" + properties.getRealm() +
                        "/protocol/openid-connect/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData("grant_type", "client_credentials")
                        .with("client_id", properties.getClientId())
                        .with("client_secret", properties.getClientSecret()))
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> response.get("access_token").toString())
                .block();
    }

    public String createUser(User user, String password) {

        String token = getAdminToken();

        return webClient.post()
                .uri(properties.getServerUrl() +
                        "/admin/realms/" + properties.getRealm() +
                        "/users")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(buildPayload(user, password))
                .exchangeToMono(response -> {

                    if (response.statusCode().is2xxSuccessful()) {

                        String location = response.headers()
                                .asHttpHeaders()
                                .getFirst(HttpHeaders.LOCATION);

                        assert location != null;

                        String userId = location.substring(location.lastIndexOf("/") + 1);

                        assignRealmRoleToUser(userId, token);

                        return Mono.just(userId);
                    }

                    return response.createException().flatMap(Mono::error);
                })
                .block();
    }

    private Map<String, Object> buildPayload(User user, String password) {

        Map<String, Object> payload = new HashMap<>();
        payload.put("username", user.getFirstname());
        payload.put("email", user.getEmail());
        payload.put("firstName", user.getFirstname());
        payload.put("lastName", user.getLastname());
        payload.put("enabled", true);

        Map<String, Object> credential = new HashMap<>();
        credential.put("type", "password");
        credential.put("value", password);
        credential.put("temporary", false);

        payload.put("credentials", List.of(credential));

        return payload;
    }

    private Map<String, Object> getRealmRole(String token) {

        return webClient.get()
                .uri(properties.getServerUrl() +
                        "/admin/realms/" + properties.getRealm() +
                        "/roles/" + "MEMBER")
                .headers(headers -> headers.setBearerAuth(token))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                })
                .block();
    }

    private void assignRealmRoleToUser(String userId, String token) {

        Map<String, Object> role = getRealmRole(token);

        webClient.post()
                .uri(properties.getServerUrl() +
                        "/admin/realms/" + properties.getRealm() +
                        "/users/" + userId +
                        "/role-mappings/realm")
                .headers(headers -> headers.setBearerAuth(token))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(List.of(role))
                .retrieve()
                .toBodilessEntity()
                .block();
    }

}