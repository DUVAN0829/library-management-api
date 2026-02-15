package co.duvan.copy.infrastructure.adapters.input.rest.model.response;

import co.duvan.copy.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CopyResponse {

    private Long copyId;
    private Long bookId;
    private String code;
    private Status status;

}
