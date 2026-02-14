package co.duvan.copy.domain.enums;

import lombok.Getter;

@Getter
public enum Status {

    AVAILABLE("Available"),
    LOANED("Loaned"),
    DAMAGED("Damaged"),
    WITHDRAWN("Withdrawn");

    private final String value;

    Status(String value) {
        this.value = value;
    }

}
