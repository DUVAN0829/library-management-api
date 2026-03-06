package co.duvan.book.infrastructure.adapters.input.rest.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        security = @SecurityRequirement(name = "oauth2"),
        info = @Info(
                title = "Book Management API",
                version = "1.0.0",
                description = """
                        REST adapter responsible for core book management operations.

                        This interface handles create, update, delete, and retrieve-by-id actions,
                        while advanced querying capabilities are provided through a separate
                        GraphQL adapter.

                        The application is built following Hexagonal Architecture (Ports & Adapters),
                        promoting a clear separation between domain, application, and infrastructure layers.
                        """
        )
)
@SecurityScheme(
        name = "oauth2",
        type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(
                authorizationCode = @OAuthFlow(
                        authorizationUrl = "http://localhost:8080/realms/library-management-api/protocol/openid-connect/auth",
                        tokenUrl = "http://localhost:8080/realms/library-management-api/protocol/openid-connect/token"
                )
        )
)
public class OpenApiConfig {
}