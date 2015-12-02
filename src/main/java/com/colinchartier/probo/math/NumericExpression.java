package com.colinchartier.probo.math;

import java.math.BigDecimal;

public class NumericExpression implements Expression {
    private final BigDecimal value; //the 1.2 in 1.2E3.4

    public NumericExpression(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean isEquivalent(Expression other) { //TODO
        return false;
    }

    @Override
    public Expression simplify() {
        return this;
    }
}
