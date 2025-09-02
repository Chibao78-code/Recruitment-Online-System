# Download JUnit 5 libraries
$libPath = ".\lib"

# JUnit Jupiter API
Invoke-WebRequest -Uri "https://repo1.maven.org/maven2/org/junit/jupiter/junit-jupiter-api/5.10.1/junit-jupiter-api-5.10.1.jar" -OutFile "$libPath\junit-jupiter-api-5.10.1.jar"

# JUnit Jupiter Engine
Invoke-WebRequest -Uri "https://repo1.maven.org/maven2/org/junit/jupiter/junit-jupiter-engine/5.10.1/junit-jupiter-engine-5.10.1.jar" -OutFile "$libPath\junit-jupiter-engine-5.10.1.jar"

# JUnit Platform Commons
Invoke-WebRequest -Uri "https://repo1.maven.org/maven2/org/junit/platform/junit-platform-commons/1.10.1/junit-platform-commons-1.10.1.jar" -OutFile "$libPath\junit-platform-commons-1.10.1.jar"

# JUnit Platform Engine
Invoke-WebRequest -Uri "https://repo1.maven.org/maven2/org/junit/platform/junit-platform-engine/1.10.1/junit-platform-engine-1.10.1.jar" -OutFile "$libPath\junit-platform-engine-1.10.1.jar"

# OpenTest4J
Invoke-WebRequest -Uri "https://repo1.maven.org/maven2/org/opentest4j/opentest4j/1.3.0/opentest4j-1.3.0.jar" -OutFile "$libPath\opentest4j-1.3.0.jar"

# Mockito for mocking
Invoke-WebRequest -Uri "https://repo1.maven.org/maven2/org/mockito/mockito-core/5.7.0/mockito-core-5.7.0.jar" -OutFile "$libPath\mockito-core-5.7.0.jar"

Write-Host "JUnit 5 libraries downloaded successfully!" -ForegroundColor Green
