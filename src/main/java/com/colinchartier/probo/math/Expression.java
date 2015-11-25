package com.colinchartier.probo.math;

public interface Expression {
    /**
     * Check whether or not two expressions are equivalent.
     * I.E: 3.0*x ~= 3x or 3+4~=7
     *
     * @param other the expression for this expression to compare itself to.
     * @return whether or not the two functions are completely equivalent.
     */
    boolean isEquivalent(Expression other);
}
