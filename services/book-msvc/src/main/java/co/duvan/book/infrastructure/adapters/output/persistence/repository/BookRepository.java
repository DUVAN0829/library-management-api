package co.duvan.book.infrastructure.adapters.output.persistence.repository;

import co.duvan.book.infrastructure.adapters.output.persistence.entity.BookEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookRepository extends CrudRepository<BookEntity, Long> {

    @EntityGraph(attributePaths = "authors")
    Optional<BookEntity> findByBookId(Long id);

}
