package com.colinchartier.probo;


import com.colinchartier.probo.gen.ProofLexer;
import com.colinchartier.probo.gen.ProofParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;

import java.io.*;

public class Test {
    public static void main(String[] args) {
        try(BufferedReader r = new BufferedReader(new FileReader(Test.class.getClassLoader().getResource("Test.proof").getFile()))) {
            ANTLRInputStream input = new ANTLRInputStream(r);
            Lexer lexer = new ProofLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ProofParser parser = new ProofParser(tokens);
            ProofParser.ProofContext proof = parser.proof();
            for(ProofParser.ProoflineContext proofLine : proof.proofline()) {
                System.out.println(proofLine.toStringTree());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
