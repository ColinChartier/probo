package com.colinchartier.probo.math;

import com.google.common.base.Preconditions;

public class Equation {
    private final Expression left;
    private final Expression right;
    private final RelativeOperator operator;

    public Equation(Expression left, RelativeOperator operator, Expression right) {
        Preconditions.checkNotNull(left);
        Preconditions.checkNotNull(right);
        Preconditions.checkNotNull(operator);
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    public RelativeOperator getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return left + operator.getIdentifier() + right;
    }
}
