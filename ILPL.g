grammar ILPL;

program:
	declaration program 
	| EOF;

declaration:
	function_declaration |
	type_declaration |
	var_declaration;

type_declaration:
	TYPE IDENT ':' type ';';

type:
	IDENT #BasicIdentify |
	LIST '(' type ')' #RecursiveIdentify
;


var_declaration:
	VAR IDENT ':' type ';';

function_declaration:
	FUN IDENT '(' function_declaration2; 

function_declaration2: 
	argument_list ')' ':' type bigbody ENDFUN #ArgFun | 
	')' ':' type bigbody ENDFUN #NoArgFun;

argument_list: 
	argument argument_list2;

argument_list2: 
	',' argument_list | ;

argument: 
	IDENT ':' type; 

bigbody:
	body;

body: 
	var_declaration body | 
	statement body | ; 

statement : 
	IDENT '=' expression ';' #ASSIGNTAG | 
	IF expression THEN bigbody if_split #IFTAG|
	WHILE expression DO bigbody ENDWHILE #WHILETAG | 
	RETURN expression ';' #RETURNTAG ; 

if_split :
	ELSE bigbody ENDIF #ELSEIF|
	ENDIF #ENDIF;

expression: 
	IDENT expression2 #IDENTEXP|
	STRING #STRINGEXP|
	NIL #NILEXP;

expression2:
	'(' expression3 | ;

expression3:
	expression_list ')' |
	')';

expression_list : 
	expression expression_list2;

expression_list2 :
	',' expression expression_list2 | ;


STRING	: QUOTE .*? QUOTE;
QUOTE 	: '\'';
COMMENT	: '#' .*? '\n' -> channel(HIDDEN);
TYPE 	: 'type';
LIST	: 'list';
VAR 	: 'var';
FUN 	: 'fun';
ENDFUN	: 'endfun';
WHILE	: 'while';
DO 		: 'do';
ENDWHILE: 'endwhile';
IF		: 'if';
THEN	: 'then';
ELSE	: 'else';
ENDIF	: 'endif';
RETURN	: 'return';
NIL		: 'nil';

IDENT 	: [a-zA-Z_][a-zA-Z_0-9]*;

WS 		: [\r\n\t ] -> skip;
