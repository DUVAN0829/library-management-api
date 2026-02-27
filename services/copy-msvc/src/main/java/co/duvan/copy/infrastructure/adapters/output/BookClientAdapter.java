package co.duvan.copy.infrastructure.adapters.output;

import co.duvan.copy.application.ports.output.BookClientPort;
import co.duvan.copy.application.ports.output.dto.BookClientResponse;
import co.duvan.copy.infrastructure.adapters.output.client.BookClientFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookClientAdapter implements BookClientPort {

    private final BookClientFeign clientFeign;

    @Override
    public BookClientResponse findBookById(Long id, String token) {
        return clientFeign.getBookById(id);
    }

}
