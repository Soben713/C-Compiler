package codeGenerator.actions.forStatement;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.instructions.Opcode;
import codeGenerator.instructions.operands.Operand;
import codeGenerator.utils.For;
import exceptions.SemanticErrorException;

public class RemFromSSAndFillJmpOut extends Action{

	public RemFromSSAndFillJmpOut(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		Operand dest = (Operand) semanticStack.pop();
		For f = (For)semanticStack.pop();
		f.setStepAssignmentAddress(cg.codeStack.size());
		for(int i=0; i<f.stepInstructions.size(); i++)
			cg.codeStack.push(f.stepInstructions.get(i));
		cg.addInstruction(Opcode.JUMP, dest);
		f.setNextInsAddress(cg.codeStack.size());
	}

}
