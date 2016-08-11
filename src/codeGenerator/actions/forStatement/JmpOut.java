package codeGenerator.actions.forStatement;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.instructions.Opcode;
import codeGenerator.instructions.operands.Operand;
import codeGenerator.utils.For;
import exceptions.SemanticErrorException;

public class JmpOut extends Action{

	public JmpOut(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		Operand cond = (Operand) semanticStack.pop();
		For f = (For) semanticStack.get(semanticStack.size()-2);
		Operand dest = (f.getNextInsAddress());
		cg.addInstruction(Opcode.JUMP_ZERO, cond, dest);
		
		// We save the codeSize and after the step assignment we pop generated 
		// instructions from codeStack and move them down to the end of for
		f.codeStackSizeAfterCondition = cg.codeStack.size();
	}

}
