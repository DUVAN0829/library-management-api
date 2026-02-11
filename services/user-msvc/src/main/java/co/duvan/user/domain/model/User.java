package co.duvan.user.domain.model;

import co.duvan.user.domain.enums.DocumentType;
import co.duvan.user.domain.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class User {

    private Long userId;
    private String firstname;
    private String lastname;
    private DocumentType documentType;
    private String documentNumber;
    private LocalDate birthdate;
    private Gender gender;
    private String address;
    private String phoneNumber;
    private Nationality nationality;

}
