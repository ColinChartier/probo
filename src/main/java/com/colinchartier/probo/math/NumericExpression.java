package com.colinchartier.probo.math;

import java.math.BigInteger;

public class NumericExpression implements Expression {
    private final BigInteger primaryMantissa; //the 1 in 1.2E3.4
    private final BigInteger primaryCharacteristic; //the 2 in 1.2E3.4
    private final BigInteger exponentMantissa; // the 3 in 1.2E3.4
    private final BigInteger exponentCharacteristic; //the 4 in 1.2E3.4

    public NumericExpression(BigInteger primaryCharacteristic, BigInteger primaryMantissa) {
        this(primaryCharacteristic, primaryMantissa, null, null);
    }

    public NumericExpression(BigInteger primaryCharacteristic, BigInteger primaryMantissa, BigInteger exponentCharacteristic, BigInteger exponentMantissa) {
        this.primaryMantissa = primaryMantissa;
        this.primaryCharacteristic = primaryCharacteristic;
        this.exponentMantissa = exponentMantissa;
        this.exponentCharacteristic = exponentCharacteristic;
    }

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
}
