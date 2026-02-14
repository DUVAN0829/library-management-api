package co.duvan.prestamo.domain.enums;

import lombok.Getter;

@Getter
public enum ErrorCatalog {

    LOAN_NOT_FOUND("ERR_LOAN_01", "Loan not found"),
    INVALID_LOAN("ERR_LOAN_02", "Invalid loan parameters"),
    GENERIC_ERROR("GEN_ERR_01", "An unexpected error occurred");

    private final String code;
    private final String message;

    ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;

    }

}
