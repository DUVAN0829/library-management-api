package co.duvan.loan.application.ports.output;

import co.duvan.loan.domain.model.LoanEvent;

public interface LoanEventPublisherPort {

    void publish(LoanEvent event);

}