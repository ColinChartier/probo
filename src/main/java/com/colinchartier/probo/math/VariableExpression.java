package com.colinchartier.probo.math;

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
}
