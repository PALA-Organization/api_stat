package fr.pala.accounting.http.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalErrorException extends RuntimeException {
    public InternalErrorException() {
        super();
    }
    public InternalErrorException(String message, Throwable cause) {
        super(message, cause);
    }
    public InternalErrorException(String message) {
        super(message);
    }
    public InternalErrorException(Throwable cause) {
        super(cause);
    }
}