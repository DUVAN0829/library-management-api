package co.duvan.book.infrastructure.adapters.input.rest;

import co.duvan.book.application.ports.input.CreateBookUseCase;
import co.duvan.book.application.ports.input.DeleteBookUseCase;
import co.duvan.book.application.ports.input.GetBookUseCase;
import co.duvan.book.application.ports.input.UpdateBookUseCase;
import co.duvan.book.infrastructure.adapters.input.rest.mapper.BookRestMapper;
import co.duvan.book.infrastructure.adapters.input.rest.model.request.BookRequest;
import co.duvan.book.infrastructure.adapters.input.rest.model.response.BookResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookRestAdapter {

    private final BookRestMapper bookRestMapper;
    private final GetBookUseCase getBookUseCase;
    private final CreateBookUseCase createBookUseCase;
    private final UpdateBookUseCase updateBookUseCase;
    private final DeleteBookUseCase deleteBookUseCase;

    @GetMapping("/api/v1")
    public ResponseEntity<List<BookResponse>> getAllBooks() {

        return ResponseEntity.ok(bookRestMapper.toBookResponseList(getBookUseCase.findAll()));

    }

    @GetMapping("/api/v1/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {

        return ResponseEntity.ok(bookRestMapper.toBookResponse(getBookUseCase.findById(id)));

    }

    @PostMapping("/api/v1")
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookRequest bookRequest) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookRestMapper.toBookResponse(createBookUseCase.save(bookRestMapper.toBook(bookRequest))));

    }

    @PutMapping("/api/v1/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequest bookRequest) {

        return ResponseEntity.ok()
                .body(bookRestMapper.toBookResponse(updateBookUseCase.update(id, bookRestMapper.toBook(bookRequest))));

    }

    @DeleteMapping("/api/v1/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable Long id) {

        deleteBookUseCase.deleteById(id);

        return ResponseEntity.noContent().build();

    }

}


















