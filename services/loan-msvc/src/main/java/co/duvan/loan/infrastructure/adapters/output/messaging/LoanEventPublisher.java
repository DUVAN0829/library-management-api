package co.duvan.loan.infrastructure.adapters.output.messaging;

import co.duvan.loan.application.ports.output.LoanEventPublisherPort;
import co.duvan.loan.domain.model.LoanEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoanEventPublisher implements LoanEventPublisherPort {

    private final StreamBridge streamBridge;

    @Override
    public void publish(LoanEvent event) {

        streamBridge.send("loan-events-out-0", event);

    }

}
