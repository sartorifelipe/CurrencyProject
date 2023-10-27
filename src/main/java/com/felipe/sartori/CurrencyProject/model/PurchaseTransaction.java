package com.felipe.sartori.CurrencyProject.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@Table(name = "purchase")
@NoArgsConstructor
public class PurchaseTransaction {
    public PurchaseTransaction(Long id, String description, LocalDate transactionDate, double purchaseAmount) {
        this.id = id;
        this.description = description;
        this.transactionDate = transactionDate;
        this.purchaseAmount = purchaseAmount;
    }

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalDate transactionDate;
    private double purchaseAmount;
}

