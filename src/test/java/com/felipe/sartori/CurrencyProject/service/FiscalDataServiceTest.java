package com.felipe.sartori.CurrencyProject.service;

import com.felipe.sartori.CurrencyProject.exceptions.CurrencyDataNotFoundException;
import com.felipe.sartori.CurrencyProject.model.Data;
import com.felipe.sartori.CurrencyProject.model.FiscalData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class FiscalDataServiceTest {

    @Mock
    private FiscalDataService fiscalDataService;

    @Mock
    private Data mockData;

    @Before
    public void setUp() {
        fiscalDataService = new FiscalDataService();
        mockData = Data.builder()
                .country_currency_desc("Brazil-Real")
                .exchange_rate(1.5)
                .record_date(LocalDate.now())
                .build();
    }
    @Test
    public void testGetFiscalAPIWithValidData() {

        FiscalData mockResponse = new FiscalData();
        mockResponse.setData(Arrays.asList(mockData));

        try {
            Data result = fiscalDataService.getFiscalAPI("fields", "filter");
            assertEquals(mockData, result);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    @Test
    public void testGetFiscalAPIWithNoData() {
        FiscalData mockResponse = new FiscalData();
        mockResponse.setData(Arrays.asList(mockData));

        try {
            fiscalDataService.getFiscalAPI(null, null);
            assertThrows(CurrencyDataNotFoundException.class, () -> fiscalDataService.getFiscalAPI(null, null));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

