package codeGenerator.actions.ifStatement;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.instructions.Instruction;
import codeGenerator.instructions.Opcode;
import codeGenerator.instructions.operands.Operand;
import exceptions.SemanticErrorException;

public class ElseJmp extends Action {

	public ElseJmp(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		Instruction jmp = new Instruction(Opcode.JUMP, (Operand)null);
		cg.addInstruction(jmp);
		/*
		 * Top of stack contains the ifjmp destination, we first pop it, push the else destination
		 * and then push it back to the semantic stack, since we'll have to fill ifjmp dest first.
		 */
		Instruction ifJmpDest = (Instruction) semanticStack.pop();
		semanticStack.push(jmp);
		semanticStack.push(ifJmpDest);
//		System.err.println("else jmp " + semanticStack);
	}

}
