package com.colinchartier.probo.math;

import java.math.BigInteger;

public class NumericExpression implements Expression {
    public static final NumericExpression ZERO = new NumericExpression(null, null);
    public static final NumericExpression ONE = new NumericExpression(BigInteger.ONE, null);

    private final BigInteger primaryMantissa; //the 1 in 1.2E3.4
    private final BigInteger primaryCharacteristic; //the 2 in 1.2E3.4
    private final BigInteger exponentMantissa; // the 3 in 1.2E3.4
    private final BigInteger exponentCharacteristic; //the 4 in 1.2E3.4

    public NumericExpression(BigInteger primaryCharacteristic, BigInteger primaryMantissa) {
        this(primaryCharacteristic, primaryMantissa, null, null);
    }

    public NumericExpression(BigInteger primaryCharacteristic, BigInteger primaryMantissa, BigInteger exponentCharacteristic, BigInteger exponentMantissa) {
        this.primaryMantissa = primaryMantissa == null || primaryMantissa.equals(BigInteger.ZERO) ? null : primaryMantissa;
        this.primaryCharacteristic = primaryCharacteristic == null || primaryCharacteristic.equals(BigInteger.ZERO) ? null : primaryCharacteristic;
        this.exponentMantissa = exponentMantissa == null || exponentMantissa.equals(BigInteger.ZERO) ? null : exponentMantissa;
        this.exponentCharacteristic = exponentCharacteristic == null || exponentCharacteristic.equals(BigInteger.ZERO) ? null : exponentCharacteristic;
    }

    public BigInteger getPrimaryMantissa() {
        return primaryMantissa;
    }

    public BigInteger getPrimaryCharacteristic() {
        return primaryCharacteristic;
    }

    public BigInteger getExponentMantissa() {
        return exponentMantissa;
    }

    public BigInteger getExponentCharacteristic() {
        return exponentCharacteristic;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        if (primaryMantissa == null && primaryCharacteristic == null) {
            b.append("0");
        } else {
            if (primaryCharacteristic != null) {
                b.append(primaryCharacteristic);
            }
            if (primaryMantissa != null) {
                b.append(".").append(primaryMantissa);
            }
        }
        if (exponentCharacteristic != null || exponentMantissa != null) {
            b.append("E");
            if (exponentCharacteristic != null) {
                b.append(exponentCharacteristic);
            }
            if (exponentMantissa != null) {
                b.append(".").append(exponentMantissa);
            }
        }
        return b.toString();
    }

    @Override
    public boolean isEquivalent(Expression other) { //TODO
        return false;
    }
}
