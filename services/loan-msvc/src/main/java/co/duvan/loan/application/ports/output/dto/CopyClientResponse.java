package co.duvan.loan.application.ports.output.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CopyClientResponse {

    private Long copyId;
    private Long bookId;
    private String code;
    private String status;

}
