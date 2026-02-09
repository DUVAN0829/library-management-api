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
import org.hibernate.mapping.Any;
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

    private Book book;
    private BookResponse bookResponse;
    private BookRequest bookRequest;

    @BeforeEach
    void setup() {
        book = new Book(1L, "Clean Code", "ISBN-1", "desc",
                Category.PROGRAMMING, List.of("Robert"), "Prentice");

        bookResponse = new BookResponse(1L, "Clean Code", "ISBN-1", "desc",
                Category.PROGRAMMING, List.of("Robert"), "Prentice");


        bookRequest = new BookRequest("Clean Code V2", "ISBN-981", "Some description",
                Category.PROGRAMMING, List.of("Robert C. Martin"), "Prentice");
    }

    @Test
    void should_get_book_by_id() throws Exception {

        //* Given
        when(getBookUseCase.findById(1L)).thenReturn(book);
        when(bookRestMapper.toBookResponse(book)).thenReturn(bookResponse);

        //* When
        mockMvc.perform(get(BASE_URL + "/1"))

                //* Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookId").value(1))
                .andExpect(jsonPath("$.title").value("Clean Code"));

        verify(getBookUseCase).findById(1L);
        verify(bookRestMapper).toBookResponse(book);

    }

    @Test
    void should_get_all_books() throws Exception {

        //* Given
        when(getBookUseCase.findAll()).thenReturn(List.of(book));
        when(bookRestMapper.toBookResponseList(List.of(book))).thenReturn(List.of(bookResponse));

        //* When
        mockMvc.perform(get(BASE_URL))

                //* Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].bookId").value(1))
                .andExpect(jsonPath("$.[0].isbn").value("ISBN-1"));

        verify(getBookUseCase).findAll();
        verify(bookRestMapper).toBookResponseList(List.of(book));

    }

    @Test
    void should_create_book() throws Exception {

        //* Given
        when(bookRestMapper.toBookResponse(any(Book.class))).thenReturn(bookResponse);
        when(createBookUseCase.save(any(Book.class))).thenReturn(book);
        when(bookRestMapper.toBook(any(BookRequest.class))).thenReturn(book);

        //* When
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookRequest)))
                .andExpect(jsonPath("$.title").value("Clean Code"))
                .andExpect(status().isCreated());

        verify(createBookUseCase).save(any(Book.class));
        verify(bookRestMapper).toBookResponse(any(Book.class));
        verify(bookRestMapper).toBook(any(BookRequest.class));

    }

    @Test
    void should_update_book() throws Exception {

        //* Given
        when(bookRestMapper.toBookResponse(any(Book.class))).thenReturn(bookResponse);
        when(updateBookUseCase.update(anyLong(), any(Book.class))).thenReturn(book);
        when(bookRestMapper.toBook(any(BookRequest.class))).thenReturn(book);

        //* When
        mockMvc.perform(put(BASE_URL + "/1")

                        //* Then
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookRequest)))
                .andExpect(jsonPath("$.authors.size()").value(1))
                .andExpect(status().isOk());

        verify(updateBookUseCase).update(eq(1L), any(Book.class));
        verify(bookRestMapper).toBookResponse(any(Book.class));
        verify(bookRestMapper).toBook(any(BookRequest.class));

    }

    @Test
    void should_delete_book() throws Exception {

        doNothing().when(deleteBookUseCase).deleteById(1L);

        mockMvc.perform(delete(BASE_URL + "/1"))
                .andExpect(status().isNoContent());

        verify(deleteBookUseCase).deleteById(1L);

    }

}