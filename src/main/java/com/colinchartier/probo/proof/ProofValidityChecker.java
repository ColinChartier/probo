package com.colinchartier.probo.proof;

public class ProofValidityChecker {
    private final Proof proof;

    public ProofValidityChecker(Proof proof) {
        this.proof = proof;
    }

    public boolean isValid( ) {
        for(ProofMember m : proof.getMembers()) {
            if(!checkMemberValidity(m)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkMemberValidity(ProofMember m) {
        return false;
    }
}
