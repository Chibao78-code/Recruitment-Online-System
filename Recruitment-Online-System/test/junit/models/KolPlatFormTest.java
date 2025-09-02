package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for KolPlatForm model class
 * @author zzzdi
 */
public class KolPlatFormTest {
    
    private KolPlatForm kolPlatForm;
    
    @BeforeEach
    void setUp() {
        kolPlatForm = new KolPlatForm("TK01", "TikTok", "Short-form video platform");
    }
    
    @Test
    @DisplayName("Test constructor with all parameters")
    void testConstructorWithParameters() {
        assertNotNull(kolPlatForm);
        assertEquals("TK01", kolPlatForm.getCode());
        assertEquals("TikTok", kolPlatForm.getPlatform());
        assertEquals("Short-form video platform", kolPlatForm.getDescription());
    }
    
    @Test
    @DisplayName("Test default constructor")
    void testDefaultConstructor() {
        KolPlatForm emptyPlatform = new KolPlatForm();
        assertNotNull(emptyPlatform);
        assertNull(emptyPlatform.getCode());
        assertNull(emptyPlatform.getPlatform());
        assertNull(emptyPlatform.getDescription());
    }
    
    @Test
    @DisplayName("Test setters and getters")
    void testSettersAndGetters() {
        kolPlatForm.setCode("FB01");
        assertEquals("FB01", kolPlatForm.getCode());
        
        kolPlatForm.setPlatform("Facebook");
        assertEquals("Facebook", kolPlatForm.getPlatform());
        
        kolPlatForm.setDescription("Social networking platform");
        assertEquals("Social networking platform", kolPlatForm.getDescription());
    }
    
    @Test
    @DisplayName("Test equals method with same code")
    void testEqualsWithSameCode() {
        KolPlatForm other = new KolPlatForm("TK01", "Different Name", "Different Description");
        assertTrue(kolPlatForm.equals(other));
    }
    
    @Test
    @DisplayName("Test equals method with different code")
    void testEqualsWithDifferentCode() {
        KolPlatForm other = new KolPlatForm("FB01", "TikTok", "Short-form video platform");
        assertFalse(kolPlatForm.equals(other));
    }
    
    @Test
    @DisplayName("Test equals with case insensitive code")
    void testEqualsCaseInsensitive() {
        kolPlatForm.setCode("TK01");
        KolPlatForm other = new KolPlatForm("tk01", "TikTok", "Description");
        assertTrue(kolPlatForm.equals(other));
    }
    
    @Test
    @DisplayName("Test equals with null and different type")
    void testEqualsEdgeCases() {
        assertFalse(kolPlatForm.equals(null));
        assertFalse(kolPlatForm.equals("Not a KolPlatForm"));
        assertTrue(kolPlatForm.equals(kolPlatForm));
    }
    
    @Test
    @DisplayName("Test toString method")
    void testToString() {
        String result = kolPlatForm.toString();
        assertTrue(result.contains("code=TK01"));
        assertTrue(result.contains("Platform=TikTok"));
        assertTrue(result.contains("Description=Short-form video platform"));
    }
    
    @Test
    @DisplayName("Test platform name edge cases")
    void testPlatformNameEdgeCases() {
        // Test with empty string
        kolPlatForm.setPlatform("");
        assertEquals("", kolPlatForm.getPlatform());
        
        // Test with spaces
        kolPlatForm.setPlatform("  YouTube  ");
        assertEquals("  YouTube  ", kolPlatForm.getPlatform());
        
        // Test with special characters
        kolPlatForm.setPlatform("Instagram/Reels");
        assertEquals("Instagram/Reels", kolPlatForm.getPlatform());
    }
    
    @Test
    @DisplayName("Test code validation patterns")
    void testCodePatterns() {
        // Valid codes
        kolPlatForm.setCode("TK01");
        assertEquals("TK01", kolPlatForm.getCode());
        
        kolPlatForm.setCode("FB99");
        assertEquals("FB99", kolPlatForm.getCode());
        
        kolPlatForm.setCode("IG01");
        assertEquals("IG01", kolPlatForm.getCode());
        
        // Edge cases - the setter doesn't validate, just stores
        kolPlatForm.setCode("INVALID");
        assertEquals("INVALID", kolPlatForm.getCode());
        
        kolPlatForm.setCode("");
        assertEquals("", kolPlatForm.getCode());
    }
}
