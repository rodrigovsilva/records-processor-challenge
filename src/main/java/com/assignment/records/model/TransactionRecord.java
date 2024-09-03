package com.assignment.records.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * TransactionRecord is a public record class that represents a transaction record.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record TransactionRecord(@JsonProperty("reference") String reference,
                                @JsonProperty("accountNumber") String accountNumber,
                                @JsonProperty("description") String description,
                                @JsonProperty("startBalance") BigDecimal startBalance,
                                @JsonProperty("mutation") BigDecimal mutation,
                                @JsonProperty("endBalance") BigDecimal endBalance) {

    /**
     * This method calculates the balance of the transaction record.
     *
     * @return the balance of the transaction record
     */
    public BigDecimal calculatedBalance() {
        return startBalance().add(mutation());
    }
}

