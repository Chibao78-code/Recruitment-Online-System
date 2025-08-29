package exceptions;

/**
 * Exception for data access errors
 * @author zzzdi
 */
public class DataAccessException extends KolSystemException {
    
    public DataAccessException(String message) {
        super(message);
    }
    
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
