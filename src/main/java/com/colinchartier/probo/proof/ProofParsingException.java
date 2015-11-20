package com.colinchartier.probo.proof;

public class ProofParsingException extends Exception {
    public ProofParsingException(String message) {
        super(message);
    }

    public ProofParsingException(String message, Throwable cause) {
        super(message, cause);
    }
}
