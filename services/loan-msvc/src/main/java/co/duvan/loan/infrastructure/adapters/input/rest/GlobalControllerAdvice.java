package co.duvan.loan.infrastructure.adapters.input.rest;

import co.duvan.loan.domain.enums.ErrorCatalog;
import co.duvan.loan.domain.exceptions.LoanNotFoundException;
import co.duvan.loan.domain.model.ErrorResponse;
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
    @ExceptionHandler(LoanNotFoundException.class)
    public ErrorResponse handlerBookNotFoundException() {

        return ErrorResponse.builder()
                .code(ErrorCatalog.LOAN_NOT_FOUND.getCode())
                .message(ErrorCatalog.LOAN_NOT_FOUND.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

    }

    //* Argument not valid exception
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        BindingResult result = exception.getBindingResult();

        return ErrorResponse.builder()
                .code(ErrorCatalog.INVALID_LOAN.getCode())
                .message(ErrorCatalog.INVALID_LOAN.getMessage())
                .details(result.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList())
                .timestamp(LocalDateTime.now())
                .build();

    }

    //* Generic Exception
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse hanlderGenericError(Exception exception) {

        return ErrorResponse.builder()
                .code(ErrorCatalog.GENERIC_ERROR.getCode())
                .message(ErrorCatalog.GENERIC_ERROR.getMessage())
                .details(Collections.singletonList(exception.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
    }

}
