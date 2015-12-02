package com.colinchartier.probo.math.expressions;

import com.colinchartier.probo.math.Expression;
import com.google.common.base.Preconditions;

public class VariableExpression implements Expression {
    private final String id;

    public VariableExpression(String id) {
        Preconditions.checkNotNull(id, "id can't be null!");
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean isEquivalent(Expression other) {
        return false; //TODO
    }

    @Override
    public Expression simplify() {
        return this;
    }
}
