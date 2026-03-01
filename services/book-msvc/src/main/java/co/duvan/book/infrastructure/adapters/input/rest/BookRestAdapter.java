package co.duvan.book.infrastructure.adapters.input.rest;

import co.duvan.book.application.ports.input.CreateBookUseCase;
import co.duvan.book.application.ports.input.DeleteBookUseCase;
import co.duvan.book.application.ports.input.GetBookUseCase;
import co.duvan.book.application.ports.input.UpdateBookUseCase;
import co.duvan.book.domain.enums.Category;
import co.duvan.book.domain.model.BookFilterQuery;
import co.duvan.book.infrastructure.adapters.input.rest.documentation.DefaultApiErrors;
import co.duvan.book.infrastructure.adapters.input.rest.documentation.ValidationApiError;
import co.duvan.book.infrastructure.adapters.input.rest.mapper.BookRestMapper;
import co.duvan.book.infrastructure.adapters.input.rest.model.request.BookRequest;
import co.duvan.book.infrastructure.adapters.input.rest.model.response.BookResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
@Tag(name = "Books API", description = "Operations basic related books (CRUD)")
public class BookRestAdapter {

    private final BookRestMapper bookRestMapper;
    private final GetBookUseCase getBookUseCase;
    private final CreateBookUseCase createBookUseCase;
    private final UpdateBookUseCase updateBookUseCase;
    private final DeleteBookUseCase deleteBookUseCase;

    @Operation(summary = "Get all books")
    @ApiResponse(responseCode = "200", description = "List all books")
    @DefaultApiErrors
    @GetMapping("/api/v1")
    public ResponseEntity<List<BookResponse>> getAllBooks() {

        return ResponseEntity.ok(bookRestMapper.toBookResponseList(getBookUseCase.findAll()));

    }

    @Operation(summary = "Get book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book found"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @DefaultApiErrors
    @GetMapping("/api/v1/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {

        if (id == 999L) {
            throw new RuntimeException("Error 500");
        }

        return ResponseEntity.ok(bookRestMapper.toBookResponse(getBookUseCase.findById(id)));

    }

    @Operation(summary = "Create book")
    @ApiResponse(responseCode = "201", description = "Book created")
    @DefaultApiErrors
    @ValidationApiError
    @PostMapping("/api/v1")
    public ResponseEntity<BookResponse> save(@Valid @RequestBody BookRequest bookRequest) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookRestMapper.toBookResponse(createBookUseCase.save(bookRestMapper.toBook(bookRequest))));

    }

    @Operation(summary = "Update book")
    @ApiResponse(responseCode = "200", description = "Book updated")
    @DefaultApiErrors
    @ValidationApiError
    @PutMapping("/api/v1/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequest bookRequest) {

        return ResponseEntity.ok()
                .body(bookRestMapper.toBookResponse(updateBookUseCase.update(id, bookRestMapper.toBook(bookRequest))));

    }

    @Operation(summary = "Delete book")
    @ApiResponse(responseCode = "204", description = "Book deleted")
    @DefaultApiErrors
    @DeleteMapping("/api/v1/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable Long id) {

        deleteBookUseCase.deleteById(id);

        return ResponseEntity.noContent().build();

    }

    @Operation(summary = "Filter books")
    @ApiResponse(responseCode = "200", description = "Filtered books")
    @GetMapping("/api/v1/filter")
    public ResponseEntity<List<BookResponse>> getBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Category category
    ) {
        BookFilterQuery filter = new BookFilterQuery(title, author, category);
        return ResponseEntity.ok(
                getBookUseCase.getWithFilters(filter)
                        .stream()
                        .map(bookRestMapper::toBookResponse)
                        .collect(Collectors.toList())
        );
    }

}