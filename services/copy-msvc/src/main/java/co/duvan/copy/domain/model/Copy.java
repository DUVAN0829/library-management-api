package co.duvan.copy.domain.model;

import co.duvan.copy.domain.enums.Status;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Copy {

    private Long copyId;
    private Long bookId;
    private String code;
    private Status status;

}
