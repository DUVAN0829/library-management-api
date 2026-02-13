package co.duvan.user.infrastructure.adapters.input.rest.model.request;

import co.duvan.user.domain.enums.DocumentType;
import co.duvan.user.domain.enums.Gender;
import co.duvan.user.domain.model.Nationality;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String firstname;
    private String lastname;
    private DocumentType documentType;
    private String documentNumber;
    private LocalDate birthdate;
    private Gender gender;
    private String email;
    private String phoneNumber;
    private Nationality nationality;

}
