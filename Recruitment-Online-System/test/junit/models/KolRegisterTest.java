package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for KolRegister model class
 * @author zzzdi
 */
public class KolRegisterTest {
    
    private KolRegister kolRegister;
    
    @BeforeEach
    void setUp() {
        kolRegister = new KolRegister("bt123456", "John Doe", "0987654321", 
                                      "john@example.com", "TK01", 1500000, 25);
    }
    
    @Test
    @DisplayName("Test constructor with all parameters")
    void testConstructorWithParameters() {
        assertNotNull(kolRegister);
        assertEquals("BT123456", kolRegister.getId());
        assertEquals("John Doe", kolRegister.getName());
        assertEquals("0987654321", kolRegister.getPhoneNumber());
        assertEquals("john@example.com", kolRegister.getEmail());
        assertEquals("TK01", kolRegister.getPlatformCode());
        assertEquals(1500000, kolRegister.getFollowerCount());
        assertEquals(25, kolRegister.getCommissionRate());
    }
    
    @Test
    @DisplayName("Test default constructor")
    void testDefaultConstructor() {
        KolRegister emptyKol = new KolRegister();
        assertNotNull(emptyKol);
        assertNull(emptyKol.getName());
        assertNull(emptyKol.getPhoneNumber());
        assertNull(emptyKol.getEmail());
    }
    
    @Test
    @DisplayName("Test getId with uppercase conversion")
    void testGetIdUppercase() {
        kolRegister.setId("fs999999");
        assertEquals("FS999999", kolRegister.getId());
        
        kolRegister.setId("BC111111");
        assertEquals("BC111111", kolRegister.getId());
    }
    
    @Test
    @DisplayName("Test setters and getters")
    void testSettersAndGetters() {
        kolRegister.setName("Jane Smith");
        assertEquals("Jane Smith", kolRegister.getName());
        
        kolRegister.setPhoneNumber("0912345678");
        assertEquals("0912345678", kolRegister.getPhoneNumber());
        
        kolRegister.setEmail("jane@test.com");
        assertEquals("jane@test.com", kolRegister.getEmail());
        
        kolRegister.setPlatformCode("FB01");
        assertEquals("FB01", kolRegister.getPlatformCode());
        
        kolRegister.setFollowerCount(2000000);
        assertEquals(2000000, kolRegister.getFollowerCount());
        
        kolRegister.setCommissionRate(30);
        assertEquals(30, kolRegister.getCommissionRate());
    }
    
    @Test
    @DisplayName("Test equals method with same ID")
    void testEqualsWithSameId() {
        KolRegister other = new KolRegister("BT123456", "Different Name", "0999999999",
                                           "different@email.com", "IG01", 500000, 20);
        assertTrue(kolRegister.equals(other));
    }
    
    @Test
    @DisplayName("Test equals method with different ID")
    void testEqualsWithDifferentId() {
        KolRegister other = new KolRegister("FS654321", "John Doe", "0987654321",
                                           "john@example.com", "TK01", 1500000, 25);
        assertFalse(kolRegister.equals(other));
    }
    
    @Test
    @DisplayName("Test equals with case insensitive ID")
    void testEqualsCaseInsensitive() {
        kolRegister.setId("BT123456");
        KolRegister other = new KolRegister();
        other.setId("bt123456");
        assertTrue(kolRegister.equals(other));
    }
    
    @Test
    @DisplayName("Test equals with null and different type")
    void testEqualsEdgeCases() {
        assertFalse(kolRegister.equals(null));
        assertFalse(kolRegister.equals("Not a KolRegister"));
        assertTrue(kolRegister.equals(kolRegister));
    }
    
    @Test
    @DisplayName("Test hashCode method")
    void testHashCode() {
        kolRegister.setId("BT123456");
        KolRegister other = new KolRegister();
        other.setId("bt123456");
        assertEquals(kolRegister.hashCode(), other.hashCode());
        
        other.setId("FS999999");
        assertNotEquals(kolRegister.hashCode(), other.hashCode());
    }
    
    @Test
    @DisplayName("Test formatString method")
    void testFormatString() {
        assertEquals("John Doe", kolRegister.formatString("john doe"));
        assertEquals("Jane Mary Smith", kolRegister.formatString("JANE MARY SMITH"));
        assertEquals("Test", kolRegister.formatString("test"));
        assertEquals("", kolRegister.formatString(""));
    }
    
    @Test
    @DisplayName("Test toString method")
    void testToString() {
        String result = kolRegister.toString();
        assertTrue(result.contains("id=bt123456"));
        assertTrue(result.contains("name=John Doe"));
        assertTrue(result.contains("phoneNumber=0987654321"));
        assertTrue(result.contains("email=john@example.com"));
        assertTrue(result.contains("platformCode=TK01"));
        assertTrue(result.contains("followerCount=1500000"));
        assertTrue(result.contains("rate=25"));
    }
}
