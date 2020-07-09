package fr.pala.accounting.transaction.exception;

public class AuthenticationFailedException extends RuntimeException {
    public AuthenticationFailedException() {
        super();
    }

    public AuthenticationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthenticationFailedException(String message) {
        super(message);
    }

    public AuthenticationFailedException(Throwable cause) {
        super(cause);
    }
}