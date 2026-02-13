package co.duvan.user.infrastructure.adapters.input.rest.model.response;

import co.duvan.user.domain.enums.DocumentType;
import co.duvan.user.domain.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long userId;
    private String firstname;
    private String lastname;
    private DocumentType documentType;
    private String documentNumber;
    private LocalDate birthdate;
    private Gender gender;
    private String email;
    private String phoneNumber;
    private String nationality;

}
