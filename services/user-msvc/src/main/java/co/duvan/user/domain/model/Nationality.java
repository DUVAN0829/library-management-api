package co.duvan.user.domain.model;

public record Nationality(String countryCode) {

    public Nationality {

        if (countryCode == null || !countryCode.matches("^[A-Z]{2}$")) {
            throw new IllegalArgumentException("Invalid ISO 3166-1 alpha-2 code");
        }

    }

}