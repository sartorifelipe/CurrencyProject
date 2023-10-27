package com.felipe.sartori.CurrencyProject.exceptions;

public class IdNotFoundException extends Exception {
    private static final long serialVersionUID = 4928599035264976611L;

    public IdNotFoundException() {
        super("Id not found!");
    }

}
