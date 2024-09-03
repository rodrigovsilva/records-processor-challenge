package com.assignment.records.validation;

import com.assignment.records.model.TransactionRecord;

import java.util.HashSet;
import java.util.Set;

/**
 * RuleUniqueReference is a class for defining the validation rule for the uniqueness of the reference of a transaction record.
 */
public class RuleUniqueReference implements ValidationRule {

    private final Set<String> referenceSet = new HashSet<>();

    @Override
    public boolean validate(TransactionRecord transactionRecord) {
        String reference = transactionRecord.reference();
        return referenceSet.add(reference); // Add to set (returns false if already present)
    }
}

