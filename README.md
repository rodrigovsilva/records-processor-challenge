# Records Processor

Some Company Challenge

**Candidate**: Rodrigo Silva

## Description

Given a file with bank transaction records, create a validation service that reads the file and gives a report with the
invalid records.

The file could either be in CSV or JSON format. An example of each is included.

### Input

Each record in the input file contains the following fields:

#### Table 1. Record description

| Field                 | Description                                |
|-----------------------|--------------------------------------------|
| Transaction reference | A numeric value                            |
| Account number        | An IBAN                                    |
| Start Balance         | The starting balance in Euros              |
| Mutation              | Either and addition (+) or a deduction (-) |
| Description           | Free text                                  |
| End Balance           | The end balance in Euros                   |

### Output

There are two validations:

* all transaction references should be unique

* per record, the end balance needs to be correct given the start balance and mutation

At the end of the processing, a report needs to be created which will display both the transaction reference and
description of each of the failed records.

## Solution

The solution is implemented in Java 21. I have decided to create a Maven project to manage the dependencies and build
the jar file.

This Java application is a command-line application that reads a file with bank transaction records and validates the
records.

The file can be in CSV or JSON format. In case of different format, the application will throw an exception with cause
and error details.

The application will validate the records and create a report with the invalid records.

### Application structure design

```text
/records-processor
  ├── src
  │   ├── main
  │   │   ├── java
  │   │   │   └── com
  │   │   │       └── assignment
  │   │   │           └── RecordsProcessorApp.java                          -> Main class to run the application
  │   │   │           └── records                                           
  │   │   │               └── exceptions                                    -> Application exceptions
  │   │   │               └── model                                         -> Where the application models are stored
  │   │   │               └── validation                                    
  |   |   |                   └── RuleEndCurrentBalance.java                -> Rule to validate the end balance
  |   |   |                   └── RuleUniqueTransactionReference.java       -> Rule to validate the unique transaction reference
  |   |   |                   └── ValidationRule.java                       -> Interface to define the contract of validation rules
  |   |   |               └── FileReaderFactory.java                        -> Factory which creates the file processor 
  |   |   |               └── FileType.java                                 -> Enumeration to facilitates the file type identification
  |   |   |               └── GenerateReport.java                           -> Interface to define the contract of report generation
  |   |   |               └── GenerateReportWithInvalidTransactions.java    -> Implementation of the GenerateReport interface to generate report with invalid transactions
  |   |   |               └── ProcessRecords.java                           -> Interface to define the contract of records processing   
  |   |   |               └── ProcessRecordsFromFile.java                   -> Implementation of the ProcessRecords interface to process records from file  
  |   |   |               └── ReadRecords.java                              -> Interface to define the contract of records reading
  |   |   |               └── ReadRecordsFromCsv.java                       -> Implementation of the ReadRecords interface to read records from CSV file
  |   |   |               └── ReadRecordsFromJson.java                      -> Implementation of the ReadRecords interface to read records from JSON file
  |   |   |               └── ReportInvalidRecords.java                     -> Interface to define the contract of invalid records report
  |   |   |               └── ReportInvalidRecordsFromParsedData.java       -> Implementation of the ReportInvalidRecords interface to report invalid records from parsed data
  │   │   ├── resources                                                     
  |   |   |   └── logback.xml                                               -> Logback configuration file
```

### Application commands

#### Running application unit tests

The application has unit tests to validate the application rules and the file processing. Nearly 100% of the code is
covered by unit tests.

Run the following command to execute the unit tests:

```shell
make tests
```

After running the tests, the application will generate a report with testing results.

**Example:**

```shell
[INFO] Results:
[INFO] 
[INFO] Tests run: 22, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.891 s
[INFO] Finished at: 2024-09-03T22:55:23+02:00
[INFO] ------------------------------------------------------------------------
```

#### Running the application

For running the application, you can use the following command:

```shell
make run FILE=<input-file>
```

**Example:**

```shell
make run src/main/resources/records.csv
```

In case of missing or invalid file, the application will return an error in the logs.

#### Other commands

Other commands are available to facilitate the application execution. Please check `Makefile` for more details.
