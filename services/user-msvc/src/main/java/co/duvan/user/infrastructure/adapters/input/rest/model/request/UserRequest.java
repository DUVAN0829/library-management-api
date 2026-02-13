package co.duvan.user.infrastructure.adapters.input.rest.model.request;

import co.duvan.user.domain.enums.DocumentType;
import co.duvan.user.domain.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "Field firstname cannot be blank")
    private String firstname;

    @NotBlank(message = "Field lastname cannot be blank")
    private String lastname;

    @NotNull(message = "Field documentType cannot be null")
    private DocumentType documentType;

    @NotBlank(message = "Field documentNumber cannot be blank")
    private String documentNumber;

    @NotNull(message = "Field birthdate cannot be null")
    private LocalDate birthdate;

    @NotNull(message = "Field gender cannot be null")
    private Gender gender;

    @NotBlank(message = "Field email cannot be blank")
    private String email;

    @NotBlank(message = "Field phoneNumber cannot be blank")
    private String phoneNumber;

    @NotBlank(message = "Field nationality cannot be blank")
    private String nationality;

}
