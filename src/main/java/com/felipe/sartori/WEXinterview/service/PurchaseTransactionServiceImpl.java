package com.felipe.sartori.WEXinterview.service;


import com.felipe.sartori.WEXinterview.exceptions.CurrencyDataNotFoundException;
import com.felipe.sartori.WEXinterview.exceptions.IdNotFoundException;
import com.felipe.sartori.WEXinterview.model.ConvertedCurrency;
import com.felipe.sartori.WEXinterview.model.Data;
import com.felipe.sartori.WEXinterview.model.PurchaseTransaction;
import com.felipe.sartori.WEXinterview.repository.PurchaseTransactionRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
public class PurchaseTransactionServiceImpl implements PurchaseTransactionService {

    @Autowired
    private PurchaseTransactionRepository repository;

    @Autowired
    private FiscalDataService fiscalDataService;

    @Override
    public PurchaseTransaction storeTransaction(PurchaseTransaction transaction) {
        return PurchaseTransaction.builder()
                .id(repository.save(transaction).getId())
                .description(transaction.getDescription())
                .transactionDate(transaction.getTransactionDate())
                .purchaseAmount(transaction.getPurchaseAmount())
                .build();
    }

    @Override
    public ConvertedCurrency retrieveTransactionInSpecifiedCurrency(Long id, String targetCurrency) throws Exception {
        Optional<PurchaseTransaction> transactionOptional = repository.findById(id);
        if (transactionOptional.isPresent()) {
            String recordDate = LocalDate.now().minusMonths(6).toString();
            String filter = "country_currency_desc:in:(" + targetCurrency + "),record_date:gte:" + recordDate;
            String fields = "country_currency_desc,exchange_rate,record_date";

            Data data = fiscalDataService.getFiscalAPI(fields, filter);
            Double convertedAmount = transactionOptional.get().getPurchaseAmount() * data.getExchange_rate();



            return ConvertedCurrency.builder()
                    .id(id.toString())
                    .transactionDate(transactionOptional.get().getTransactionDate())
                    .description(transactionOptional.get().getDescription())
                    .convertedAmount(String.format("%.2f", convertedAmount))
                    .exchangeRate(data.getExchange_rate().toString())
                    .originalValue(String.valueOf(transactionOptional.get().getPurchaseAmount()))
                    .build();
        }
        throw new CurrencyDataNotFoundException();

    }

    public List<PurchaseTransaction> listAll() {
        return repository.findAll();
    }

    public PurchaseTransaction getTransactionById(Long id) throws Exception{
        Optional<PurchaseTransaction> transactionOptional = repository.findById(id);
        if(transactionOptional.isPresent()){
            return transactionOptional.get();
        }
        throw new IdNotFoundException();
    }

}

