package co.duvan.loan.application.ports.output.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserClientResponse {

    private Long userId;
    private String firstname;
    private String lastname;
    private String documentType;
    private String documentNumber;
    private LocalDate birthdate;
    private String gender;
    private String email;
    private String phoneNumber;
    private String nationality;

}