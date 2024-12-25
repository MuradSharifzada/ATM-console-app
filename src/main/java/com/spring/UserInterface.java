package com.spring;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);
    private AccountManager accountManager = new AccountManager();
    private TransactionHistory transactionHistory = new TransactionHistory();

    public void displayMenu() {
        System.out.println("\n1. Create Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Check Balance");
        System.out.println("5. View Transactions");
        System.out.println("6. Exit");
        System.out.print("Choice: ");
    }

    public int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Write properly choice");
            return -1;
        }
    }

    public void handleUserChoice(int choice) {
        switch (choice) {
            case 1 -> createAccount();
            case 2 -> depositToAccount();
            case 3 -> withdrawFromAccount();
            case 4 -> checkBalance();
            case 5 -> viewTransactions();
            case 6 -> exitProgram();
            default -> System.out.println("Invalid choice.");
        }
    }

    private void createAccount() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Initial Balance: ");
        double balance = Double.parseDouble(scanner.nextLine());
        Integer accountId = accountManager.createAccount(new Account(name, balance));

    }

    private void depositToAccount() {
        System.out.print("Account ID: ");
        Integer accountId = Integer.parseInt(scanner.nextLine());
        System.out.print("Deposit Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        Account account = accountManager.getAccount(accountId);
        if (account != null && account.deposit(amount)) {
            transactionHistory.addTransaction(new Transaction(accountId, "Deposit", amount));
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Deposit failed.");
        }
    }

    private void withdrawFromAccount() {
        System.out.print("Account ID: ");
        Integer accountId = Integer.parseInt(scanner.nextLine());
        System.out.print("Withdraw Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        Account account = accountManager.getAccount(accountId);
        if (account != null && account.withdraw(amount)) {
            transactionHistory.addTransaction(new Transaction(accountId, "Withdraw", amount));
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Withdrawal failed.");
        }
    }

    private void checkBalance() {
        System.out.print("Account ID: ");
        Integer accountId = Integer.parseInt(scanner.nextLine());
        Account account = accountManager.getAccount(accountId);
        if (account != null) {
            System.out.println("Balance: " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    private void viewTransactions() {
        transactionHistory.displayTransactions();
    }

    private void exitProgram() {
        System.out.println("Exiting...");
        System.exit(0);
    }
}
