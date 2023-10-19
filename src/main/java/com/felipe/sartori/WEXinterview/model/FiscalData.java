package com.felipe.sartori.WEXinterview.model;

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
