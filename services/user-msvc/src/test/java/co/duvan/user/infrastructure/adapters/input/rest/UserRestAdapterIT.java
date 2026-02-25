package co.duvan.user.infrastructure.adapters.input.rest;

import co.duvan.user.domain.enums.DocumentType;
import co.duvan.user.domain.enums.Gender;
import co.duvan.user.infrastructure.adapters.input.rest.model.request.UserRequest;
import co.duvan.user.infrastructure.adapters.output.identity.service.KeycloakService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.UUID;


import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
public class UserRestAdapterIT {

    private static final String BASE_URL = "/users/api/v1";

    private static final SimpleGrantedAuthority ADMIN =
            new SimpleGrantedAuthority("ROLE_ADMIN");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private KeycloakService keycloakService;

    @Container
    static MySQLContainer<?> mysql =
            new MySQLContainer<>("mysql:8.3")
                    .withDatabaseName("users")
                    .withUsername("root")
                    .withPassword("12345");

    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    }


    @BeforeEach
    void setup() {
        when(keycloakService.createUser(
                ArgumentMatchers.any(),
                ArgumentMatchers.any())
        ).thenAnswer(invocation -> UUID.randomUUID().toString());
    }

    @Test
    void should_create_user_end_to_end() throws Exception {

        //* Given
        UserRequest request = new UserRequest(
                "Duvan",
                "Gonzalez",
                DocumentType.IDENTITY_DOCUMENT,
                "74.930.123",
                LocalDate.of(1995, 5, 10),
                Gender.MALE,
                "duv@gmail.com",
                "300-123-456",
                "CO"
        );

        //* When
        mockMvc.perform(post(BASE_URL)
                        .with(jwt().authorities(ADMIN))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))

                //* Then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").exists());
    }

    @Test
    void should_get_user_by_id_end_to_end() throws Exception {

        //* Given
        UserRequest request = new UserRequest(
                "Maria",
                "Perez",
                DocumentType.IDENTITY_DOCUMENT,
                "87.654321",
                LocalDate.of(1998, 3, 20),
                Gender.FEMALE,
                "maria@gmail.com",
                "300-999-888",
                "MX"
        );

        //* When
        String response = mockMvc.perform(post(BASE_URL)
                        .with(jwt().authorities(ADMIN))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        long id = objectMapper.readTree(response).get("userId").asLong();

        //* Then
        mockMvc.perform(get(BASE_URL + "/" + id)
                        .with(jwt().authorities(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(id));
    }

    @Test
    void should_delete_user_end_to_end() throws Exception {

        //* Given
        UserRequest request = new UserRequest(
                "Carlos",
                "Lopez",
                DocumentType.PASSPORT,
                "AMN3914",
                LocalDate.of(1990, 1, 15),
                Gender.MALE,
                "carlos@gmail.com",
                "301-222-333",
                "CL"
        );

        //* When
        String response = mockMvc.perform(post(BASE_URL)
                        .with(jwt().authorities(ADMIN))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        long id = objectMapper.readTree(response).get("userId").asLong();

        //* Then
        mockMvc.perform(delete(BASE_URL + "/" + id)
                        .with(jwt().authorities(ADMIN)))
                .andExpect(status().isNoContent());

        mockMvc.perform(get(BASE_URL + "/" + id)
                        .with(jwt().authorities(ADMIN)))
                .andExpect(status().is4xxClientError());
    }

}