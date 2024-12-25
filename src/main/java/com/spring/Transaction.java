package com.spring;

import java.time.LocalDateTime;

public class Transaction {
    private static int idCounter = 1;
    private Integer transactionId;
    private Integer accountId;
    private String transactionType;
    private double amount;
    private LocalDateTime timestamp;

    public Transaction(Integer accountId, String transactionType, double amount) {
        this.transactionId = idCounter++;
        this.accountId = accountId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", accountId=" + accountId +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                '}';
    }
}
