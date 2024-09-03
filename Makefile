# Variables
MVN = mvn -s settings.xml
TARGET_DIR = target
JAR = $(TARGET_DIR)/records-processor-1.0-SNAPSHOT-jar-with-dependencies.jar
MAIN_CLASS = com.assignment.RecordsProcessorApp

# Clean the project
.PHONY: clean
clean:
	$(MVN) clean

# Compile and package the application into a JAR
.PHONY: package
package:
	$(MVN) clean compile assembly:single

# Run the application with the specified file
.PHONY: run
run: package
	java -cp $(JAR) $(MAIN_CLASS) $(FILE)

# Run tests
.PHONY: tests
tests: clean package
	$(MVN) test
