//package com.felipe.sartori.WEXinterview.service;
//
//import com.felipe.sartori.WEXinterview.exceptions.CurrencyDataNotFoundException;
//import com.felipe.sartori.WEXinterview.model.Data;
//import com.felipe.sartori.WEXinterview.model.FiscalData;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThrows;
//import static org.mockito.Mockito.*;
//
//@RunWith(MockitoJUnitRunner.class)
//public class FiscalDataServiceTest {
//
//    @InjectMocks
//    private FiscalDataService fiscalDataService;
//
//    @Mock
//    private WebClient.Builder webClientBuilder;
//    @Mock
//    private WebClient webClient;
//
//    Data mockData = new Data();
//
//    @Before
//    public void setUp() {
//        fiscalDataService = new FiscalDataService();
//        mockData = Data.builder()
//                .country_currency_desc("Brazil-Real")
//                .exchange_rate(1.5)
//                .record_date(LocalDate.now())
//                .build();
//        webClient = getWebClientMock(mockData);
//    }
//
//    @Test
//    public void testGetFiscalAPIWithValidData() {
//
//        FiscalData mockResponse = new FiscalData();
//        mockResponse.setData(Arrays.asList(mockData));
//
//        try {
//            Data result = fiscalDataService.getFiscalAPI("fields", "filter");
//            assertEquals(mockData, result);
//        } catch (Exception e) {
//
//        }
//
//        verify(webClientBuilder, times(1)).build();
//        verify(webClient, times(1)).get();
//        verify(webClient, times(1)).get().uri(anyString());
//        verify(webClient, times(1)).get().retrieve();
//        verify(webClient, times(1)).get().retrieve().bodyToMono(FiscalData.class);
//    }
//
//    @Test
//    public void testGetFiscalAPIWithNoData() {
//        FiscalData mockResponse = new FiscalData();
//        mockResponse.setData(null);
//        WebClient webClientMock = getWebClientMock(mockData);
//
//        when(webClientBuilder.build()).thenReturn(webClient);
//        when(webClient.get()).thenReturn(webClientMock);
//        when(webClient.get().uri(anyString())).thenReturn(webClient);
//        when(webClient.get().retrieve()).thenReturn(webClient);
//        when(webClient.get().retrieve().bodyToMono(FiscalData.class)).thenReturn(Mono.just(mockResponse));
//
//        assertThrows(CurrencyDataNotFoundException.class, () -> fiscalDataService.getFiscalAPI("fields", "filter"));
//    }
//    private static WebClient getWebClientMock(final Data resp) {
//        final var mock = Mockito.mock(WebClient.class);
//        final var uriSpecMock = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
//        final var headersSpecMock = Mockito.mock(WebClient.RequestHeadersSpec.class);
//        final var responseSpecMock = Mockito.mock(WebClient.ResponseSpec.class);
//
//        when(mock.get()).thenReturn(uriSpecMock);
//        when(uriSpecMock.uri(ArgumentMatchers.<String>notNull())).thenReturn(headersSpecMock);
//        when(headersSpecMock.header(notNull(), notNull())).thenReturn(headersSpecMock);
//        when(headersSpecMock.headers(notNull())).thenReturn(headersSpecMock);
//        when(headersSpecMock.retrieve()).thenReturn(responseSpecMock);
//        when(responseSpecMock.bodyToMono(ArgumentMatchers.<Class<Data>>notNull()))
//                .thenReturn(Mono.just(resp));
//
//        return mock;
//    }
//}
//
