package co.duvan.user.infrastructure.adapters.input.rest.model.response;

import co.duvan.user.domain.enums.DocumentType;
import co.duvan.user.domain.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
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
        name = "User Response",
        description = "User data returned by the API"
)
public class UserResponse {

    @Schema(
            description = "userId of the user",
            example = "5"
    )
    private Long userId;

    @Schema(
            description = "firstname of the user",
            example = "Duván"
    )
    private String firstname;

    @Schema(
            description = "lastname of the user",
            example = "González"
    )
    private String lastname;

    @Schema(
            description = "documentType of the user",
            example = "IDENTITY_DOCUMENT"
    )
    private DocumentType documentType;

    @Schema(
            description = "documentNumber of the user",
            example = "3298403241"
    )
    private String documentNumber;

    @Schema(
            description = "birthdate of the user",
            example = "2004-02-11"
    )
    private LocalDate birthdate;

    @Schema(
            description = "gender of the user",
            example = "MALE"
    )
    private Gender gender;

    @Schema(
            description = "email of the user",
            example = "duvan@gmail.com"
    )
    private String email;

    @Schema(
            description = "phoneNumber of the user",
            example = "3189368127"
    )
    private String phoneNumber;

    @Schema(
            description = "Nationality of the user in ISO 3166-1 alpha-2 format",
            example = "CO"
    )
    private String nationality;

}
