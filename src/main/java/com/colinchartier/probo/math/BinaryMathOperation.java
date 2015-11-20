package com.colinchartier.probo.math;

import com.google.common.collect.ImmutableMap;

import java.math.BigDecimal;
import java.util.Optional;

public enum BinaryMathOperation implements BinaryEvaluator {
    ADD("+") {
        @Override
        public BigDecimal evaluate(BigDecimal left, BigDecimal right) {
            return left.add(right);
        }
    },
    SUBTRACT("-") {
        @Override
        public BigDecimal evaluate(BigDecimal left, BigDecimal right) {
            return left.subtract(right);
        }
    },
    DIVIDE("/") {
        @Override
        public BigDecimal evaluate(BigDecimal left, BigDecimal right) {
            return left.divide(right);
        }
    },
    MULTIPLY("*") {
        @Override
        public BigDecimal evaluate(BigDecimal left, BigDecimal right) {
            return left.multiply(right);
        }
    },
    EXPONENTIATE("^") {
        @Override
        public BigDecimal evaluate(BigDecimal left, BigDecimal right) {
            return left.pow(right.intValue()); //TODO
        }
    };

    static final ImmutableMap<String, BinaryMathOperation> identifierMap = populateIdentifierMap();

    private static ImmutableMap<String, BinaryMathOperation> populateIdentifierMap() {
        ImmutableMap.Builder<String, BinaryMathOperation> b = ImmutableMap.builder();
        for(BinaryMathOperation v : values()) {
            b.put(v.getIdentifier(), v);
        }
        return b.build();
    }

    private final String identifier;

    BinaryMathOperation(String symbol) {
        this.identifier = symbol;
    }

    public String getIdentifier() {
        return identifier;
    }

    public static Optional<BinaryMathOperation> getOperation(String identifier) {
        return Optional.ofNullable(identifierMap.get(identifier));
    }
}
