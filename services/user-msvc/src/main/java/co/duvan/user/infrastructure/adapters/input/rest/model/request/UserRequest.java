package co.duvan.user.infrastructure.adapters.input.rest.model.request;

import co.duvan.user.domain.enums.DocumentType;
import co.duvan.user.domain.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
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
            example = "Alex",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Size(min = 2, max = 50, message = "Field firstname must be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ ]+$", message = "Field firstname can only contain letters")
    @NotBlank(message = "Field firstname cannot be blank")
    private String firstname;

    @Schema(
            description = "lastname of the user",
            example = "González",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Size(min = 2, max = 50, message = "Field lastname must be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ ]+$", message = "Field lastname can only contain letters")
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
            example = "32984032",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Size(min = 5, max = 20, message = "Field documentNumber must be between 5 and 20 characters")
    @Pattern(regexp = "^[0-9]+$", message = "Field documentNumber can only contain numbers")
    @NotBlank(message = "Field documentNumber cannot be blank")
    private String documentNumber;

    @Schema(
            description = "birthdate of the user",
            example = "2004-02-11",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Past(message = "Field birthdate must be a past date")
    @NotNull(message = "Field birthdate cannot be null")
    private LocalDate birthdate;

    @Schema(
            description = "gender of the user",
            example = "MALE",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Field gender cannot be null")
    private Gender gender;

    @Schema(
            description = "email of the user",
            example = "alex83@gmail.com",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Email(message = "Field email must be a valid email address")
    @Size(max = 50, message = "Field email cannot exceed 50 characters")
    @NotBlank(message = "Field email cannot be blank")
    private String email;

    @Schema(
            description = "phoneNumber of the user",
            example = "3189366812",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Field phoneNumber cannot be blank")
    @Pattern(regexp = "^[0-9]{7,15}$", message = "Field phoneNumber must contain between 7 and 15 digits")
    @NotBlank(message = "Field phoneNumber cannot be blank")
    private String phoneNumber;

    @Schema(
            description = "nationality of the user in ISO 3166-1 alpha-2 format",
            example = "CO",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Size(min = 2, max = 2, message = "Field nationality must be a valid ISO 3166-1 alpha-2 code")
    @Pattern(regexp = "^[A-Z]{2}$", message = "Field nationality must be uppercase, example: CO, US, MX")
    @NotBlank(message = "Field nationality cannot be blank")
    private String nationality;

}