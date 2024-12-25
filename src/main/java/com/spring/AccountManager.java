package com.spring;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class AccountManager {
    private Map<Integer, Account> accounts = new HashMap<>();
    private AtomicInteger accountCounter = new AtomicInteger(1);

    public Integer createAccount(Account account) {
        if (account.getBalance() < 0) {
            System.out.println("Balance cannot be negative");
            return 0;
        }
        Integer accountId = accountCounter.getAndIncrement();
        account.setAccountId(accountId);
        accounts.put(accountId, account);
        System.out.println("Account created with ID: " + accountId);
        return accountId;
    }

    public Account getAccount(Integer accountId) {
        return accounts.get(accountId);
    }

    public void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
        } else {
            accounts.values().forEach(System.out::println);
        }
    }
}
