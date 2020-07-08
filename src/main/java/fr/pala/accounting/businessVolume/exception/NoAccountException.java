package fr.pala.accounting.businessVolume.exception;

public class NoAccountException extends RuntimeException {
    public NoAccountException() {
        super();
    }
    public NoAccountException(String message, Throwable cause) {
        super(message, cause);
    }
    public NoAccountException(String message) {
        super(message);
    }
    public NoAccountException(Throwable cause) {
        super(cause);
    }
}