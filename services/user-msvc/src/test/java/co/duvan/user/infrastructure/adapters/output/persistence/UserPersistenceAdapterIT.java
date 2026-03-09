package co.duvan.user.infrastructure.adapters.output.persistence;

import co.duvan.user.application.ports.output.UserRepositoryPort;
import co.duvan.user.domain.enums.DocumentType;
import co.duvan.user.domain.enums.Gender;
import co.duvan.user.domain.model.Nationality;
import co.duvan.user.domain.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
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
                .keycloakId(UUID.randomUUID().toString())
                .firstname("Duván")
                .lastname("González")
                .documentType(DocumentType.IDENTITY_DOCUMENT)
                .documentNumber("23984631")
                .birthdate(LocalDate.of(2004, 2, 11))
                .gender(Gender.MALE)
                .email("duvan@gmail.com")
                .phoneNumber("314984234")
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
                .keycloakId(UUID.randomUUID().toString())
                .firstname("Kata")
                .lastname("Suárez")
                .documentType(DocumentType.PASSPORT)
                .documentNumber("90832457")
                .birthdate(LocalDate.of(2003, 1, 29))
                .gender(Gender.FEMALE)
                .email("katha04@gmail.com")
                .phoneNumber("984416091")
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
        User userA = User.builder()
                .keycloakId(UUID.randomUUID().toString())
                .firstname("Mahrie").lastname("Cardona").documentType(DocumentType.IDENTITY_DOCUMENT)
                .documentNumber("45178092").birthdate(LocalDate.of(2000, 11, 2)).gender(Gender.FEMALE)
                .email("mari56@gmail.com").phoneNumber("349902413").nationality(new Nationality("MX")).build();

        User userB = User.builder()
                .keycloakId(UUID.randomUUID().toString())
                .firstname("Jacob").lastname("Álvarez").documentType(DocumentType.OTHER)
                .documentNumber("76364419").birthdate(LocalDate.of(1997, 6, 18)).gender(Gender.MALE)
                .email("jacalv07@gmail.com").phoneNumber("632209183").nationality(new Nationality("CO")).build();

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
                .keycloakId(UUID.randomUUID().toString())
                .firstname("Mark")
                .lastname("Adams")
                .documentType(DocumentType.PASSPORT)
                .documentNumber("9837190")
                .birthdate(LocalDate.of(2001, 8, 22))
                .gender(Gender.MALE)
                .email("markadams@gmail.com")
                .phoneNumber("984416091")
                .nationality(new Nationality("AR"))
                .build();

        User exitignUser = repositoryPort.save(user);

        //* When
        exitignUser.setPhoneNumber("678321790");

        User updatedUser = repositoryPort.save(exitignUser);

        //* Then
        assertNotNull(updatedUser.getUserId());
        assertEquals("678321790", updatedUser.getPhoneNumber());

    }

    @Test
    void should_delete_user() {

        //* Given
        User user = User.builder()
                .keycloakId(UUID.randomUUID().toString())
                .firstname("Melanie")
                .lastname("Martinez")
                .documentType(DocumentType.IDENTITY_DOCUMENT)
                .documentNumber("89261725")
                .birthdate(LocalDate.of(1998, 12, 14))
                .gender(Gender.FEMALE)
                .email("melanie93@gmail.com")
                .phoneNumber("902612927")
                .nationality(new Nationality("US"))
                .build();

        User exitingUser = repositoryPort.save(user);

        //* When
        repositoryPort.deleteById(exitingUser.getUserId());

        //* Then
        Optional<User> deleteUser = repositoryPort.findById(exitingUser.getUserId());

        assertTrue(deleteUser.isEmpty());

    }

}