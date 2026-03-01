package co.duvan.loan.domain.model;

public record LoanEvent(
        Long copyId,
        String eventType
) {}