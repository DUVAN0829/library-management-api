package co.duvan.loan.infrastructure.adapters.input.rest;

import co.duvan.loan.domain.enums.Status;
import co.duvan.loan.infrastructure.adapters.input.rest.model.request.LoanRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
public class LoanRestAdapterIT {

    private static final String BASE_URL = "/loans/api/v1";

    private static final SimpleGrantedAuthority ADMIN =
            new SimpleGrantedAuthority("ROLE_ADMIN");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Container
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:17-alpine")
                    .withDatabaseName("loans")
                    .withUsername("postgres")
                    .withPassword("12345");

    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    }

    @Test
    void should_create_loan_end_to_end() throws Exception {

        //* Given
        LoanRequest request = new LoanRequest(
                1L,
                10L,
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                null,
                Status.ACTIVE
        );

        //* When
        mockMvc.perform(post(BASE_URL)
                        .with(jwt().authorities(ADMIN))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))

                //* Then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.loanId").exists());
    }

    @Test
    void should_get_all_loans_end_to_end() throws Exception {

        //* Given
        LoanRequest request = new LoanRequest(
                1L,
                10L,
                LocalDate.now(),
                LocalDate.now().plusDays(7),
                null,
                Status.ACTIVE
        );

        mockMvc.perform(post(BASE_URL)
                        .with(jwt().authorities(ADMIN))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        //* When & Then
        mockMvc.perform(get(BASE_URL)
                        .with(jwt().authorities(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].loanId").exists())
                .andExpect(jsonPath("$[0].userId").value(1));
    }

    @Test
    void should_delete_loan_end_to_end() throws Exception {

        //* Given
        LoanRequest request = new LoanRequest(
                3L,
                30L,
                LocalDate.now(),
                LocalDate.now().plusDays(5),
                null,
                Status.ACTIVE
        );

        //* When
        String response = mockMvc.perform(post(BASE_URL)
                        .with(jwt().authorities(ADMIN))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        long id = objectMapper.readTree(response).get("loanId").asLong();

        //* Then
        mockMvc.perform(delete(BASE_URL + "/" + id)
                        .with(jwt().authorities(ADMIN)))
                .andExpect(status().isNoContent());

        mockMvc.perform(get(BASE_URL + "/" + id)
                        .with(jwt().authorities(ADMIN)))
                .andExpect(status().is4xxClientError());
    }

}