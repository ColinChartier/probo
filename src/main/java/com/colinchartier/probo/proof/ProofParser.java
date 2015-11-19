package com.colinchartier.probo.proof;

import com.colinchartier.probo.gen.ProofLexer;
import com.colinchartier.probo.math.BaseSet;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;

import java.io.*;
import java.util.Arrays;
import java.util.Optional;

public class ProofParser {
    private final ANTLRInputStream antlrInput;

    public ProofParser(String... proof) {
        antlrInput = new ANTLRInputStream(Joiner.on("\n").join(Arrays.asList(proof)));
    }

    public ProofParser(InputStream input) throws IOException {
        antlrInput = new ANTLRInputStream(input);
    }

    /**
     * Generate a parser from a reader.
     * The reader will be closed after this method returns automatically.
     *
     * @param reader
     */
    public ProofParser(Reader reader) throws IOException {
        antlrInput = new ANTLRInputStream(reader);
    }

    public Proof parse() {
        Lexer lexer = new ProofLexer(antlrInput);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        com.colinchartier.probo.gen.ProofParser parser = new com.colinchartier.probo.gen.ProofParser(tokens);
        com.colinchartier.probo.gen.ProofParser.ProofContext proof = parser.proof();
        ImmutableList.Builder<ProofMember> proofMembers = ImmutableList.builder();
        for (com.colinchartier.probo.gen.ProofParser.ProoflineContext proofLine : proof.proofline()) {
            parseProofMember(proofMembers, proofLine);
        }
        return new Proof(proofMembers.build());
    }

    private void parseProofMember(ImmutableList.Builder<ProofMember> proofMembers, com.colinchartier.probo.gen.ProofParser.ProoflineContext proofLine) {
        if (proofLine.assumption() != null) {
            parseAssumption(proofMembers, proofLine.assumption());
        } else if (proofLine.deduction() != null) {
            parseDeduction(proofMembers, proofLine.deduction());
        } else if (proofLine.definition() != null) {
            parseDefinition(proofMembers, proofLine.definition());
        } else {
            throw new AssertionError("A line of the proof wasn't an assumption, deduction, or definition: " + proofLine.getText());
        }
    }

    private void parseDefinition(ImmutableList.Builder<ProofMember> proofMembers, com.colinchartier.probo.gen.ProofParser.DefinitionContext definition) {
        String identifier = definition.variable().getText();
        proofMembers.add(new VariableDeclaration(identifier, BaseSet.REALS)); //TODO sort of stubbed
    }


    private void parseDeduction(ImmutableList.Builder<ProofMember> proofMembers, com.colinchartier.probo.gen.ProofParser.DeductionContext deduction) {
        proofMembers.add(new Deduction());
    }

    private void parseAssumption(ImmutableList.Builder<ProofMember> proofMembers, com.colinchartier.probo.gen.ProofParser.AssumptionContext assumption) {
        proofMembers.add(new Assumption());
    }
}
