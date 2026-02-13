package co.duvan.user.infrastructure.adapters.input.rest;

import co.duvan.user.domain.enums.ErrorCatalog;
import co.duvan.user.domain.exceptions.UserNotFoundException;
import co.duvan.user.domain.model.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;

@RestControllerAdvice
public class GlobalControllerAdvice {

    //* Not found exception
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse handlerUserNotFoundException() {

        return ErrorResponse.builder()
                .code(ErrorCatalog.USER_NOT_FOUND.getCode())
                .message(ErrorCatalog.USER_NOT_FOUND.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    //* Validation exception (DTO validations)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        BindingResult result = exception.getBindingResult();

        return ErrorResponse.builder()
                .code(ErrorCatalog.INVALID_USER.getCode())
                .message(ErrorCatalog.INVALID_USER.getMessage())
                .details(result.getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .toList())
                .timestamp(LocalDateTime.now())
                .build();
    }

    //* Domain validation exception (ej: Nationality inválida)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse handlerIllegalArgumentException(IllegalArgumentException exception) {

        return ErrorResponse.builder()
                .code(ErrorCatalog.INVALID_USER.getCode())
                .message(ErrorCatalog.INVALID_USER.getMessage())
                .details(Collections.singletonList(exception.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
    }

    //* Generic Exception
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handlerGenericError(Exception exception) {

        return ErrorResponse.builder()
                .code(ErrorCatalog.GENERIC_ERROR.getCode())
                .message(ErrorCatalog.GENERIC_ERROR.getMessage())
                .details(Collections.singletonList(exception.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
    }

}
