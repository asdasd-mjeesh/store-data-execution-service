package data_execution.data_execution.exception;

public class ServiceDoesntContainRequestedEntityException extends RuntimeException {

    public ServiceDoesntContainRequestedEntityException(String message) {
        super(message);
    }
}
