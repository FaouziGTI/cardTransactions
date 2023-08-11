package com.gti.cardTransactions.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gti.cardTransactions.entities.Transaction;
import com.gti.cardTransactions.models.TransactionFilter;
import com.gti.cardTransactions.services.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transactions")
    public List<Transaction> listTransactions() {
        return transactionService.getListTransactions();
    }
    
    
    @GetMapping("/transactionsFiltred")
    public List<Transaction> listTransactionsFiltred(
            @RequestParam(required = false) Double amount,
            @RequestParam(required = false) String merchant,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        TransactionFilter filter = new TransactionFilter();
        filter.setAmount(amount);
        filter.setMerchant(merchant);
        filter.setStatus(status);
        return transactionService.getFiltredListTransactions(filter);
    }


    @GetMapping("/transactionsByPage")
    public Page<Transaction> listTransactionsByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return transactionService.getListTransactionsByPage(pageable);
    }
    
    @GetMapping("/sortedListByAmount")
    public List<Transaction> getSortedListByAmount() {
        return transactionService.getSortedListByAmount();
    }
    
    
    @GetMapping("/sortedListByMerchantAndStatus")
    public List<Transaction> getSortedListByMerchantAndStatus() {
        return transactionService.getSortedListByMerchantAndStatus();
    }
}
