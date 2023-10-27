package com.felipe.sartori.CurrencyProject.repository;

import com.felipe.sartori.CurrencyProject.model.PurchaseTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseTransactionRepository extends JpaRepository<PurchaseTransaction, Long> {
}
