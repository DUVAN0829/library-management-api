package co.duvan.copy.infrastructure.adapters.output.persistence.model;

import co.duvan.copy.domain.enums.Status;
import jakarta.persistence.*;

@Entity
@Table(name = "copy")
public class CopyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long copyId;
    private Long bookId;
    private String code;
    private Status status;

}
