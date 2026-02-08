package co.duvan.book.infrastructure.adapters.input.rest.mapper;

import co.duvan.book.domain.model.Book;
import co.duvan.book.infrastructure.adapters.input.rest.model.request.BookRequest;
import co.duvan.book.infrastructure.adapters.input.rest.model.response.BookResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookRestMapper {

    Book toBook(BookRequest bookRequest);

    BookResponse toBookResponse(Book book);

    List<BookResponse> toBookResponseList(List<Book> books);

}
