package co.duvan.copy.infrastructure.adapters.input.messaging;

public record LoanEvent(Long copyId, String eventType) {
}