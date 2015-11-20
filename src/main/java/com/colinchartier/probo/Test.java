package com.colinchartier.probo;


import com.colinchartier.probo.proof.Proof;
import com.colinchartier.probo.proof.ProofParser;
import com.colinchartier.probo.proof.ProofParsingException;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException, ProofParsingException {
        ProofParser parser = new ProofParser(Test.class.getClassLoader().getResource("Test.proof").openStream());
        Proof p = parser.parse();
        System.out.println("Parsed proof: " + p);
    }
}
