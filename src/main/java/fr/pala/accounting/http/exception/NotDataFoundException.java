package fr.pala.accounting.http.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class NotDataFoundException extends RuntimeException {
    public NotDataFoundException() {
        super();
    }
    public NotDataFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public NotDataFoundException(String message) {
        super(message);
    }
    public NotDataFoundException(Throwable cause) {
        super(cause);
    }
}