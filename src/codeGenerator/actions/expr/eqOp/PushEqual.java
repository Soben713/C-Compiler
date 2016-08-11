package codeGenerator.actions.expr.eqOp;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.instructions.Opcode;
import exceptions.SemanticErrorException;

public class PushEqual extends Action{

	public PushEqual(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		semanticStack.push(Opcode.EQUAL);
	}

}
