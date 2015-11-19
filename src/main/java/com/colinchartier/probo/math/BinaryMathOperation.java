package com.colinchartier.probo.math;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;

import java.math.BigDecimal;
import java.util.Arrays;
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

    static final ImmutableMap<String, BinaryMathOperation> symbolMap = populateSymbolMap();

    private static ImmutableMap<String, BinaryMathOperation> populateSymbolMap() {
        ImmutableMap.Builder<String, BinaryMathOperation> b = ImmutableMap.builder();
        for(BinaryMathOperation v : values()) {
            b.put(v.getSymbol(), v);
        }
        return b.build();
    }

    private final String symbol;

    BinaryMathOperation(String symbol) {
        this.symbol = symbol;
    }


    public String getSymbol() {
        return symbol;
    }

    public static Optional<BinaryMathOperation> getOperation(String symbol) {
        return Optional.ofNullable(symbolMap.get(symbol));
    }
}
