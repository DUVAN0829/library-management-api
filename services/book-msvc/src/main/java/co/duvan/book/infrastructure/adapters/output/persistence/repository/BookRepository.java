package co.duvan.book.infrastructure.adapters.output.persistence.repository;

import co.duvan.book.domain.enums.Category;
import co.duvan.book.infrastructure.adapters.output.persistence.entity.BookEntity;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@NullMarked
public interface BookRepository extends CrudRepository<BookEntity, Long>, JpaSpecificationExecutor<BookEntity> {

    @EntityGraph(attributePaths = "authors")
    Optional<BookEntity> findByBookId(Long id);

    @EntityGraph(attributePaths = "authors")
    List<BookEntity> findAll();

    @Query("""
                SELECT DISTINCT b FROM BookEntity b JOIN b.authors a
                WHERE (:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', CAST(:title AS string), '%')))
                AND (:category IS NULL OR b.category = :category)
                AND (:author IS NULL OR LOWER(a) LIKE LOWER(CONCAT('%', CAST(:author AS string), '%')))
            """)
    List<BookEntity> findWithFilters(
            @Param("title") String title,
            @Param("author") String author,
            @Param("category") Category category
    );

}