package com.colinchartier.probo.math.expressions;

import com.colinchartier.probo.math.ArithmeticOperator;
import com.colinchartier.probo.math.Expression;

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

    @Override
    public boolean isEquivalent(Expression other) {
        return false; //TODO
    }

    @Override
    public Expression simplify() {
        return null;
    }
}
