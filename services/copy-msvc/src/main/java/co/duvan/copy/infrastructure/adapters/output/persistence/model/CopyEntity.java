package co.duvan.copy.infrastructure.adapters.output.persistence.model;

import co.duvan.copy.domain.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "copies")
public class CopyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long copyId;
    private Long bookId;
    private String code;

    @Enumerated(EnumType.STRING)
    private Status status;

}
