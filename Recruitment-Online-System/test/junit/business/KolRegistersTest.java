package business;

import models.KolRegister;
import models.KolPlatForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.nio.file.Path;
import java.util.List;

/**
 * Unit tests for KolRegisters business class
 * @author zzzdi
 */
public class KolRegistersTest {
    
    private KolRegisters kolRegisters;
    private KolPlatForms kolPlatForms;
    private KolRegister testKol1;
    private KolRegister testKol2;
    private KolRegister testKol3;
    
    @TempDir
    Path tempDir;
    
    @BeforeEach
    void setUp() {
        String testFile = tempDir.resolve("test_kols.dat").toString();
        kolRegisters = new KolRegisters(testFile);
        
        // Create test platform data
        String platformFile = tempDir.resolve("platforms.csv").toString();
        kolPlatForms = new KolPlatForms(platformFile);
        kolPlatForms.put("TK01", new KolPlatForm("TK01", "TikTok", "Short video platform"));
        kolPlatForms.put("FB01", new KolPlatForm("FB01", "Facebook", "Social network"));
        
        // Create test KOL registrations
        testKol1 = new KolRegister("BT123456", "John Doe", "0987654321", 
                                   "john@test.com", "TK01", 1500000, 25);
        testKol2 = new KolRegister("FS999999", "Jane Smith", "0912345678", 
                                   "jane@test.com", "FB01", 500000, 20);
        testKol3 = new KolRegister("BC111111", "Bob Johnson", "0934567890", 
                                   "bob@test.com", "TK01", 2000000, 25);
    }
    
    @Test
    @DisplayName("Test adding new KOL registration")
    void testAddNew() {
        kolRegisters.addNew(testKol1);
        assertTrue(kolRegisters.contains(testKol1));
        assertEquals(1, kolRegisters.size());
        assertFalse(kolRegisters.toSave());
    }
    
    @Test
    @DisplayName("Test adding duplicate KOL")
    void testAddDuplicate() {
        kolRegisters.addNew(testKol1);
        
        // Try adding same KOL again
        kolRegisters.addNew(testKol1);
        assertEquals(1, kolRegisters.size()); // Should still be 1
    }
    
    @Test
    @DisplayName("Test isDuplication method")
    void testIsDuplication() {
        kolRegisters.addNew(testKol1);
        assertTrue(kolRegisters.isDuplicaition(testKol1));
        assertFalse(kolRegisters.isDuplicaition(testKol2));
    }
    
    @Test
    @DisplayName("Test update KOL registration")
    void testUpdate() {
        kolRegisters.addNew(testKol1);
        
        // Modify the KOL
        testKol1.setName("John Updated");
        testKol1.setEmail("john.updated@test.com");
        kolRegisters.update(testKol1);
        
        KolRegister retrieved = kolRegisters.searchById("BT123456");
        assertEquals("John Updated", retrieved.getName());
        assertEquals("john.updated@test.com", retrieved.getEmail());
        assertFalse(kolRegisters.toSave());
    }
    
    @Test
    @DisplayName("Test searchById method")
    void testSearchById() {
        kolRegisters.addNew(testKol1);
        kolRegisters.addNew(testKol2);
        
        KolRegister found = kolRegisters.searchById("BT123456");
        assertNotNull(found);
        assertEquals(testKol1, found);
        
        found = kolRegisters.searchById("FS999999");
        assertNotNull(found);
        assertEquals(testKol2, found);
        
        found = kolRegisters.searchById("NOTEXIST");
        assertNull(found);
    }
    
    @Test
    @DisplayName("Test searchById with case insensitive")
    void testSearchByIdCaseInsensitive() {
        kolRegisters.addNew(testKol1);
        
        KolRegister found = kolRegisters.searchById("bt123456");
        assertNotNull(found);
        assertEquals(testKol1, found);
    }
    
    @Test
    @DisplayName("Test delete KOL registration")
    void testDelete() {
        kolRegisters.addNew(testKol1);
        kolRegisters.addNew(testKol2);
        assertEquals(2, kolRegisters.size());
        
        kolRegisters.delete(testKol1);
        assertEquals(1, kolRegisters.size());
        assertFalse(kolRegisters.contains(testKol1));
        assertTrue(kolRegisters.contains(testKol2));
        assertFalse(kolRegisters.toSave());
    }
    
    @Test
    @DisplayName("Test filterByName method")
    void testFilterByName() {
        kolRegisters.addNew(testKol1);
        kolRegisters.addNew(testKol2);
        kolRegisters.addNew(testKol3);
        
        List<KolRegister> results = kolRegisters.filterByName("John");
        assertEquals(2, results.size());
        assertTrue(results.contains(testKol1));
        assertTrue(results.contains(testKol3));
        
        results = kolRegisters.filterByName("jane");
        assertEquals(1, results.size());
        assertTrue(results.contains(testKol2));
        
        results = kolRegisters.filterByName("NotFound");
        assertEquals(0, results.size());
    }
    
    @Test
    @DisplayName("Test filterByCategory method")
    void testFilterByCategory() {
        kolRegisters.addNew(testKol1);
        kolRegisters.addNew(testKol2);
        kolRegisters.addNew(testKol3);
        
        List<KolRegister> results = kolRegisters.filterByCategoty("BT");
        assertEquals(1, results.size());
        assertTrue(results.contains(testKol1));
        
        results = kolRegisters.filterByCategoty("bt");
        assertEquals(1, results.size());
        
        results = kolRegisters.filterByCategoty("XX");
        assertEquals(0, results.size());
    }
    
    @Test
    @DisplayName("Test getAll method")
    void testGetAll() {
        kolRegisters.addNew(testKol1);
        kolRegisters.addNew(testKol2);
        kolRegisters.addNew(testKol3);
        
        List<KolRegister> all = kolRegisters.getAll();
        assertEquals(3, all.size());
        assertTrue(all.contains(testKol1));
        assertTrue(all.contains(testKol2));
        assertTrue(all.contains(testKol3));
    }
    
    @Test
    @DisplayName("Test save state tracking")
    void testSaveStateTracking() {
        assertTrue(kolRegisters.toSave()); // Initially saved
        
        kolRegisters.addNew(testKol1);
        assertFalse(kolRegisters.toSave()); // Modified after add
        
        kolRegisters.saveToFile();
        assertTrue(kolRegisters.toSave()); // Saved after saveToFile
        
        kolRegisters.update(testKol1);
        assertFalse(kolRegisters.toSave()); // Modified after update
        
        kolRegisters.saveToFile();
        assertTrue(kolRegisters.toSave());
        
        kolRegisters.delete(testKol1);
        assertFalse(kolRegisters.toSave()); // Modified after delete
    }
    
    @Test
    @DisplayName("Test saveToFile creates file")
    void testSaveToFile() {
        kolRegisters.addNew(testKol1);
        kolRegisters.addNew(testKol2);
        
        kolRegisters.saveToFile();
        
        File file = new File(tempDir.resolve("test_kols.dat").toString());
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
        assertTrue(kolRegisters.toSave());
    }
}
