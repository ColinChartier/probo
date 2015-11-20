package com.colinchartier.probo.proof;

import com.colinchartier.probo.math.Equation;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

public class Deduction extends ProofMember {
    private final ImmutableList<Equation> equations; //This is a list because 0>a>5 is equal to 0>a and a>5.

    public Deduction(ImmutableList<Equation> equations) {
        Preconditions.checkNotNull(equations);
        this.equations = equations;
    }

    @Override
    public String toString() {
        return "Deduction with " + equations.size() + " equations: " + equations;
    }

}
