package com.felipe.sartori.WEXinterview.service;

import com.felipe.sartori.WEXinterview.exceptions.CurrencyDataNotFoundException;
import com.felipe.sartori.WEXinterview.model.Data;
import com.felipe.sartori.WEXinterview.model.FiscalData;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Comparator;
import java.util.Optional;

@Service
@NoArgsConstructor
public class FiscalDataService {

    @Value("${url.base}")
    private String urlBase;
    private StringBuilder URLParams;

    public Data getFiscalAPI(String fields, String filter) throws Exception {
        URLParams = new StringBuilder();
        URLParams.append(urlBase)
                .append("?")
                .append("fields=")
                .append(fields)
                .append("&filter=")
                .append(filter);
        WebClient.Builder builder = WebClient.builder();
        FiscalData response = builder.build()
                .get()
                .uri(URLParams.toString())
                .retrieve()
                .bodyToMono(FiscalData.class)
                .block();

        Optional<Data> optionalData = response.getData().stream().sorted(Comparator.comparing(Data::getRecord_date).reversed()).findFirst();

        if(optionalData.isPresent()){
            return optionalData.get();
        }
        throw new CurrencyDataNotFoundException();
    }

}

