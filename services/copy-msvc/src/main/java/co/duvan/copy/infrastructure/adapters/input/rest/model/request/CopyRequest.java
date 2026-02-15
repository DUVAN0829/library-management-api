package co.duvan.copy.infrastructure.adapters.input.rest.model.request;

import co.duvan.copy.domain.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CopyRequest {

    @NotNull(message = "Field bookId cannot be null")
    private Long bookId;

    @NotBlank(message = "Field code cannot be blank")
    private String code;

    @NotNull(message = "Field status cannot be null")
    private Status status;

}
