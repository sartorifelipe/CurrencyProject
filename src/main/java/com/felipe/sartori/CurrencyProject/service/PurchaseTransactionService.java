package com.felipe.sartori.CurrencyProject.service;


import com.felipe.sartori.CurrencyProject.model.ConvertedCurrency;
import com.felipe.sartori.CurrencyProject.model.PurchaseTransaction;

public interface PurchaseTransactionService {
    PurchaseTransaction storeTransaction(PurchaseTransaction transaction);
    ConvertedCurrency retrieveTransactionInSpecifiedCurrency(Long id, String targetCurrency) throws Exception;
}
