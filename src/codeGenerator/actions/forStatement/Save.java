package codeGenerator.actions.forStatement;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.instructions.operands.Operand;
import codeGenerator.instructions.operands.OprType;
import exceptions.SemanticErrorException;

public class Save extends Action{

	public Save(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		if(cg.codeStack.lastElement().operands.get(1).oprType!=OprType.INT){
			scanner.decreaseLookAhead(2);
			throw new SemanticErrorException("First assignment of for must be an int value", scanner.getLine());
		}
		Operand o = Operand.getImmediate(cg.codeStack.size());
		semanticStack.push(o);
	}

}
