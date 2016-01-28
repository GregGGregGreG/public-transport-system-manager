package ua.telesens.ostapenko.auth.persistence.exception;

/**
 * @author root
 * @since 26.01.16
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User not found.");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
