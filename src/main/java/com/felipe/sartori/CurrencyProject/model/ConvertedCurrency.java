package com.felipe.sartori.CurrencyProject.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ConvertedCurrency {
    private String id;
    private String description;
    private LocalDate transactionDate;
    private String originalValue;
    private String exchangeRate;
    private String convertedAmount;
}
