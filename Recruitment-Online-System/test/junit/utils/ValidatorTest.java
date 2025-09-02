package utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;
import exceptions.ValidationException;
import constants.AppConstants;

/**
 * Unit tests for Validator utility class
 * @author zzzdi
 */
public class ValidatorTest {
    
    // Tests for validateKolId
    @ParameterizedTest
    @DisplayName("Test valid KOL IDs")
    @ValueSource(strings = {"BT123456", "bt123456", "FS999999", "BC000001", "GM123456", "TL987654"})
    void testValidKolIds(String id) {
        assertDoesNotThrow(() -> Validator.validateKolId(id));
    }
    
    @ParameterizedTest
    @DisplayName("Test invalid KOL IDs")
    @ValueSource(strings = {"XX123456", "BT12345", "BT1234567", "123456", "BTABCDEF", "", "   "})
    void testInvalidKolIds(String id) {
        assertThrows(ValidationException.class, () -> Validator.validateKolId(id));
    }
    
    @Test
    @DisplayName("Test null KOL ID")
    void testNullKolId() {
        assertThrows(ValidationException.class, () -> Validator.validateKolId(null));
    }
    
    // Tests for validateName
    @ParameterizedTest
    @DisplayName("Test valid names")
    @ValueSource(strings = {"John Doe", "Nguyen Van A", "Maria", "Long Name With Many Characters"})
    void testValidNames(String name) {
        assertDoesNotThrow(() -> Validator.validateName(name));
    }
    
    @ParameterizedTest
    @DisplayName("Test invalid names")
    @ValueSource(strings = {"John", "", "   ", "This name is way too long and exceeds thirty characters limit"})
    void testInvalidNames(String name) {
        assertThrows(ValidationException.class, () -> Validator.validateName(name));
    }
    
    @Test
    @DisplayName("Test null name")
    void testNullName() {
        assertThrows(ValidationException.class, () -> Validator.validateName(null));
    }
    
    // Tests for validatePhone
    @ParameterizedTest
    @DisplayName("Test valid Vietnamese phone numbers")
    @ValueSource(strings = {"0987654321", "0912345678", "0345678901", "0567890123", "0789012345", 
                          "0823456789", "0934567890", "0978901234", "0989012345", "0993456789"})
    void testValidPhoneNumbers(String phone) {
        assertDoesNotThrow(() -> Validator.validatePhone(phone));
    }
    
    @ParameterizedTest
    @DisplayName("Test invalid phone numbers")
    @ValueSource(strings = {"0123456789", "098765432", "09876543210", "1234567890", "", "   ", "abcdefghij"})
    void testInvalidPhoneNumbers(String phone) {
        assertThrows(ValidationException.class, () -> Validator.validatePhone(phone));
    }
    
    @Test
    @DisplayName("Test null phone number")
    void testNullPhone() {
        assertThrows(ValidationException.class, () -> Validator.validatePhone(null));
    }
    
    // Tests for validateEmail
    @ParameterizedTest
    @DisplayName("Test valid emails")
    @ValueSource(strings = {"test@example.com", "user.name@domain.co", "user+tag@example.org", 
                          "123@test.vn", "a@b.cc"})
    void testValidEmails(String email) {
        assertDoesNotThrow(() -> Validator.validateEmail(email));
    }
    
    @ParameterizedTest
    @DisplayName("Test invalid emails")
    @ValueSource(strings = {"invalid", "@example.com", "user@", "user@.com", "", "   ", 
                          "user@domain", "user space@example.com"})
    void testInvalidEmails(String email) {
        assertThrows(ValidationException.class, () -> Validator.validateEmail(email));
    }
    
    @Test
    @DisplayName("Test null email")
    void testNullEmail() {
        assertThrows(ValidationException.class, () -> Validator.validateEmail(null));
    }
    
    // Tests for validateFollowerCount
    @ParameterizedTest
    @DisplayName("Test valid follower counts")
    @ValueSource(ints = {1, 100, 10000, 1000000, 999999999})
    void testValidFollowerCounts(int count) {
        assertDoesNotThrow(() -> Validator.validateFollowerCount(count));
    }
    
    @ParameterizedTest
    @DisplayName("Test invalid follower counts")
    @ValueSource(ints = {0, -1, -100, 1000000001})
    void testInvalidFollowerCounts(int count) {
        assertThrows(ValidationException.class, () -> Validator.validateFollowerCount(count));
    }
    
    // Tests for validatePlatformCode
    @ParameterizedTest
    @DisplayName("Test valid platform codes")
    @ValueSource(strings = {"TK01", "FB01", "IG99", "YT00", "AB12"})
    void testValidPlatformCodes(String code) {
        assertDoesNotThrow(() -> Validator.validatePlatformCode(code));
    }
    
