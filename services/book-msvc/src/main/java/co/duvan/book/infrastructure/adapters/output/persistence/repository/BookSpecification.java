package co.duvan.book.infrastructure.adapters.output.persistence.repository;

import co.duvan.book.domain.enums.Category;
import co.duvan.book.infrastructure.adapters.output.persistence.entity.BookEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<BookEntity> hasTitle(String title) {
        return (root, query, cb) -> title == null ? null
                : cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<BookEntity> hasCategory(Category category) {
        return (root, query, cb) -> category == null ? null
                : cb.equal(root.get("category"), category);
    }

    public static Specification<BookEntity> hasAuthor(String author) {
        return (root, query, cb) -> {
            if (author == null) return null;
            // Join con la tabla book_authors
            Join<BookEntity, String> authorsJoin = root.join("authors", JoinType.INNER);
            query.distinct(true); //* evitar duplicados
            return cb.like(cb.lower(authorsJoin), "%" + author.toLowerCase() + "%");
        };
    }

}
