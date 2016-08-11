package codeGenerator.instructions;

public enum Opcode{
	ADD					("+",	3),
	SUBTRACT			("-",	3),
	MULTIPLY			("*",	3),
	DIVIDE				("/",	3),
	MOD					("%",	3), 
	LOGICAL_AND			("&&",	3),
	LOGICAL_OR			("||",	3),
	LESS_THAN			("<",	3),
	GREATER_THAN		(">",	3),
	LESS_THAN_EQUAL		("<=",	3),
	GREATER_THAN_EQUAL	(">=",	3),
	EQUAL				("==",	3),
	NOT_EQUAL			("!=",	3),
	LOGICAL_NOT			("!",	2),
	UNARY_MINUS			("u-",	2),
	ASSIGNMENT			(":=",	2),
	JUMP_ZERO			("jz",	2),
	JUMP				("jmp", 1),
	WRITE_INT			("wi",	1),
	WRITE_FLOAT			("wf",	1),
	READ_INT			("ri",	1),
	READ_FLOAT			("rf",	1),
	PC_VALUE			(":=pc",	1),
	SP_VALUE			(":=sp",	1),
	ASSIGN_SP			("sp:=",	1),
	ERROR				("err",	2)
	;
	private final String code;
	public final int numberOfOperands;
	private Opcode(final String text, final int numberOfOperands) {
        this.code = text;
        this.numberOfOperands = numberOfOperands;
    }
    @Override
    public String toString() {
        return code;
    }
}