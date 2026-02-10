package co.duvan.book.infrastructure.adapters.input.rest;

import co.duvan.book.application.ports.input.CreateBookUseCase;
import co.duvan.book.application.ports.input.DeleteBookUseCase;
import co.duvan.book.application.ports.input.GetBookUseCase;
import co.duvan.book.application.ports.input.UpdateBookUseCase;
import co.duvan.book.domain.exceptions.BookNotFoundException;
import co.duvan.book.infrastructure.adapters.input.rest.mapper.BookRestMapper;
import co.duvan.book.infrastructure.adapters.input.rest.model.request.BookRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(BookRestAdapter.class)
@Import(GlobalControllerAdvice.class)
class GlobalControllerAdviceTest {

    private static final String BASE_URL = "/books/api/v1";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GetBookUseCase getBookUseCase;

    @MockitoBean
    private CreateBookUseCase createBookUseCase;

    @MockitoBean
    private UpdateBookUseCase updateBookUseCase;

    @MockitoBean
    private DeleteBookUseCase deleteBookUseCase;

    @MockitoBean
    private BookRestMapper bookRestMapper;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_return_404_when_book_not_found() throws Exception {

        //* Given
        when(getBookUseCase.findById(99L)).thenThrow(new BookNotFoundException());

        //* When
        mockMvc.perform(get("/books/api/v1/99"))

                //* Then
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("ERR_BOOK_01"))
                .andExpect(jsonPath("$.message").value("Book not found"));

    }

    @Test
    void should_return_400_when_book_request_is_invalid() throws Exception {

        //* Given
        BookRequest invalidRequest = new BookRequest();
        invalidRequest.setTitle("");

        //* When
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("ERR_BOOK_02"))
                .andExpect(jsonPath("$.message").value("Invalid book parameters"))
                .andExpect(jsonPath("$.details").isArray())
                .andExpect(jsonPath("$.details[?(@ == 'Field title cannot be blank')]").exists());

    }

}















