grammar Proof;

proof
	: proofline+
	;
	
proofline
	: assumption
	| definition
	| deduction
	;
	
assumption
	: 'assume' multiequation
	;
	
definition
	: 'let' variable 'in' set (suchthat multiequation)? justification?
	;
	
suchthat
	: 'st'
	| 's.t.'
	| 's.t'
	| 'such that'
	;
	
deduction
	: 'then' multiequation justification?
	;
	
justification
	: '[' .*? ']'
	;
	
multiequation  
    : expression (relop expression)+
    ;
	
set
	: CAPITALLETTER
	;

expression 
    : multiplyingExpression (additionSubtraction multiplyingExpression)*
    ;
	
multiplyingExpression  
    : implicitMultiplyingExpression (multiplicationDivision implicitMultiplyingExpression)*
    ;

implicitMultiplyingExpression
	: (powExpression)+
	;

powExpression
    : negativeAtom (POW negativeAtom)?
    ;

negativeAtom
    : (MINUS)? atom
    ;

atom 
    : scientific
    | variable
    | LPAREN expression RPAREN
    ;

scientific
    : number (E number)?
    ;

relop 
    : EQ | GT | LT
    ;

number
    : DIGIT+ (POINT DIGIT+)?
    ;

variable
    : LETTER
    ;

additionSubtraction
    : PLUS|MINUS
    ;

multiplicationDivision
    : TIMES|DIV
    ;
            
LPAREN 
    : '('
    ;

RPAREN 
    : ')'
    ;

PLUS 
    : '+'
    ;

MINUS 
    : '-'
    ;

TIMES 
    : '*'
    ;

DIV 
    : '/'
    ;

GT 
    : '>'
    ;

LT 
    : '<'
    ;

EQ
    : '='
    ;

POINT
    : '.'
    ;

E
    : 'e'
    | 'E'
    ;

POW
    : '^'
    ;

LETTER
    : ('a'..'z') | ('A'..'Z')
    ;
	
CAPITALLETTER
	: ('A'..'Z')
	;

DIGIT
    : ('0'..'9')
    ;

WS 
    : [ \r\n\t]+ -> channel(HIDDEN)
    ;