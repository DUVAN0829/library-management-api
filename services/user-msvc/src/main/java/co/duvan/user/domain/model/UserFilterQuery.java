package co.duvan.user.domain.model;

import co.duvan.user.domain.enums.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserFilterQuery {

    private String firstname;
    private String lastname;
    private DocumentType documentType;
    private String documentNumber;

}