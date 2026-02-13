package co.duvan.user.domain.enums;

import lombok.Getter;

@Getter
public enum ErrorCatalog {

    USER_NOT_FOUND("ERR_USER_01", "User not found"),
    INVALID_USER("ERR_USER_02", "Invalid user parameters"),
    GENERIC_ERROR("GEN_ERR_01", "An unexpected error occurred");

    private final String code;
    private final String message;

    ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;

    }

}
