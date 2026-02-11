package co.duvan.user.domain.exceptions;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }

}
