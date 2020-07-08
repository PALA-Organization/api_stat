package fr.pala.accounting.transaction.exception;

public class GetTransactionException extends RuntimeException {
    public GetTransactionException() {
        super();
    }
    public GetTransactionException(String message, Throwable cause) {
        super(message, cause);
    }
    public GetTransactionException(String message) {
        super(message);
    }
    public GetTransactionException(Throwable cause) {
        super(cause);
    }
}