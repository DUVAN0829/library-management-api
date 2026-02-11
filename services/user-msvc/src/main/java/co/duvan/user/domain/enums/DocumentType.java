package co.duvan.user.domain.enums;

import lombok.Getter;

@Getter
public enum DocumentType {

    IDENTITY_DOCUMENT("Identity Document"),
    PASSPORT("Passport"),
    OTHER("Other");

    private final String value;

    DocumentType(String value) {
        this.value = value;
    }

}
