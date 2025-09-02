package business;

import models.KolPlatForm;
import models.KolRegister;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit tests for KolPlatForms business class
 * @author zzzdi
 */
public class KolPlatFormsTest {
    
    private KolPlatForms kolPlatForms;
    private String testCsvFile;
    
    @TempDir
    Path tempDir;
    
    @BeforeEach
    void setUp() throws IOException {
        testCsvFile = tempDir.resolve("test_platforms.csv").toString();
        
        // Create a test CSV file
        File csvFile = new File(testCsvFile);
        FileWriter writer = new FileWriter(csvFile);
        writer.write("Code,Platform,Description\n");
        writer.write("TK01,TikTok,Short-form video platform\n");
        writer.write("FB01,Facebook,Social networking platform\n");
        writer.write("IG01,Instagram,Photo and video sharing\n");
        writer.write("YT01,YouTube,Video sharing platform\n");
        writer.close();
        
        kolPlatForms = new KolPlatForms(testCsvFile);
    }
    
    @Test
    @DisplayName("Test reading platforms from CSV file")
    void testReadFromFile() {
        assertEquals(4, kolPlatForms.size());
        assertTrue(kolPlatForms.containsKey("TK01"));
        assertTrue(kolPlatForms.containsKey("FB01"));
        assertTrue(kolPlatForms.containsKey("IG01"));
        assertTrue(kolPlatForms.containsKey("YT01"));
    }
    
    @Test
    @DisplayName("Test dataToObject method with valid data")
    void testDataToObjectValid() {
        KolPlatForm platform = kolPlatForms.dataToObject("TW01,Twitter,Microblogging platform");
        assertNotNull(platform);
        assertEquals("TW01", platform.getCode());
        assertEquals("Twitter", platform.getPlatform());
        assertEquals("Microblogging platform", platform.getDescription());
    }
    
    @Test
    @DisplayName("Test dataToObject method with header line")
    void testDataToObjectHeader() {
        KolPlatForm platform = kolPlatForms.dataToObject("Code,Platform,Description");
        assertNull(platform);
    }
    
    @Test
    @DisplayName("Test dataToObject method with invalid data")
    void testDataToObjectInvalid() {
        KolPlatForm platform = kolPlatForms.dataToObject("InvalidData");
        assertNull(platform);
        
        platform = kolPlatForms.dataToObject("");
        assertNull(platform);
    }
    
    @Test
    @DisplayName("Test searchById with existing code")
    void testSearchByIdExists() {
        KolPlatForm found = kolPlatForms.searchById("TK01");
        assertNotNull(found);
        assertEquals("TK01", found.getCode());
        assertEquals("TikTok", found.getPlatform());
    }
    
    @Test
    @DisplayName("Test searchById with case insensitive")
    void testSearchByIdCaseInsensitive() {
        KolPlatForm found = kolPlatForms.searchById("tk01");
        assertNotNull(found);
        assertEquals("TK01", found.getCode());
        
        found = kolPlatForms.searchById("FB01");
        assertNotNull(found);
        assertEquals("FB01", found.getCode());
    }
    
    @Test
    @DisplayName("Test searchById with non-existing code")
    void testSearchByIdNotExists() {
        KolPlatForm found = kolPlatForms.searchById("XX99");
        assertNull(found);
    }
    
    @Test
    @DisplayName("Test searchById with null code")
    void testSearchByIdNull() {
        KolPlatForm found = kolPlatForms.searchById(null);
        assertNull(found);
    }
    
    @Test
    @DisplayName("Test searchById with spaces")
    void testSearchByIdWithSpaces() {
        KolPlatForm found = kolPlatForms.searchById("  TK01  ");
        assertNotNull(found);
        assertEquals("TK01", found.getCode());
    }
    
    @Test
    @DisplayName("Test showCategoryByPlatform statistics")
    void testShowCategoryByPlatform() {
        // Create test registrations
        List<KolRegister> registers = new ArrayList<>();
        registers.add(new KolRegister("BT123456", "User1", "0987654321", 
                                      "user1@test.com", "TK01", 1500000, 25));
        registers.add(new KolRegister("FS999999", "User2", "0912345678", 
                                      "user2@test.com", "TK01", 500000, 20));
        registers.add(new KolRegister("BC111111", "User3", "0934567890", 
                                      "user3@test.com", "FB01", 2000000, 30));
        registers.add(new KolRegister("GM222222", "User4", "0956789012", 
                                      "user4@test.com", "IG01", 750000, 15));
        
        // This method prints to console, so we just verify it doesn't throw
        assertDoesNotThrow(() -> kolPlatForms.showCategoryByPlatform(registers));
    }
    
    @Test
    @DisplayName("Test showCategoryByPlatform with empty list")
    void testShowCategoryByPlatformEmpty() {
        List<KolRegister> emptyList = new ArrayList<>();
        assertDoesNotThrow(() -> kolPlatForms.showCategoryByPlatform(emptyList));
    }
    
    @Test
    @DisplayName("Test showCategoryByPlatform with invalid platform codes")
    void testShowCategoryByPlatformInvalidCodes() {
        List<KolRegister> registers = new ArrayList<>();
        registers.add(new KolRegister("BT123456", "User1", "0987654321", 
                                      "user1@test.com", "INVALID", 1500000, 25));
        
        // Should handle gracefully
        assertDoesNotThrow(() -> kolPlatForms.showCategoryByPlatform(registers));
    }
    
    @Test
    @DisplayName("Test file not found scenario")
    void testFileNotFound() {
        String nonExistentFile = tempDir.resolve("non_existent.csv").toString();
        KolPlatForms emptyPlatforms = new KolPlatForms(nonExistentFile);
        assertEquals(0, emptyPlatforms.size());
    }
    
    @Test
    @DisplayName("Test adding new platform manually")
    void testAddNewPlatform() {
        KolPlatForm newPlatform = new KolPlatForm("LI01", "LinkedIn", "Professional network");
        kolPlatForms.put("LI01", newPlatform);
        
        assertEquals(5, kolPlatForms.size());
        KolPlatForm found = kolPlatForms.searchById("LI01");
        assertNotNull(found);
        assertEquals("LinkedIn", found.getPlatform());
    }
}
