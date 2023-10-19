package com.felipe.sartori.WEXinterview.controller;

import com.felipe.sartori.WEXinterview.model.ConvertedCurrency;
import com.felipe.sartori.WEXinterview.model.PurchaseTransaction;
import com.felipe.sartori.WEXinterview.service.FiscalDataService;
import com.felipe.sartori.WEXinterview.service.PurchaseTransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/transactions")
public class PurchaseTransactionController {

    @Autowired
    PurchaseTransactionServiceImpl purchaseTransactionService;
    @Autowired
    FiscalDataService fiscalDataService;

    @PostMapping
    @RequestMapping("/save")
    public ResponseEntity<PurchaseTransaction> saveTransaction(
            @RequestParam("description") String description,
            @RequestParam("purchaseAmount") Long purchaseAmount
    ) {
        if (description.length() <= 50) {
            PurchaseTransaction response = purchaseTransactionService.storeTransaction(PurchaseTransaction.builder()
                    .description(description)
                    .transactionDate(LocalDate.now())
                    .purchaseAmount(purchaseAmount)
                    .build());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return null;
    }

    @GetMapping
    @RequestMapping("/get")
    public ResponseEntity getTransactionById(
            @RequestParam("id") Long id
    ) throws Exception {
        try {
            PurchaseTransaction response = purchaseTransactionService.getTransactionById(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @RequestMapping("/getall")
    public ResponseEntity getAll(
    ) {
        try{
        List<PurchaseTransaction> list = purchaseTransactionService.listAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @RequestMapping("/conversion")
    public ResponseEntity getConvertedCurrency(
            @RequestParam("targetCurrency") String currency,
            @RequestParam("purchaseId") Long purchaseId
    ) throws Exception {
        try {
            ConvertedCurrency response = purchaseTransactionService.retrieveTransactionInSpecifiedCurrency(purchaseId, currency);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

}
