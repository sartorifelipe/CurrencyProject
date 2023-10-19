package com.felipe.sartori.WEXinterview.model;


import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Data {
    private String country_currency_desc;
    private Double exchange_rate;
    private LocalDate record_date;
}
