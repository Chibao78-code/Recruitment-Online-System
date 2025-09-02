import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

/**
 * Test suite to run all unit tests for the Recruitment Online System
 * @author zzzdi
 */
@Suite
@SuiteDisplayName("Recruitment Online System - All Tests")
@SelectPackages({
    "models",
    "utils", 
    "business"
})
public class AllTestsSuite {
    // This class will automatically discover and run all tests in the specified packages
}
