package com.felipe.sartori.CurrencyProject.model;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FiscalData {
    private List<Data> data;
}
