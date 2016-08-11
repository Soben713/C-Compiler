package codeGenerator.actions.forStatement;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.instructions.Instruction;
import codeGenerator.instructions.operands.OprType;
import codeGenerator.utils.For;
import exceptions.SemanticErrorException;

public class MoveDown extends Action{

	public MoveDown(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		if(cg.codeStack.lastElement().operands.get(1).oprType!=OprType.INT){
			scanner.decreaseLookAhead(2);
			throw new SemanticErrorException("Thrird statement of for must be an integer assignment", scanner.getLine());
		}
		For f = (For) semanticStack.get(semanticStack.size()-2);
		while(cg.codeStack.size() > f.codeStackSizeAfterCondition){
			Instruction ins = cg.codeStack.pop();
			f.stepInstructions.add(0, ins);
		}
	}

}
