package co.duvan.copy.infrastructure.adapters.input.messaging;

import co.duvan.copy.application.ports.input.UpdateCopyUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoanEventConsumer {

    private final UpdateCopyUseCase updateCopyUseCase;

    @Bean
    public Consumer<LoanEvent> loanEvents() {
        return event -> {
            log.info("Evento recibido: {} para copyId: {}", event.eventType(), event.copyId());
            switch (event.eventType()) {
                case "LOAN_CREATED"   -> updateCopyUseCase.updateStatus(event.copyId(), "LOANED");
                case "LOAN_RETURNED"  -> updateCopyUseCase.updateStatus(event.copyId(), "AVAILABLE");
                default -> log.warn("Evento desconocido: {}", event.eventType());
            }
        };
    }

}
