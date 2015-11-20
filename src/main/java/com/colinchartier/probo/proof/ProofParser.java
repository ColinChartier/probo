package com.colinchartier.probo.proof;

import com.colinchartier.probo.gen.ProofLexer;
import com.colinchartier.probo.math.BaseSet;
import com.colinchartier.probo.math.Equation;
import com.colinchartier.probo.math.Expression;
import com.colinchartier.probo.math.RelativeOperator;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    public Proof parse() throws ProofParsingException {
        Lexer lexer = new ProofLexer(antlrInput);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        com.colinchartier.probo.gen.ProofParser parser = new com.colinchartier.probo.gen.ProofParser(tokens);
        com.colinchartier.probo.gen.ProofParser.ProofContext proof = parser.proof();
        ImmutableList.Builder<ProofMember> proofMembers = ImmutableList.builder();
        int lineNumber = 0;
        for (com.colinchartier.probo.gen.ProofParser.ProoflineContext proofLine : proof.proofline()) {
            try {
                parseProofMember(proofMembers, proofLine, lineNumber);
            } catch (NullPointerException | IllegalArgumentException ex) {
                throw new ProofParsingException("Error caught while parsing proof at line " + lineNumber, ex);
            }
            lineNumber++;
        }
        return new Proof(proofMembers.build());
    }

    private void parseProofMember(ImmutableList.Builder<ProofMember> proofMembers, com.colinchartier.probo.gen.ProofParser.ProoflineContext proofLine, int lineNumber) throws ProofParsingException {
        if (proofLine.assumption() != null) {
            parseAssumption(proofMembers, proofLine.assumption(), lineNumber);
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

    private void parseAssumption(ImmutableList.Builder<ProofMember> proofMembers, com.colinchartier.probo.gen.ProofParser.AssumptionContext assumption, int lineNumber) throws ProofParsingException {
        proofMembers.add(new Assumption(ImmutableList.copyOf(parseMultiequation(assumption.multiequation(), lineNumber))));
    }

    private List<Equation> parseMultiequation(com.colinchartier.probo.gen.ProofParser.MultiequationContext multieq, int lineNumber) throws ProofParsingException {
        int expressionSize = multieq.expression().size(); //I.E a > b > c has 3 expressions, 2 ops.
        List<Equation> ret = new ArrayList<>(expressionSize - 1);
        for (int i = 1; i < expressionSize; i++) {
            ret.add(new Equation(
                            parseExpression(multieq.expression(i - 1)),
                            parseRelativeOperator(multieq.relative(i - 1), lineNumber),
                            parseExpression(multieq.expression(i)))
            );
        }
        return ret;
    }

    private RelativeOperator parseRelativeOperator(com.colinchartier.probo.gen.ProofParser.RelativeContext relative, int lineNumber) throws ProofParsingException {
        Optional<RelativeOperator> operatorOptional = RelativeOperator.getRelativeOperator(relative.getText());
        if (!operatorOptional.isPresent()) {
            throw new ProofParsingException("Invalid operator found on line " + lineNumber + ": " + relative.getText());
        }
        return operatorOptional.get();
    }

    private Expression parseExpression(com.colinchartier.probo.gen.ProofParser.ExpressionContext expression) {
        return new Expression(); //TODO stubbed
    }
}
