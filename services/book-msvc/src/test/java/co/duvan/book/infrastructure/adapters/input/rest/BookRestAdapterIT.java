package co.duvan.book.infrastructure.adapters.input.rest;

import co.duvan.book.domain.enums.Category;
import co.duvan.book.infrastructure.adapters.input.rest.model.request.BookRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
public class BookRestAdapterIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Container
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:17-alpine")
                    .withDatabaseName("books")
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
    void should_create_book_end_to_end() throws Exception {

        //* Given
        BookRequest bookRequest = new BookRequest(
                "1984",
                "ISBN-9780451524935",
                "A dystopian novel about surveillance, control, and the loss of freedom.",
                Category.DYSTOPIA,
                List.of("George Orwell"),
                "Secker & Warburg"
        );

        //* When
        mockMvc.perform(post("/books/api/v1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookRequest)))

                //* Then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.bookId").exists());

    }

    @Test
    void should_get_book_by_id_end_to_end() throws Exception {

        //* Given
        BookRequest bookRequest = new BookRequest(
                "The Shining",
                "ISBN-9780307743657",
                "A writer and his family face terrifying forces in an isolated hotel.",
                Category.THRILLER,
                List.of("Stephen King"),
                "Doubleday"
        );

        //* When
        String response = mockMvc.perform(post("/books/api/v1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookRequest)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        long id = objectMapper.readTree(response).get("bookId").asLong();

        //* When
        mockMvc.perform(get("/books/api/v1/" + id))

                //* Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookId").value(id));

    }

    @Test
    void should_delete_book_end_to_end() throws Exception {

        //* Given
        BookRequest bookRequest = new BookRequest(
                "Misery",
                "ISBN-9780450417399",
                "A famous writer is held captive by an obsessed fan.",
                Category.THRILLER,
                List.of("Stephen King"),
                "Viking Press"
        );

        //* When
        String response = mockMvc.perform(post("/books/api/v1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookRequest)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        long id = objectMapper.readTree(response).get("bookId").asLong();

        //* Then
        mockMvc.perform(delete("/books/api/v1/" + id))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/books/api/v1/" + id))
                .andExpect(status().is4xxClientError());

    }

}












