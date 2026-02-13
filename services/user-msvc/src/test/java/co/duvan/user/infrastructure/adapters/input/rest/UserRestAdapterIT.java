package co.duvan.user.infrastructure.adapters.input.rest;

import co.duvan.user.domain.enums.DocumentType;
import co.duvan.user.domain.enums.Gender;
import co.duvan.user.infrastructure.adapters.input.rest.model.request.UserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
public class UserRestAdapterIT {

    private static final String BASE_URL = "/users/api/v1";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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

    @Test
    void should_create_user_end_to_end() throws Exception {

        //* Given
        UserRequest request = new UserRequest(
                "Duvan",
                "Gonzalez",
                DocumentType.PASSPORT,
                "12345678",
                LocalDate.of(1995, 5, 10),
                Gender.MALE,
                "duvan@email.com",
                "3001234567",
                "CO"
        );

        //* When
        mockMvc.perform(post(BASE_URL)
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
                "87654321",
                LocalDate.of(1998, 3, 20),
                Gender.FEMALE,
                "maria@email.com",
                "3009998888",
                "CO"
        );

        //* Create
        String response = mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        long id = objectMapper.readTree(response).get("userId").asLong();

        //* When + Then
        mockMvc.perform(get(BASE_URL + "/" + id))
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
                "111222333",
                LocalDate.of(1990, 1, 15),
                Gender.MALE,
                "carlos@email.com",
                "3012223333",
                "CO"
        );

        //* Create
        String response = mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        long id = objectMapper.readTree(response).get("userId").asLong();

        //* Delete
        mockMvc.perform(delete(BASE_URL + "/" + id))
                .andExpect(status().isNoContent());

        //* Verify deleted
        mockMvc.perform(get(BASE_URL + "/" + id))
                .andExpect(status().is4xxClientError());
    }

}