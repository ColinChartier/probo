package com.colinchartier.probo.math;

public class ArithmeticExpression implements Expression {
    private final Expression left;
    private final Expression right;
    private final ArithmeticOperator operator;

    public ArithmeticExpression(Expression left, ArithmeticOperator operator, Expression right) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public String toString() {
        return left + operator.getIdentifier() + right;
    }
}
