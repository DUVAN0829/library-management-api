package co.duvan.book.domain.enums;

import lombok.Getter;

@Getter
public enum ErrorCatalog {

    BOOK_NOT_FOUND("ERR_STUDENT_01", "Student not found"),
    INVALID_STUDENT("ERR_STUDENT_02", "Invalid student parameters"),
    GENERIC_ERROR("GEN_ERR_01", "An unexpected error occurred");

    private final String code;
    private final String message;

    ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
