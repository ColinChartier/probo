package com.colinchartier.probo.proof;

import com.colinchartier.probo.math.Equation;

/**
 * Represents a transformation of equivalences
 * That is, if we had the axiom "any integer x, a=b implies xa=xb" and we were given inputs
 * a=5 and 5a=25, we just need to find the equivalence 5a = a*5 and 25=5*5, and apply the axiom.
 */
public interface Axiom {
    boolean valid(Equation before, Equation after);
}
