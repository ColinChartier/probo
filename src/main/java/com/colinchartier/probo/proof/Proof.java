package com.colinchartier.probo.proof;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

public class Proof {
    ImmutableList<ProofMember> members;

    public Proof(ImmutableList<ProofMember> members) {
        Preconditions.checkNotNull(members);
        this.members = members;
    }
}
