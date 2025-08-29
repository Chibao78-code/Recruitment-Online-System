package constants;

/**
 * Application constants for KOL Recruitment System
 * @author zzzdi
 */
public class AppConstants {
    
    // File paths
    public static final String PLATFORM_FILE = "test/KOLList.csv";
    public static final String REGISTER_FILE = "kol_registrations.dat";
    
    // Menu Options
    public static final int REGISTER_KOL = 1;
    public static final int UPDATE_REGISTER = 2;
    public static final int DISPLAY_REGISTERS = 3;
    public static final int DELETE_REGISTER = 4;
    public static final int SEARCH_BY_NAME = 5;
    public static final int FILTER_BY_CATEGORY = 6;
    public static final int DISPLAY_STATISTICS = 7;
    public static final int SAVE_DATA = 8;
    public static final int EXIT = 9;
    
    // Sub-menu options
    public static final int CONTINUE_ACTION = 1;
    public static final int RETURN_MAIN_MENU = 2;
    
    // Commission rates
    public static final int HIGH_COMMISSION_RATE = 25;
    public static final int NORMAL_COMMISSION_RATE = 20;
    public static final int FOLLOWER_THRESHOLD = 1000000;
    
    // Platform codes
    public static final String[] VALID_PLATFORM_PREFIXES = {"BT", "FS", "BC", "GM", "TL"};
    
    // Validation patterns
    public static final String ID_PATTERN = "^(BT|bt|FS|fs|BC|bc|GM|gm|TL|tl)\\d{6}$";
    public static final String NAME_PATTERN = "^.{5,30}$";
    public static final String PHONE_PATTERN = "^0(?:3[2-9]|5[2689]|7[06-9]|8[1-9]|9[0-9])\\d{7}$";
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+.-]+@[a-zA-Z.-]+\\.[a-zA-Z]{2,}$";
    
    // Messages
    public static final String NO_KOLS_REGISTERED = "No KOLs have registered yet";
    public static final String DUPLICATE_ID = "Duplicate KOL ID";
    public static final String KOL_NOT_FOUND = "This KOL has not registered yet";
    public static final String SAVE_SUCCESS = "Data saved successfully!";
    public static final String DELETE_SUCCESS = "The registration has been successfully deleted.";
    public static final String DELETE_CANCELLED = "The registration has NOT been deleted.";
    public static final String INVALID_CHOICE = "Invalid choice. Please try again.";
}