    @ParameterizedTest
    @DisplayName("Test invalid platform codes")
    @ValueSource(strings = {"tk01", "T01", "TK1", "TK001", "1234", "", "   ", "TKAB"})
    void testInvalidPlatformCodes(String code) {
        assertThrows(ValidationException.class, () -> Validator.validatePlatformCode(code));
    }
    
    @Test
    @DisplayName("Test null platform code")
    void testNullPlatformCode() {
        assertThrows(ValidationException.class, () -> Validator.validatePlatformCode(null));
    }
    
    // Tests for calculateCommissionRate
    @Test
    @DisplayName("Test commission rate calculation")
    void testCalculateCommissionRate() {
        // Below threshold
        assertEquals(AppConstants.NORMAL_COMMISSION_RATE, 
                    Validator.calculateCommissionRate(999999));
        assertEquals(AppConstants.NORMAL_COMMISSION_RATE, 
                    Validator.calculateCommissionRate(1000000));
        
        // Above threshold
        assertEquals(AppConstants.HIGH_COMMISSION_RATE, 
                    Validator.calculateCommissionRate(1000001));
        assertEquals(AppConstants.HIGH_COMMISSION_RATE, 
                    Validator.calculateCommissionRate(5000000));
    }
    
    // Tests for formatName
    @ParameterizedTest
    @DisplayName("Test name formatting")
    @CsvSource({
        "john doe, John Doe",
        "JANE SMITH, Jane Smith",
        "mArY jOhNsOn, Mary Johnson",
        "single, Single",
        "'  spaces  around  ', Spaces Around",
        "'', ''"
    })
    void testFormatName(String input, String expected) {
        assertEquals(expected, Validator.formatName(input));
    }
    
    @Test
    @DisplayName("Test formatName with null")
    void testFormatNameNull() {
        assertEquals("", Validator.formatName(null));
    }
    
    // Tests for normalizeId
    @ParameterizedTest
    @DisplayName("Test ID normalization")
    @CsvSource({
        "bt123456, BT123456",
        "FS999999, FS999999",
        "bc000001, BC000001",
        "GM123456, GM123456",
        "tl987654, TL987654",
        "a, A",
        "'', ''"
    })
    void testNormalizeId(String input, String expected) {
        assertEquals(expected, Validator.normalizeId(input));
    }
    
    @Test
    @DisplayName("Test normalizeId with null")
    void testNormalizeIdNull() {
        assertEquals("", Validator.normalizeId(null));
    }
    
    // Integration tests
    @Test
    @DisplayName("Test validation exception messages")
    void testValidationExceptionMessages() {
        ValidationException ex;
        
        // Test KOL ID error message
        ex = assertThrows(ValidationException.class, () -> Validator.validateKolId(""));
        assertEquals("KOL ID cannot be empty", ex.getMessage());
        
        ex = assertThrows(ValidationException.class, () -> Validator.validateKolId("INVALID"));
        assertEquals("KOL ID must start with BT/FS/BC/GM/TL followed by 6 digits", ex.getMessage());
        
        // Test name error message
        ex = assertThrows(ValidationException.class, () -> Validator.validateName(""));
        assertEquals("Name cannot be empty", ex.getMessage());
        
        ex = assertThrows(ValidationException.class, () -> Validator.validateName("abc"));
        assertEquals("Name must be between 5 and 30 characters", ex.getMessage());
        
        // Test phone error message
        ex = assertThrows(ValidationException.class, () -> Validator.validatePhone(""));
        assertEquals("Phone number cannot be empty", ex.getMessage());
        
        ex = assertThrows(ValidationException.class, () -> Validator.validatePhone("123"));
        assertEquals("Invalid Vietnamese phone number format", ex.getMessage());
        
        // Test email error message
        ex = assertThrows(ValidationException.class, () -> Validator.validateEmail(""));
        assertEquals("Email cannot be empty", ex.getMessage());
        
        ex = assertThrows(ValidationException.class, () -> Validator.validateEmail("invalid"));
        assertEquals("Invalid email format", ex.getMessage());
        
        // Test follower count error message
        ex = assertThrows(ValidationException.class, () -> Validator.validateFollowerCount(0));
        assertEquals("Follower count must be greater than 0", ex.getMessage());
        
        ex = assertThrows(ValidationException.class, () -> Validator.validateFollowerCount(1000000001));
        assertEquals("Follower count seems unrealistic", ex.getMessage());
        
        // Test platform code error message
        ex = assertThrows(ValidationException.class, () -> Validator.validatePlatformCode(""));
        assertEquals("Platform code cannot be empty", ex.getMessage());
        
        ex = assertThrows(ValidationException.class, () -> Validator.validatePlatformCode("INVALID"));
        assertEquals("Platform code must be 2 letters followed by 2 digits", ex.getMessage());
    }
}
