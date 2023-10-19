package com.felipe.sartori.WEXinterview.service;


import com.felipe.sartori.WEXinterview.model.ConvertedCurrency;
import com.felipe.sartori.WEXinterview.model.PurchaseTransaction;

public interface PurchaseTransactionService {
    PurchaseTransaction storeTransaction(PurchaseTransaction transaction);
    ConvertedCurrency retrieveTransactionInSpecifiedCurrency(Long id, String targetCurrency) throws Exception;
}
