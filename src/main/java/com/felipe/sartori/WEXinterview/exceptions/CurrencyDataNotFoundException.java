package com.felipe.sartori.WEXinterview.exceptions;

public class CurrencyDataNotFoundException extends Exception {
    private static final long serialVersionUID = 4928599035264976611L;

    public CurrencyDataNotFoundException() {
        super("The purchase cannot be converted to the target currency");
    }
}
