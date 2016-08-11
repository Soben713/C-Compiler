package codeGenerator.actions.ifStatement;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.instructions.Instruction;
import codeGenerator.instructions.operands.Operand;
import exceptions.SemanticErrorException;

public class FillElseJmp extends Action{

	public FillElseJmp(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		Instruction ins = (Instruction) semanticStack.pop();
		Operand dest = Operand.getImmediate(cg.codeStack.size());
		ins.operands.set(0, dest);
//		System.err.println("fill else jmp " + semanticStack);
	}

}
