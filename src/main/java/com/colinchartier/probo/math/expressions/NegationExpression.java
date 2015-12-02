package com.colinchartier.probo.math.expressions;

import com.colinchartier.probo.math.Expression;
import com.google.common.base.Preconditions;

public class NegationExpression implements Expression {
    private final Expression expression;

    public NegationExpression(Expression expression) {
        Preconditions.checkNotNull(expression, "expression");
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public boolean isEquivalent(Expression other) {
        return false; //TODO
    }

    @Override
    public Expression simplify() {
        Expression inner = expression.simplify();
        if (inner instanceof NegationExpression) {
            return ((NegationExpression) inner).getExpression(); //--a == a
        }
        return new NegationExpression(inner);
    }
}
