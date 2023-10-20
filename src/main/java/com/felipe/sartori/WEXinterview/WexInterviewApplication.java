package com.felipe.sartori.WEXinterview;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "WEX Interview App",
                version = "1.0.0",
                description = "Backend application for persist and convert currency transactions"
        )
)
public class WexInterviewApplication {

    public static void main(String[] args) {
        SpringApplication.run(WexInterviewApplication.class, args);
    }


}
