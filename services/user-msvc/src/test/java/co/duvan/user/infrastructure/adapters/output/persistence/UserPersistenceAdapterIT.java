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
import java.util.List;

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

    @Test
    void should_find_user_by_id() {

        //* Given
        User user = User.builder()
                .firstname("Kata")
                .lastname("Suárez")
                .documentType(DocumentType.PASSPORT)
                .documentNumber("90.832.457")
                .birthdate(LocalDate.of(2003, 1, 29))
                .gender(Gender.FEMALE)
                .email("katha04@gmail.com")
                .phoneNumber("984-416-091")
                .nationality(new Nationality("AR"))
                .build();

        User exitingUser = repositoryPort.save(user);

        //* When
        User result = repositoryPort.findById(exitingUser.getUserId()).orElseThrow();

        //* Then
        assertNotNull(result);
        assertNotNull(result.getUserId());

    }

    @Test
    void should_find_all_users() {

        //* Given
        User userA = User.builder().firstname("Mahrie").lastname("Cardona").documentType(DocumentType.IDENTITY_DOCUMENT)
                .documentNumber("45.178.092").birthdate(LocalDate.of(2000, 11, 2)).gender(Gender.FEMALE)
                .email("mari56@gmail.com").phoneNumber("349-902-413").nationality(new Nationality("MX")).build();

        User userB = User.builder().firstname("Jacob").lastname("Álvarez").documentType(DocumentType.OTHER)
                .documentNumber("76.364.419").birthdate(LocalDate.of(1997, 6, 18)).gender(Gender.MALE)
                .email("jacalv07@gmail.com").phoneNumber("632-209-183").nationality(new Nationality("CO")).build();

        repositoryPort.save(userA);
        repositoryPort.save(userB);

        //* When
        List<User> result = repositoryPort.findAll();

        //* Then
        assertFalse(result.isEmpty());
        assertTrue(result.size() >= 2);

    }

    @Test
    void should_update_user() {

        //* Given
        User user = User.builder()
                .firstname("Mark")
                .lastname("Adams")
                .documentType(DocumentType.PASSPORT)
                .documentNumber("65.892.701")
                .birthdate(LocalDate.of(2001, 8, 22))
                .gender(Gender.MALE)
                .email("markadams@gmail.com")
                .phoneNumber("984-416-091")
                .nationality(new Nationality("AR"))
                .build();

        User exitignUser = repositoryPort.save(user);

        //* When
        exitignUser.setPhoneNumber("678-321-790");

        User updatedUser = repositoryPort.save(exitignUser);

        //* Then
        assertNotNull(updatedUser.getUserId());
        assertEquals("678-321-790", updatedUser.getPhoneNumber());

    }

}














