package com.colinchartier.probo.math;

public class NegationExpression implements Expression {
    private final Expression expression;

    public NegationExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public boolean isEquivalent(Expression other) {
        return false; //TODO
    }

    @Override
    public Expression simplify() {
        return null;
    }
}
