package com.colinchartier.probo.proof;

import com.colinchartier.probo.math.Set;
import com.google.common.base.Preconditions;

public class VariableDeclaration extends ProofMember {
    private final String variableIdentifier;
    private final Set set;

    public VariableDeclaration(String variableIdentifier, Set set) {
        Preconditions.checkNotNull(variableIdentifier);
        Preconditions.checkNotNull(set);
        Preconditions.checkArgument(!variableIdentifier.isEmpty(), "identifier must not be empty!");
        this.variableIdentifier = variableIdentifier;
        this.set = set;
    }

    @Override
    public String toString() {
        return "Variable declaration: " + variableIdentifier + " in " + set;
    }
}
