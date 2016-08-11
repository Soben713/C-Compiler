package codeGenerator.actions.expr.arithOp2;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.instructions.Opcode;
import exceptions.SemanticErrorException;

public class PushMod extends Action{

	public PushMod(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		semanticStack.push(Opcode.MOD);
	}

}
