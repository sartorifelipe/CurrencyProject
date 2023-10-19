package com.felipe.sartori.WEXinterview.repository;

import com.felipe.sartori.WEXinterview.model.PurchaseTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseTransactionRepository extends JpaRepository<PurchaseTransaction, Long> {
}
