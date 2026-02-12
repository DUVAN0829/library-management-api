package co.duvan.user.application.usecase;

import co.duvan.user.domain.model.Nationality;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class NationalityTest {

    @Test
    void should_create_nationality_when_iso_code_is_valid() {

        Nationality nationality = new Nationality("CO");

        assertNotNull(nationality);
        assertEquals("CO", nationality.countryCode());

    }

    @Test
    void should_throw_exception_when_iso_code_is_lowercase() {

        assertThrows(IllegalArgumentException.class,
                () -> new Nationality("co"));

    }

    @Test
    void should_throw_exception_when_iso_code_length_is_invalid() {

        assertThrows(IllegalArgumentException.class,
                () -> new Nationality("COL"));

    }

    @Test
    void should_throw_exception_when_iso_code_is_null() {

        assertThrows(IllegalArgumentException.class,
                () -> new Nationality(null));

    }

    @Test
    void should_throw_exception_when_iso_code_contains_numbers() {

        assertThrows(IllegalArgumentException.class,
                () -> new Nationality("C1"));

    }


}