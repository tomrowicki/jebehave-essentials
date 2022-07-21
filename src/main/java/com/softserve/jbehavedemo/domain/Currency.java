package com.softserve.jbehavedemo.domain;

public enum Currency {
    EUR ("€"),
    USD ("$"),
    ;

    private String symbol;

    Currency(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
