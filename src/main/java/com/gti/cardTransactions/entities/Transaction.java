package com.gti.cardTransactions.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Transaction {
    private Long id;
    private Double amount;
    private Date date;
    private String merchant;
    private String status;

}
