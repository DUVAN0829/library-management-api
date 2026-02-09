package co.duvan.book.infrastructure.adapters.input.rest;

import co.duvan.book.application.ports.input.CreateBookUseCase;
import co.duvan.book.application.ports.input.DeleteBookUseCase;
import co.duvan.book.application.ports.input.GetBookUseCase;
import co.duvan.book.application.ports.input.UpdateBookUseCase;
import co.duvan.book.domain.enums.Category;
import co.duvan.book.domain.model.Book;
import co.duvan.book.infrastructure.adapters.input.rest.mapper.BookRestMapper;
import co.duvan.book.infrastructure.adapters.input.rest.model.request.BookRequest;
import co.duvan.book.infrastructure.adapters.input.rest.model.response.BookResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookRestAdapter.class)
class BookRestAdapterTest {

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

    private Book book;
    private BookResponse bookResponse;

    @BeforeEach
    void setup() {
        book = new Book(1L, "Clean Code", "ISBN-1", "desc",
                Category.PROGRAMMING, List.of("Robert"), "Prentice");

        bookResponse = new BookResponse(1L, "Clean Code", "ISBN-1", "desc",
                Category.PROGRAMMING, List.of("Robert"), "Prentice");
    }

    @Test
    void should_get_book_by_id() throws Exception {

        //* Given
        when(getBookUseCase.findByid(1L)).thenReturn(book);
        when(bookRestMapper.toBookResponse(book)).thenReturn(bookResponse);

        //* When
        mockMvc.perform(get("/books/api/v1/1"))

                //* Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookId").value(1));

    }

    @Test
    void should_get_all_books() throws Exception {

        //* Given
        when(getBookUseCase.findAll()).thenReturn(List.of(book));
        when(bookRestMapper.toBookResponseList(List.of(book))).thenReturn(List.of(bookResponse));

        //* When
        mockMvc.perform(get("/books/api/v1"))

                //* Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].bookId").value(1));

    }

    @Test
    void should_create_book() throws Exception {

        //* Given
        BookRequest request = new BookRequest();
        request.setTitle("Clean Code V2");

        when(bookRestMapper.toBookResponse(book)).thenReturn(bookResponse);
        when(createBookUseCase.save(book)).thenReturn(book);
        when(bookRestMapper.toBook(request)).thenReturn(book);

        //* When
        mockMvc.perform(post("/books/api/v1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

    }



}





















































