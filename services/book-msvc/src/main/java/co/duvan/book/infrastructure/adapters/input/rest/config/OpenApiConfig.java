package co.duvan.book.infrastructure.adapters.input.rest.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Book Management API",
                version = "1.0.0",
                description = """
                        REST adapter responsible for core book management operations.

                        This interface handles create, update, delete, and retrieve-by-id actions,
                        while advanced querying capabilities are provided through a separate
                        GraphQL adapter.

                        The application is built following Hexagonal Architecture (Ports & Adapters),
                        promoting a clear separation between domain, application, and infrastructure layers.
                        """
        )
)
public class OpenApiConfig {
}