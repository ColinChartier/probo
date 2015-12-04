grammar Proof;

proof
	: (proofline (LINE_BREAK|EOF))+
	;
	
proofline
	: assumption
	| definition
	| deduction
	| standalone_multiequation //I.E you can have one line be "Then x=5" and the next be " > 5"
	;
	
assumption
	: assume WS multiequation
	;

assume
    : 'assume'
    | 'Assume'
    ;
	
definition
	: let WS variable (',' variable)* WS? in WS? set (suchthat multiequation)? justification?
	;

let
    : 'let'
    | 'Let'
    ;

in
    : 'in'
    | '\\in'
    ;
	
suchthat
	: 'st'
	| 's.t.'
	| 's.t'
	| 'ST'
	| 'S.T'
	| 'such that'
	| 'Such that'
	;
	
deduction
	: then WS multiequation (WS justification)?
	;

then
    : 'then'
    | 'therefore'
    | 'thus'
    | 'Then'
    | 'Therefore'
    | 'Thus'
    ;
	
justification
	: '[' .*? ']'
	;
	
multiequation  
    : expression (relative expression)+
    ;

standalone_multiequation
    : (relative expression)+

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