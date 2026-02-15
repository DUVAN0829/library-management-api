package co.duvan.copy.infrastructure.adapters.input.rest;

import co.duvan.copy.domain.enums.Status;
import co.duvan.copy.infrastructure.adapters.input.rest.model.request.CopyRequest;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
public class CopyRestAdapterIT {

    private static final String BASE_URL = "/copies/api/v1";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Container
    static MySQLContainer<?> mysql =
            new MySQLContainer<>("mysql:8.3")
                    .withDatabaseName("copies")
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
    void should_create_copy_end_to_end() throws Exception {

        //* Given
        CopyRequest request = new CopyRequest(
                1L,
                "CP-100",
                Status.AVAILABLE
        );

        //* When
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))

                //* Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.copyId").exists())
                .andExpect(jsonPath("$.code").value("CP-100"));
    }

    @Test
    void should_get_copy_by_id_end_to_end() throws Exception {

        //* Given
        CopyRequest request = new CopyRequest(
                2L,
                "CP-200",
                Status.LOANED
        );

        String response = mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        long id = objectMapper.readTree(response).get("copyId").asLong();

        //* When
        mockMvc.perform(get(BASE_URL + "/" + id))

                //* Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.copyId").value(id))
                .andExpect(jsonPath("$.code").value("CP-200"));
    }

}
