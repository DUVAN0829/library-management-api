package co.duvan.user.domain.enums;

import lombok.Getter;

@Getter
public enum Gender {

    FEMALE("Female"),
    MALE("Male"),
    OTHER("Other");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

}
