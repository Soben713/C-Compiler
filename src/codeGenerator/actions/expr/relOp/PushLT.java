package codeGenerator.actions.expr.relOp;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.instructions.Opcode;
import exceptions.SemanticErrorException;

public class PushLT extends Action{

	public PushLT(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		semanticStack.push(Opcode.LESS_THAN);
	}

}
