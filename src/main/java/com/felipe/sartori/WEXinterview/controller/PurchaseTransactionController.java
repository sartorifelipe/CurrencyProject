package com.felipe.sartori.WEXinterview.controller;

import com.felipe.sartori.WEXinterview.model.ConvertedCurrency;
import com.felipe.sartori.WEXinterview.model.PurchaseTransaction;
import com.felipe.sartori.WEXinterview.service.FiscalDataService;
import com.felipe.sartori.WEXinterview.service.PurchaseTransactionServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/transactions")
@Tag(name = "wex-interview")
public class PurchaseTransactionController {

    @Autowired
    PurchaseTransactionServiceImpl purchaseTransactionService;
    @Autowired
    FiscalDataService fiscalDataService;


    @Operation(summary = "Receive and store a transaction", method ="POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transaction received and created successfully!"),
            @ApiResponse(responseCode = "400", description = "Transaction not accepted"),
    })
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity saveTransaction(
            @RequestParam("description") String description,
            @RequestParam("purchaseAmount") Long purchaseAmount
    ) {
        if (description.length() <= 50) {
            PurchaseTransaction response = purchaseTransactionService.storeTransaction(PurchaseTransaction.builder()
                    .description(description)
                    .transactionDate(LocalDate.now())
                    .purchaseAmount(purchaseAmount)
                    .build());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Description bigger then 50 Char.", HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Retrieve transaction and convert to target currency", method ="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction converted and retrieved successfully!"),
            @ApiResponse(responseCode = "404", description = "Error in transaction conversion or retrieval"),
    })
    @RequestMapping(value = "/conversion", method = RequestMethod.GET)
    @GetMapping
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
