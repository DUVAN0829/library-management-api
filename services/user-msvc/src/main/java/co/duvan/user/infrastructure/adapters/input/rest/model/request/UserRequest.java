package co.duvan.user.infrastructure.adapters.input.rest.model.request;

import co.duvan.user.domain.enums.DocumentType;
import co.duvan.user.domain.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
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
@Schema(
        name = "User Request",
        description = "Payload used to create or update a user in the system"
)
public class UserRequest {

    @Schema(
            description = "firstname of the user",
            example = "Duván",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Field firstname cannot be blank")
    private String firstname;

    @Schema(
            description = "lastname of the user",
            example = "González",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Field lastname cannot be blank")
    private String lastname;

    @Schema(
            description = "documentType of the user",
            example = "IDENTITY_DOCUMENT",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Field documentType cannot be null")
    private DocumentType documentType;

    @Schema(
            description = "documentNumber of the user",
            example = "32.984.032",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Field documentNumber cannot be blank")
    private String documentNumber;

    @Schema(
            description = "birthdate of the user",
            example = "2004-02-11",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Field birthdate cannot be null")
    private LocalDate birthdate;

    @Schema(
            description = "documentType of the user",
            example = "MALE",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Field gender cannot be null")
    private Gender gender;

    @Schema(
            description = "documentType of the user",
            example = "duvan@gmail.com",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Field email cannot be blank")
    @Email
    private String email;

    @Schema(
            description = "documentType of the user",
            example = "318-936-812",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Field phoneNumber cannot be blank")
    private String phoneNumber;

    @Schema(
            description = "documentType of the user",
            example = "CO",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Field nationality cannot be blank")
    private String nationality;

}