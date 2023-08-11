package com.gti.cardTransactions.services;

import java.util.Comparator;

import com.gti.cardTransactions.entities.Transaction;

public class TransactionComparator implements Comparator<Transaction> {
    @Override
    public int compare(Transaction t1, Transaction t2) {
        int merchantComparison = t1.getMerchant().compareTo(t2.getMerchant());

        if (merchantComparison != 0) {
            return merchantComparison;
        } else {
            return t1.getStatus().compareTo(t2.getStatus());
        }
    }
}
