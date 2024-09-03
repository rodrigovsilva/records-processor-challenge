package com.assignment.records;

import com.assignment.records.model.TransactionRecord;
import com.assignment.records.validation.RuleEndCurrentBalance;
import com.assignment.records.validation.RuleUniqueReference;
import com.assignment.records.validation.ValidationRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * ReportInvalidRecordsFromParsedData is a class for reporting invalid records based on the validation rules.
 */
public class ReportInvalidRecordsFromParsedData implements ReportInvalidRecords {

    private static final Logger logger = LoggerFactory.getLogger(ReportInvalidRecordsFromParsedData.class);

    public ReportInvalidRecordsFromParsedData() {
    }

    /**
     * This method returns a list of invalid transactions based on the validation rules.
     *
     * @param transactionRecords the list of transaction records to validate
     * @return the list of invalid transactions
     */
    public List<TransactionRecord> on(List<TransactionRecord> transactionRecords) {
        List<TransactionRecord> invalidTransactions = new ArrayList<>();

        List<ValidationRule> validationRules = List.of(new RuleUniqueReference(), new RuleEndCurrentBalance());

        for (TransactionRecord record : transactionRecords) {
            for (ValidationRule rule : validationRules) {
                // If any rule is not satisfied, the record is invalid
                if (!rule.validate(record)) {
                    invalidTransactions.add(record);
                }
            }
        }

        return invalidTransactions;
    }
}
