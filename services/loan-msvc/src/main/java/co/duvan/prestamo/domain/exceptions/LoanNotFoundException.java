package co.duvan.prestamo.domain.exceptions;

public class LoanNotFoundException extends RuntimeException {

    public LoanNotFoundException() {
    }

    public LoanNotFoundException(String message) {
        super(message);
    }

}
