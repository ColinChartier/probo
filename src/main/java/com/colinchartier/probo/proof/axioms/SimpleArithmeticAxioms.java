package com.colinchartier.probo.proof.axioms;

import com.colinchartier.probo.math.ArithmeticExpression;
import com.colinchartier.probo.math.ArithmeticOperator;
import com.colinchartier.probo.math.Equation;
import com.colinchartier.probo.math.Expression;
import com.colinchartier.probo.proof.Axiom;

public enum SimpleArithmeticAxioms implements Axiom {
    /**
     * I.E if someone said a=b -> 5a=5b, we just need to check (5a/a)=(5b/b), which is true in this case.
     * An interesting edge case is 5=6->0=0, because it's fine for proofs to go false->true
     */
    MULTIPLICATION {
        @Override
        public boolean valid(Equation before, Equation after) {
            if (!before.getOperator().equals(after.getOperator())) {
                return false;
            }
            Expression multiplicationDelta = new ArithmeticExpression(after.getLeft(), ArithmeticOperator.DIVIDE, before.getLeft()); //TODO make
            return new ArithmeticExpression(after.getRight(), ArithmeticOperator.DIVIDE, before.getRight()).isEquivalent(multiplicationDelta);
        }
    }
}
