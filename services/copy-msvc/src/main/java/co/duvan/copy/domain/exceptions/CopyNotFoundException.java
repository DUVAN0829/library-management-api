package co.duvan.copy.domain.exceptions;

public class CopyNotFoundException extends RuntimeException {

    public CopyNotFoundException() {
        super();
    }

    public CopyNotFoundException(String message) {
        super(message);
    }

}
