package exceptions;

/**
 * Exception for validation errors
 * @author zzzdi
 */
public class ValidationException extends KolSystemException {
    
    public ValidationException(String message) {
        super(message);
    }
}
