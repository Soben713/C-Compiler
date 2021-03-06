package codeGenerator.actions.expr.basic;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.instructions.operands.Operand;
import codeGenerator.instructions.operands.OprLevel;
import codeGenerator.instructions.operands.OprType;
import exceptions.SemanticErrorException;

public class PushFloat extends Action{

	public PushFloat(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		scanner.decreaseLookAhead(2);
		semanticStack.push(new Operand(OprLevel.IMMEDIATE, OprType.FLOAT, scanner.getLexeme()));
		scanner.increaseLookAhead(2);
	}

}
