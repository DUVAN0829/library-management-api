package co.duvan.copy.domain.enums;

import lombok.Getter;

@Getter
public enum ErrorCatalog {

    COPY_NOT_FOUND("ERR_COPY_01", "Copy not found"),
    INVALID_COPY("ERR_COPY_02", "Invalid copy parameters"),
    GENERIC_ERROR("GEN_ERR_01", "An unexpected error occurred");

    private final String code;
    private final String message;

    ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;

    }

}
