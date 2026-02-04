package co.duvan.book.infrastructure.adapters.output.persistence.repository;

import co.duvan.book.infrastructure.adapters.output.persistence.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<BookEntity, Long> {
}
