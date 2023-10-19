package com.felipe.sartori.WEXinterview.service;

import com.felipe.sartori.WEXinterview.exceptions.CurrencyDataNotFoundException;
import com.felipe.sartori.WEXinterview.exceptions.IdNotFoundException;
import com.felipe.sartori.WEXinterview.model.ConvertedCurrency;
import com.felipe.sartori.WEXinterview.model.Data;
import com.felipe.sartori.WEXinterview.model.PurchaseTransaction;
import com.felipe.sartori.WEXinterview.repository.PurchaseTransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

public class PurchaseTransactionServiceImplTest {

    @InjectMocks
    private PurchaseTransactionServiceImpl transactionService;

    @Mock
    private PurchaseTransactionRepository transactionRepository;

    @Mock
    private FiscalDataService fiscalDataService;

    private Data mockData;

    @Before
    public void setUp() {

        transactionService = new PurchaseTransactionServiceImpl();
             mockData = Data.builder()
                .country_currency_desc("Brazil-Real")
                .exchange_rate(1.5)
                .record_date(LocalDate.now())
                .build();
    }

    @Test
    public void testStoreTransaction() {
        PurchaseTransaction inputTransaction = new PurchaseTransaction(1L, "Test description", LocalDate.now(), 50L);
        PurchaseTransaction savedTransaction = new PurchaseTransaction(1L, "Test description", LocalDate.now(), 50L);

        when(transactionRepository.save(inputTransaction)).thenReturn(savedTransaction);

        PurchaseTransaction result = transactionService.storeTransaction(inputTransaction);

        assertEquals(savedTransaction, result);
    }

    @Test
    public void testRetrieveTransactionInSpecifiedCurrency() throws Exception {
        Long transactionId = 1L;
        String targetCurrency = "USD";
        LocalDate sixMonthsAgo = LocalDate.now().minusMonths(6);

        PurchaseTransaction purchaseTransaction = new PurchaseTransaction(/* Initialize with data */);
        Data data = new Data("Brazil-Real", 50D, LocalDate.now() );

        when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(purchaseTransaction));
        when(fiscalDataService.getFiscalAPI(anyString(), anyString())).thenReturn(data);

        ConvertedCurrency result = transactionService.retrieveTransactionInSpecifiedCurrency(transactionId, targetCurrency);

        // Perform assertions on the result
    }

    @Test(expected = IdNotFoundException.class)
    public void testRetrieveTransactionInSpecifiedCurrencyWithInvalidId() throws Exception {
        Long transactionId = 999L;
        String targetCurrency = "USD";

        when(transactionRepository.findById(transactionId)).thenReturn(Optional.empty());

        transactionService.retrieveTransactionInSpecifiedCurrency(transactionId, targetCurrency);
    }

    @Test(expected = CurrencyDataNotFoundException.class)
    public void testRetrieveTransactionInSpecifiedCurrencyWithNoData() throws Exception {
        Long transactionId = 1L;
        String targetCurrency = "USD";

        PurchaseTransaction purchaseTransaction = new PurchaseTransaction(/* Initialize with data */);

        when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(purchaseTransaction));
        when(fiscalDataService.getFiscalAPI(anyString(), anyString())).thenThrow(CurrencyDataNotFoundException.class);

        transactionService.retrieveTransactionInSpecifiedCurrency(transactionId, targetCurrency);
    }
}
