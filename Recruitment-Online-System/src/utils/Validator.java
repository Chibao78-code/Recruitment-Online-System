package utils;

import constants.AppConstants;
import exceptions.ValidationException;

/**
 * Utility class for data validation
 * @author zzzdi
 */
public class Validator {
    
    /**
     * Validates KOL ID format
     */
    public static void validateKolId(String id) throws ValidationException {
        if (id == null || id.trim().isEmpty()) {
            throw new ValidationException("KOL ID cannot be empty");
        }
        
        if (!id.matches(AppConstants.ID_PATTERN)) {
            throw new ValidationException("KOL ID must start with BT/FS/BC/GM/TL followed by 6 digits");
        }
    }
    
    /**
     * Validates name format
     */
    public static void validateName(String name) throws ValidationException {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("Name cannot be empty");
        }
        
        if (!name.matches(AppConstants.NAME_PATTERN)) {
            throw new ValidationException("Name must be between 5 and 30 characters");
        }
    }
    
    /**
     * Validates phone number format
     */
    public static void validatePhone(String phone) throws ValidationException {
        if (phone == null || phone.trim().isEmpty()) {
            throw new ValidationException("Phone number cannot be empty");
        }
        
        if (!phone.matches(AppConstants.PHONE_PATTERN)) {
            throw new ValidationException("Invalid Vietnamese phone number format");
        }
    }
    
    /**
     * Validates email format
     */
    public static void validateEmail(String email) throws ValidationException {
        if (email == null || email.trim().isEmpty()) {
            throw new ValidationException("Email cannot be empty");
        }
        
        if (!email.matches(AppConstants.EMAIL_PATTERN)) {
            throw new ValidationException("Invalid email format");
        }
    }
    
    /**
     * Validates follower count
     */
    public static void validateFollowerCount(int count) throws ValidationException {
        if (count <= 0) {
            throw new ValidationException("Follower count must be greater than 0");
        }
        
        if (count > 1000000000) {
            throw new ValidationException("Follower count seems unrealistic");
        }
    }
    
    /**
     * Validates platform code
     */
    public static void validatePlatformCode(String code) throws ValidationException {
        if (code == null || code.trim().isEmpty()) {
            throw new ValidationException("Platform code cannot be empty");
        }
        
        // Check if code follows pattern (e.g., TK01, FB01)
        if (!code.matches("^[A-Z]{2}\\d{2}$")) {
            throw new ValidationException("Platform code must be 2 letters followed by 2 digits");
        }
    }
    
    /**
     * Determines commission rate based on follower count
     */
    public static int calculateCommissionRate(int followerCount) {
        return followerCount > AppConstants.FOLLOWER_THRESHOLD 
            ? AppConstants.HIGH_COMMISSION_RATE 
            : AppConstants.NORMAL_COMMISSION_RATE;
    }
    
    /**
     * Formats a string with proper capitalization
     */
    public static String formatName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "";
        }
        
        String[] parts = name.trim().split("\\s+");
        StringBuilder formatted = new StringBuilder();
        
        for (String part : parts) {
            if (part.length() > 0) {
                formatted.append(Character.toUpperCase(part.charAt(0)))
                        .append(part.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        
        return formatted.toString().trim();
    }
    
    /**
     * Normalizes ID to uppercase
     */
    public static String normalizeId(String id) {
        if (id == null) return "";
        
        // Make first 2 characters uppercase and keep the rest
        if (id.length() >= 2) {
            return id.substring(0, 2).toUpperCase() + id.substring(2);
        }
        return id.toUpperCase();
    }
}
