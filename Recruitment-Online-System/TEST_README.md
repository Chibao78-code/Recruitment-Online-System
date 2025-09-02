# Unit Testing Guide for Recruitment Online System

## Overview
This project includes comprehensive unit tests for all major components of the KOL Recruitment Online System.

## Test Structure

The test suite is organized into the following packages:

- **`test/junit/models/`** - Tests for model classes (KolRegister, KolPlatForm)
- **`test/junit/utils/`** - Tests for utility classes (Validator)
- **`test/junit/business/`** - Tests for business logic classes (KolRegisters, KolPlatForms)

## Test Coverage

### Model Classes
- **KolRegisterTest**: 13 test cases covering constructors, getters/setters, equals/hashCode, and formatting methods
- **KolPlatFormTest**: 11 test cases covering all model functionality

### Utility Classes
- **ValidatorTest**: 30+ test cases covering all validation methods with both valid and invalid inputs

### Business Logic Classes
- **KolRegistersTest**: 12 test cases covering CRUD operations, searching, filtering, and file persistence
- **KolPlatFormsTest**: 13 test cases covering CSV parsing, searching, and statistics generation

## Prerequisites

1. **Download JUnit Libraries**: Run the PowerShell script to download required libraries:
   ```powershell
   .\download-junit.ps1
   ```

2. **Compile the Source Code**: Ensure the main source code is compiled:
   ```bash
   ant compile
   ```

## Running Tests

### Using Apache Ant

1. **Run All Tests**:
   ```bash
   ant test
   ```

2. **Generate Test Report**:
   ```bash
   ant test-report
   ```
   The HTML report will be generated in `build/test-reports/html/`

3. **Clean Test Artifacts**:
   ```bash
   ant clean-tests
   ```

### Using Command Line (without Ant)

1. **Compile Tests**:
   ```bash
   javac -cp "lib/*;build/classes" -d build/test test/junit/**/*.java
   ```

2. **Run Tests**:
   ```bash
   java -cp "lib/*;build/classes;build/test" org.junit.platform.console.ConsoleLauncher --scan-classpath
   ```

## Test Conventions

### Naming Conventions
- Test classes end with `Test` (e.g., `KolRegisterTest`)
- Test methods use descriptive names with `@DisplayName` annotations
- Test methods follow the pattern: `test<MethodName><Scenario>`

### Test Patterns Used
- **@BeforeEach**: Setup common test data
- **@ParameterizedTest**: Test multiple inputs with same logic
- **@Test**: Individual test cases
- **@TempDir**: Create temporary directories for file operations

## Adding New Tests

To add new test cases:

1. Create a new test class in the appropriate package
2. Follow the existing naming conventions
3. Include comprehensive test cases for:
   - Normal/happy path scenarios
   - Edge cases
   - Error conditions
   - Null/empty inputs

## Test Best Practices

1. **Independence**: Each test should be independent and not rely on other tests
2. **Clarity**: Use descriptive test names and assertions
3. **Coverage**: Aim for high code coverage (>80%)
4. **Performance**: Keep tests fast and focused
5. **Maintainability**: Update tests when code changes

## Continuous Integration

For CI/CD pipelines, use:
```bash
ant clean compile test test-report
```

## Troubleshooting

### Common Issues

1. **ClassNotFoundException**: Ensure all JUnit libraries are in the `lib/` directory
2. **CompilationError**: Run `ant compile` before running tests
3. **FileNotFoundException**: Check that test data files exist in the expected locations

### Debug Mode

To run tests with debugging output:
```bash
java -cp "lib/*;build/classes;build/test" -Djunit.platform.output.capture.stdout=true org.junit.platform.console.ConsoleLauncher --scan-classpath
```

## Test Metrics

Current test statistics:
- Total test classes: 5
- Total test methods: 60+
- Code coverage: Estimated >75%
- Execution time: <5 seconds

## Contributing

When contributing new features:
1. Write tests first (TDD approach recommended)
2. Ensure all existing tests pass
3. Add new tests for new functionality
4. Update this README if test structure changes
