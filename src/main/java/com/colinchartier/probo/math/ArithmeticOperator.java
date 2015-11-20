package com.colinchartier.probo.math;

import com.google.common.collect.ImmutableMap;

import java.util.Optional;

public enum ArithmeticOperator {
    ADD("+") {
    },
    SUBTRACT("-") {
    },
    DIVIDE("/") {
    },
    MULTIPLY("*") {
    },
    EXPONENTIATE("^") {
    };

    static final ImmutableMap<String, ArithmeticOperator> identifierMap = populateIdentifierMap();

    private static ImmutableMap<String, ArithmeticOperator> populateIdentifierMap() {
        ImmutableMap.Builder<String, ArithmeticOperator> b = ImmutableMap.builder();
        for (ArithmeticOperator v : values()) {
            b.put(v.getIdentifier(), v);
        }
        return b.build();
    }

    private final String identifier;

    ArithmeticOperator(String symbol) {
        this.identifier = symbol;
    }

    public String getIdentifier() {
        return identifier;
    }

    public static Optional<ArithmeticOperator> getOperation(String identifier) {
        return Optional.ofNullable(identifierMap.get(identifier));
    }
}
