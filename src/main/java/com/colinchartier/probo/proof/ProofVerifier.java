package com.colinchartier.probo.proof;

public class ProofVerifier {
    private final Proof proof;

    public ProofVerifier(Proof proof) {
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
