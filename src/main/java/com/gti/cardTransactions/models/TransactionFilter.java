package com.gti.cardTransactions.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class TransactionFilter {
    private Double amount;
    private String merchant;
    private String status;

}
