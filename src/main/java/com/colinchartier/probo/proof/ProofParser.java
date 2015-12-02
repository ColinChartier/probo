package com.colinchartier.probo.proof;

import com.colinchartier.probo.gen.ProofLexer;
import com.colinchartier.probo.math.*;
import com.colinchartier.probo.math.expressions.ArithmeticExpression;
import com.colinchartier.probo.math.expressions.NegationExpression;
import com.colinchartier.probo.math.expressions.NumericExpression;
import com.colinchartier.probo.math.expressions.VariableExpression;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
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
            parseDeduction(proofMembers, proofLine.deduction(), lineNumber);
        } else if (proofLine.definition() != null) {
            parseDefinition(proofMembers, proofLine.definition());
        } else {
            throw new AssertionError("A line of the proof wasn't an assumption, deduction, or definition: " + proofLine.getText());
        }
    }

    private void parseDefinition(ImmutableList.Builder<ProofMember> proofMembers, com.colinchartier.probo.gen.ProofParser.DefinitionContext definition) {
        for (com.colinchartier.probo.gen.ProofParser.VariableContext var : definition.variable()) {
            String identifier = var.getText();
            proofMembers.add(new VariableDeclaration(identifier, BaseSet.REALS)); //TODO sort of stubbed
        }
    }


    private void parseDeduction(ImmutableList.Builder<ProofMember> proofMembers, com.colinchartier.probo.gen.ProofParser.DeductionContext deduction, int lineNumber) throws ProofParsingException {
        proofMembers.add(new Deduction(ImmutableList.copyOf(parseMultiequation(deduction.multiequation(), lineNumber))));
    }

    private void parseAssumption(ImmutableList.Builder<ProofMember> proofMembers, com.colinchartier.probo.gen.ProofParser.AssumptionContext assumption, int lineNumber) throws ProofParsingException {
        proofMembers.add(new Assumption(ImmutableList.copyOf(parseMultiequation(assumption.multiequation(), lineNumber))));
    }

    private List<Equation> parseMultiequation(com.colinchartier.probo.gen.ProofParser.MultiequationContext multieq, int lineNumber) throws ProofParsingException {
        int expressionSize = multieq.expression().size(); //I.E a > b > c has 3 expressions, 2 ops.
        List<Equation> ret = new ArrayList<>(expressionSize - 1);
        for (int i = 1; i < expressionSize; i++) {
            ret.add(new Equation(
                            parseExpression(multieq.expression(i - 1), lineNumber),
                            parseRelativeOperator(multieq.relative(i - 1), lineNumber),
                            parseExpression(multieq.expression(i), lineNumber))
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

    private Expression parseExpression(com.colinchartier.probo.gen.ProofParser.ExpressionContext expression, int lineNumber) {
        if (expression.additionSubtraction() != null) {
            return parseAdditionSubtractionExpression(expression.expression(0), expression.additionSubtraction(), expression.expression(1), lineNumber);
        }
        if (expression.multiplicationDivision() != null) {
            return parseMultiplicationDivision(expression.expression(0), expression.multiplicationDivision(), expression.expression(1), lineNumber);
        }
        return parseImplicitMultiplication(expression.powExpression(), lineNumber);
    }

    private Expression parseAdditionSubtractionExpression(com.colinchartier.probo.gen.ProofParser.ExpressionContext left, com.colinchartier.probo.gen.ProofParser.AdditionSubtractionContext plusMinus, com.colinchartier.probo.gen.ProofParser.ExpressionContext right, int lineNumber) {
        ArithmeticOperator op = plusMinus.PLUS() != null ? ArithmeticOperator.ADD : ArithmeticOperator.SUBTRACT;
        return new ArithmeticExpression(parseExpression(left, lineNumber), op, parseExpression(right, lineNumber));
    }

    private Expression parseMultiplicationDivision(com.colinchartier.probo.gen.ProofParser.ExpressionContext left, com.colinchartier.probo.gen.ProofParser.MultiplicationDivisionContext timesDiv, com.colinchartier.probo.gen.ProofParser.ExpressionContext right, int lineNumber) {
        ArithmeticOperator op = timesDiv.TIMES() != null ? ArithmeticOperator.MULTIPLY : ArithmeticOperator.DIVIDE;
        return new ArithmeticExpression(parseExpression(left, lineNumber), op, parseExpression(right, lineNumber));
    }

    private Expression parseImplicitMultiplication(List<com.colinchartier.probo.gen.ProofParser.PowExpressionContext> powExpressionContexts, int lineNumber) {
        int length = powExpressionContexts.size();
        Expression ret = parsePowExpression(powExpressionContexts.get(0), lineNumber);
        for (int i = 1; i < length; i++) {
            ret = new ArithmeticExpression(ret, ArithmeticOperator.MULTIPLY, parsePowExpression(powExpressionContexts.get(i), lineNumber));
        }
        return ret;
    }

    private Expression parsePowExpression(com.colinchartier.probo.gen.ProofParser.PowExpressionContext powExpressionContext, int lineNumber) {
        int length = powExpressionContext.negativeAtom().size();
        Expression ret = parseNegativeAtom(powExpressionContext.negativeAtom(0), lineNumber);
        for (int i = 1; i < length; i++) {
            ret = new ArithmeticExpression(ret, ArithmeticOperator.EXPONENTIATE, parseNegativeAtom(powExpressionContext.negativeAtom(i), lineNumber));
        }
        return ret;
    }

    private Expression parseNegativeAtom(com.colinchartier.probo.gen.ProofParser.NegativeAtomContext negativeAtomContext, int lineNumber) {
        if (negativeAtomContext.MINUS() != null) {
            return new NegationExpression(parseAtom(negativeAtomContext.atom(), lineNumber));
        } else {
            return parseAtom(negativeAtomContext.atom(), lineNumber);
        }
    }

    private Expression parseAtom(com.colinchartier.probo.gen.ProofParser.AtomContext atom, int lineNumber) {
        if (atom.expression() != null) {
            return parseExpression(atom.expression(), lineNumber);
        }
        if (atom.variable() != null) {
            return parseVariable(atom.variable(), lineNumber);
        }
        if (atom.scientific() != null) {
            return parseScientific(atom.scientific(), lineNumber);
        }
        throw new AssertionError("An atom wasn't an expression, variable, or constant.");
    }

    private Expression parseVariable(com.colinchartier.probo.gen.ProofParser.VariableContext variable, int lineNumber) {
        return new VariableExpression(variable.getText());
    }

    //in the comments, numbers refer to 1.2E3.4
    private Expression parseScientific(com.colinchartier.probo.gen.ProofParser.ScientificContext scientific, int lineNumber) {
        return new NumericExpression(new BigDecimal(scientific.getText()));
    }

}
