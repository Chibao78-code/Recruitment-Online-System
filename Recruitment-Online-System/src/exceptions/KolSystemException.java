package exceptions;

/**
 * Base exception class for KOL System
 * @author zzzdi
 */
public class KolSystemException extends Exception {
    
    public KolSystemException(String message) {
        super(message);
    }
    
    public KolSystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
