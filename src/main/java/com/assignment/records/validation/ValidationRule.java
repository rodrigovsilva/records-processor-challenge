package com.assignment.records.validation;

import com.assignment.records.model.TransactionRecord;

/**
 * ValidationRule is an enum class for defining validation rules for transaction records.
 */
public interface ValidationRule {
    /**
     * This is an abstract method that the enums should implement to be a valid validation rule.
     *
     * @param record the list of transaction records to validate
     * @return true if the validation rule is satisfied, false otherwise
     */
    boolean validate(TransactionRecord record);
}
