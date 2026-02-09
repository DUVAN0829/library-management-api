package co.duvan.book.infrastructure.adapters.input.rest;

import co.duvan.book.domain.enums.ErrorCatalog;
import co.duvan.book.domain.exceptions.BookNotFoundException;
import co.duvan.book.domain.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalControllerAdvice {

    //* Not found exception
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BookNotFoundException.class)
    public ErrorResponse handlerBookNotFoundException() {

        return ErrorResponse.builder()
                .code(ErrorCatalog.BOOK_NOT_FOUND.getCode())
                .message(ErrorCatalog.BOOK_NOT_FOUND.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

    }



}
