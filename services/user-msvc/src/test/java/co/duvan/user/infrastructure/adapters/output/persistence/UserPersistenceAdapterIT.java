package co.duvan.user.infrastructure.adapters.output.persistence;

import co.duvan.user.application.ports.output.UserRepositoryPort;
import co.duvan.user.domain.enums.DocumentType;
import co.duvan.user.domain.enums.Gender;
import co.duvan.user.domain.model.Nationality;
import co.duvan.user.domain.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
class UserPersistenceAdapterIT {

    @Container
    static MySQLContainer<?> mysql =
            new MySQLContainer<>("mysql:8.3")
                    .withDatabaseName("users")
                    .withUsername("root")
                    .withPassword("12345");

    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    }

    @Autowired
    private UserRepositoryPort repositoryPort;

    @Test
    void should_save_user() {

        //* Given
        User user = User.builder()
                .firstname("Duván")
                .lastname("González")
                .documentType(DocumentType.IDENTITY_DOCUMENT)
                .documentNumber("23.984.631")
                .birthdate(LocalDate.of(2004, 2, 11))
                .gender(Gender.MALE)
                .email("duvan@gmail.com")
                .phoneNumber("314-984-234")
                .nationality(new Nationality("CO"))
                .build();

        //* When
        User exitingUser = repositoryPort.save(user);

        //* Then
        assertNotNull(exitingUser);
        assertNotNull(exitingUser.getUserId());

    }

}














