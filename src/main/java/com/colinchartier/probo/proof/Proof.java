package com.colinchartier.probo.proof;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

public class Proof {
    private final ImmutableList<ProofMember> members;

    public Proof(ImmutableList<ProofMember> members) {
        Preconditions.checkNotNull(members);
        this.members = members;
    }

    @Override
    public String toString( ) {
        return "Proof with " + members.size() + " members. " + members.toString();
    }

    public ImmutableList<ProofMember> getMembers() {
        return members;
    }
}
