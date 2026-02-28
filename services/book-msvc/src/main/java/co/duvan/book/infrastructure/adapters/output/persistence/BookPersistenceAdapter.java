package co.duvan.book.infrastructure.adapters.output.persistence;

import co.duvan.book.application.ports.output.BookRepositoryPort;
import co.duvan.book.domain.model.Book;
import co.duvan.book.domain.model.BookFilterQuery;
import co.duvan.book.infrastructure.adapters.output.persistence.entity.BookEntity;
import co.duvan.book.infrastructure.adapters.output.persistence.mapper.BookPersistenceMapper;
import co.duvan.book.infrastructure.adapters.output.persistence.repository.BookRepository;
import co.duvan.book.infrastructure.adapters.output.persistence.repository.BookSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookPersistenceAdapter implements BookRepositoryPort {

    private final BookPersistenceMapper bookPersistenceMapper;
    private final BookRepository repository;

    @Override
    public Optional<Book> findById(Long id) {
        return repository.findByBookId(id).map(bookPersistenceMapper::toBook);
    }

    @Override
    public Book save(Book book) {
        return bookPersistenceMapper.toBook(repository.save(bookPersistenceMapper.toBookEntity(book)));
    }

    @Override
    public List<Book> findAll() {
        return bookPersistenceMapper.toListBook(repository.findAll());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Book> findWithFilters(BookFilterQuery filter) {

        Specification<BookEntity> spec = Specification
                .where(BookSpecification.hasTitle(filter.getTitle()))
                .and(BookSpecification.hasCategory(filter.getCategory()))
                .and(BookSpecification.hasAuthor(filter.getAuthor()));

        return repository.findAll(spec)
                .stream()
                .map(bookPersistenceMapper::toBook)
                .collect(Collectors.toList());

    }

}