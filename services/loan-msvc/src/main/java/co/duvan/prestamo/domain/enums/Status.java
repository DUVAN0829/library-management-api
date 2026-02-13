package co.duvan.prestamo.domain.enums;

import lombok.Getter;

@Getter
public enum Status {

    ACTIVE("Active"),
    RETURNED("Returned"),
    OVERDUE("Overdue");

    private final String value;

    Status(String value) {
        this.value = value;
    }

}
