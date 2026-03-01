package co.duvan.user.infrastructure.adapters.output.persistence.repository;

import co.duvan.user.domain.enums.DocumentType;
import co.duvan.user.infrastructure.adapters.output.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByKeycloakId(String keycloakId);

    @Query("""
    SELECT u FROM UserEntity u
    WHERE (:firstname IS NULL OR LOWER(u.firstname) LIKE LOWER(CONCAT('%', CAST(:firstname AS string), '%')))
    AND (:lastname IS NULL OR LOWER(u.lastname) LIKE LOWER(CONCAT('%', CAST(:lastname AS string), '%')))
    AND (:documentType IS NULL OR u.documentType = :documentType)
    AND (:documentNumber IS NULL OR u.documentNumber = :documentNumber)
""")
    List<UserEntity> findWithFilters(
            @Param("firstname") String firstname,
            @Param("lastname") String lastname,
            @Param("documentType") DocumentType documentType,
            @Param("documentNumber") String documentNumber
    );

}