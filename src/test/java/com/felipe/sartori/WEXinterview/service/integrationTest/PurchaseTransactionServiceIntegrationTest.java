package com.felipe.sartori.WEXinterview.service.integrationTest;

import com.felipe.sartori.WEXinterview.model.PurchaseTransaction;
import com.felipe.sartori.WEXinterview.repository.PurchaseTransactionRepository;
import com.felipe.sartori.WEXinterview.service.FiscalDataService;
import com.felipe.sartori.WEXinterview.service.PurchaseTransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PurchaseTransactionServiceIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PurchaseTransactionRepository transactionRepository;

    @Autowired
    private FiscalDataService fiscalDataService;

    private PurchaseTransactionServiceImpl transactionService;

    @BeforeEach
    public void setup() {
        transactionService = new PurchaseTransactionServiceImpl();
        transactionService.setRepository(transactionRepository);
        transactionService.setFiscalDataService(fiscalDataService);
    }

    @Test
    public void testStoreTransaction() {
        // Criar uma instância de PurchaseTransaction para envio na requisição
        PurchaseTransaction requestTransaction = new PurchaseTransaction(999L, "Test description", LocalDate.now(), 50L);

        // Enviar uma solicitação POST para o endpoint de armazenamento de transação
        ResponseEntity<PurchaseTransaction> responseEntity = restTemplate.postForEntity("http://localhost:"+port+"/v1/transaction/save", requestTransaction, PurchaseTransaction.class);

        // Verificar se a resposta HTTP é 200 OK
        assertEquals(200, responseEntity.getStatusCodeValue());

        // Verificar se a transação foi armazenada no banco de dados
        Long transactionId = responseEntity.getBody().getId();
        PurchaseTransaction savedTransaction = transactionRepository.findById(transactionId).orElse(null);
        assertEquals(requestTransaction, savedTransaction);
    }

    @Test
    public void testRetrieveTransactionInSpecifiedCurrency() {
        // Realizar um teste de integração para recuperar uma transação em uma moeda específica
        // Certifique-se de que a transação e os dados fiscais apropriados estejam disponíveis no banco de dados
        // Enviar uma solicitação GET para o endpoint correspondente
        // Verificar a resposta e os resultados esperados
    }
}
