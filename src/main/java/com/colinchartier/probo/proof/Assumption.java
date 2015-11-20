package com.colinchartier.probo.proof;

import com.colinchartier.probo.math.Equation;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

public class Assumption extends ProofMember {
    private final ImmutableList<Equation> equations; //This is a list because 0>a>5 is equal to 0>a and a>5.

    public Assumption(ImmutableList<Equation> equations) {
        Preconditions.checkNotNull(equations);
        this.equations = equations;
    }

    @Override
    public String toString( ) {
        return "Assumption with " + equations.size() + " equations: " + equations;
    }
}
