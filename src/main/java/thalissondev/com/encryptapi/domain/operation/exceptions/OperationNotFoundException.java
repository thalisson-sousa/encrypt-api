package thalissondev.com.encryptapi.domain.operation.exceptions;

public class OperationNotFoundException extends RuntimeException{

    public OperationNotFoundException(Long id) {
        super("Operation Not Found with ID " + id);
    }
}
