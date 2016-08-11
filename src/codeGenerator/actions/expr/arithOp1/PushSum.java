package codeGenerator.actions.expr.arithOp1;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.instructions.Opcode;
import exceptions.SemanticErrorException;

public class PushSum extends Action{

	public PushSum(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		semanticStack.push(Opcode.ADD);
	}

}
