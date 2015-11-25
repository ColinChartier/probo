package com.colinchartier.probo.math;

public class NegativeExpression implements Expression {
    private final Expression expression;

    public NegativeExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public boolean isEquivalent(Expression other) {
        return false; //TODO
    }
}
