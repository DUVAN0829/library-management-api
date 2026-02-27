package co.duvan.copy.application.ports.output;

import co.duvan.copy.application.ports.output.dto.BookClientResponse;

public interface BookClientPort {

    BookClientResponse findBookById(Long id, String token);

}
