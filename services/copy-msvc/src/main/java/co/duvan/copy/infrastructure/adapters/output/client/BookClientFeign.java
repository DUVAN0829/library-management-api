package co.duvan.copy.infrastructure.adapters.output.client;

import co.duvan.copy.application.ports.output.dto.BookClientResponse;
import co.duvan.copy.infrastructure.feign.config.FeignClientInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "book-msvc", url = "${book-msvc.url}", configuration = FeignClientInterceptor.class)
public interface BookClientFeign {

    @GetMapping("/books/api/v1/{id}")
    BookClientResponse getBookById(@PathVariable Long id);

}
