package codeGenerator.actions.expr.condOp;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.instructions.Opcode;
import exceptions.SemanticErrorException;

public class PushAnd extends Action{

	public PushAnd(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		semanticStack.push(Opcode.LOGICAL_AND);
	}

}
