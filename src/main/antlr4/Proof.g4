grammar Proof;

proof
	: (proofline LINE_BREAK)+
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
    : expression (relative expression)+
    ;

relative
    : '>'
    | '<'
    | '='
    | '>='
    | '<='
    ;
	
set
	: LETTER
	;

expression 
    : expression additionSubtraction expression
	| expression multiplicationDivision expression
	| powExpression+
    ;

powExpression
    : negativeAtom (POW negativeAtom)*
    ;

negativeAtom
    : (MINUS)? atom
    ;

atom
    : scientific
    | variable
    | '(' expression ')'
    ;

scientific
    : number (E number)?
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

DIGIT
    : ('0'..'9')
    ;

LINE_BREAK
    : '\n'|'\r\n'
    ;

WS
    : [ \r\t]+ -> skip
    ;