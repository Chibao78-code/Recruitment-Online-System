package enums;

/**
 * Enum for KOL categories
 * @author zzzdi
 */
public enum KolCategory {
    BEAUTY("BT", "Beauty & Cosmetics"),
    FASHION("FS", "Fashion & Style"),
    BROADCAST("BC", "Broadcasting & Entertainment"),
    GAMING("GM", "Gaming & Esports"),
    TRAVEL("TL", "Travel & Lifestyle");
    
    private final String code;
    private final String description;
    
    KolCategory(String code, String description) {
        this.code = code;
        this.description = description;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getDescription() {
        return description;
    }
    
    /**
     * Get category by code
     */
    public static KolCategory getByCode(String code) {
        if (code == null) return null;
        
        String upperCode = code.toUpperCase();
        for (KolCategory category : values()) {
            if (category.code.equals(upperCode)) {
                return category;
            }
        }
        return null;
    }
    
    /**
     * Check if code is valid
     */
    public static boolean isValidCode(String code) {
        return getByCode(code) != null;
    }
    
    /**
     * Get all valid codes as string
     */
    public static String getAllCodes() {
        StringBuilder codes = new StringBuilder();
        for (KolCategory category : values()) {
            if (codes.length() > 0) {
                codes.append(", ");
            }
            codes.append(category.code);
        }
        return codes.toString();
    }
}
