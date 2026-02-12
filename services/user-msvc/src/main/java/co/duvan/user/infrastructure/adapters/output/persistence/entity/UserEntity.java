package co.duvan.user.infrastructure.adapters.output.persistence.entity;

import co.duvan.user.domain.enums.DocumentType;
import co.duvan.user.domain.enums.Gender;
import co.duvan.user.domain.model.Nationality;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String firstname;

    private String lastname;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type")
    private DocumentType documentType;

    @Column(name = "document_number")
    private String documentNumber;

    private LocalDate birthdate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "nationality")
    private String nationality;

}
