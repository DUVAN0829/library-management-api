package co.duvan.book.infrastructure.adapters.output.persistence.mapper;

import co.duvan.book.domain.model.Book;
import co.duvan.book.infrastructure.adapters.output.persistence.entity.BookEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookPersistenceMapper {

    BookEntity toBookEntity(Book book);

    Book toBook(BookEntity bookEntity);

    List<Book> toListBook(List<BookEntity> bookEntityList);

}
