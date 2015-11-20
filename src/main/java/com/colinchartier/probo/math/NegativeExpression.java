package com.colinchartier.probo.math;

public class NegativeExpression implements Expression {
    private final Expression expression;

    public NegativeExpression(Expression expression) {
        this.expression = expression;
    }
}
