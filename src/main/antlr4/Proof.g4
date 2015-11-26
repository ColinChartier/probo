grammar Proof;

proof
	: (proofline (LINE_BREAK|EOF))+
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
	: let WS variable WS in WS set (suchthat multiequation)? justification?
	;

let
    : 'let'
    ;

in
    : 'in'
    | '\\in'
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
    : gt
    | lt
    | eq
    | geq
    | leq
    ;

gt
    : '>'
    ;
lt
    : '<'
    ;
eq
    : '='
    ;
leq
    : '<='
    | '\\leq'
    ;
geq
    : '>='
    | '\\geq'
    ;
	
set
	: LETTER
	| '\\mathbb{' LETTER '}'
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
    : integer
    | integer POINT integer
    | POINT integer
    ;

integer
    : DIGIT+
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
    : '\r'? '\n'|'\r'
    ;

WS
    : [ \t]+
    ;