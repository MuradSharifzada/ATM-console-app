package com.spring;

import java.util.ArrayList;
import java.util.List;

public class Tester {
    private List<Account> testedAccounts;
    private List<Transaction> testedTransactions;
    private List<String> errorLog;

    public Tester() {
        testedAccounts = new ArrayList<>();
        testedTransactions = new ArrayList<>();
        errorLog = new ArrayList<>();
    }

    public void testAccountCreation() {
        Account account = new Account();
        if (account.getBalance() != 0.0) {
            logError("The balance of a newly created account is not zero.");
        }

        for (Account acc : testedAccounts) {
            if (acc.getAccountId() == account.getAccountId()) {
                logError("Account ID is not unique: " + account.getAccountId());
            }
        }

        testedAccounts.add(account);
    }

    public void testDepositFunctionality(Account account, double amount) {
        boolean success = account.deposit(amount);
        if (amount <= 0 && success) {
            logError("Negative amounts were allowed to be deposited.");
        } else if (amount > 0 && !success) {
            logError("Positive amounts failed to be deposited.");
        }
    }

    public void testWithdrawFunctionality(Account account, double amount) {
        double initialBalance = account.getBalance();
        boolean success = account.withdraw(amount);

        if (amount == 0) {
            logError("Withdrawal of zero amount was allowed.");
        } else if (amount > initialBalance && success) {
            logError("Withdrawal of an amount greater than the balance was allowed.");
        } else if (amount <= 0 && success) {
            logError("Negative amounts were allowed to be withdrawn.");
        } else if (amount <= initialBalance && !success) {
            logError("Withdrawal of a valid amount failed.");
        }
    }

    public void testTransactionLogging(TransactionHistory history) {
        if (history.getTransactions().isEmpty()) {
            logError("No transactions were added to the history.");
        } else {
            for (Transaction transaction : history.getTransactions()) {
                if (transaction.getTransactionType() == null || transaction.getTimestamp() == null || transaction.getAmount() <= 0) {
                    logError("Transaction history contains incomplete information.");
                }
                testedTransactions.add(transaction);
            }
        }
    }

    public void logError(String errorMessage) {
        errorLog.add(errorMessage);
    }

    public void generateTestReport() {
        System.out.println("=== Test Report ===");
        System.out.println("Number of accounts tested: " + testedAccounts.size());
        System.out.println("Number of transactions tested: " + testedTransactions.size());
        System.out.println("Errors found:");
        if (errorLog.isEmpty()) {
            System.out.println("No errors found.");
        } else {
            for (String error : errorLog) {
                System.out.println("- " + error);
            }
        }
    }

    public static void main(String[] args) {
        Tester tester = new Tester();

        tester.testAccountCreation();

        Account account = new Account();
        tester.testDepositFunctionality(account, 100);
        tester.testDepositFunctionality(account, -50);
        tester.testWithdrawFunctionality(account, 50);
        tester.testWithdrawFunctionality(account, 200);

        TransactionHistory history = new TransactionHistory();
        history.displayTransactions();
        tester.testTransactionLogging(history);
        tester.generateTestReport();
    }
}
