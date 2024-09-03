package com.assignment.records.validation;

import com.assignment.records.model.TransactionRecord;

/**
 * RuleEndCurrentBalance is a class for defining the validation rule for the end balance of a transaction record.
 */
public class RuleEndCurrentBalance implements ValidationRule {

    @Override
    public boolean validate(TransactionRecord transactionRecord) {
        return transactionRecord.calculatedBalance().compareTo(transactionRecord.endBalance()) == 0;
    }
}
