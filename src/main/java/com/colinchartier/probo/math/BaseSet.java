package com.colinchartier.probo.math;

public enum BaseSet {
    REALS("R"),
    RATIONALS("Q"),
    INTEGERS("Z"),
    NATURALS("N");

    private final String symbol;

    BaseSet(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol( ) {
        return symbol;
    }
}
