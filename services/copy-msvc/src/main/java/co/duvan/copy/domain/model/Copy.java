package co.duvan.copy.domain.model;

import co.duvan.copy.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Copy {

    private Long copyId;
    private Long bookId;
    private String code;
    private Status status;

}
