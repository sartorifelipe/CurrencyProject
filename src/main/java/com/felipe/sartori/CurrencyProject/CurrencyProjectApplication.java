package com.felipe.sartori.CurrencyProject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Currency project",
                version = "1.0.0",
                description = "Backend application for persist and convert currency transactions"
        )
)
public class CurrencyProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyProjectApplication.class, args);
    }


}
