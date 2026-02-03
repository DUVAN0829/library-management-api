package co.duvan.book.domain.enums;

import lombok.Getter;

@Getter
public enum Category {

    THRILLER("Thriller"),
    DRAMA("Drama"),
    ROMANCE("Romance"),
    HISTORY("History"),
    DYSTOPIA("Dystopia"),
    FICTION("Fiction"),
    PROGRAMMING("Programming");

    private final String value;

    Category(String value) {
        this.value = value;
    }

}
