package codeGenerator.actions.ifStatement;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.instructions.Instruction;
import codeGenerator.instructions.Opcode;
import codeGenerator.instructions.operands.Operand;
import codeGenerator.instructions.operands.OprType;
import exceptions.SemanticErrorException;

public class IfJmp extends Action{

	public IfJmp(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		Operand cond = (Operand) semanticStack.pop();
		if(cond.oprType!=OprType.BOOL){
			scanner.decreaseLookAhead(2);
			throw new SemanticErrorException("Not a boolean expression", scanner.getLine());
		}
		Instruction jmp = new Instruction(Opcode.JUMP_ZERO, cond, (Operand)null);
		cg.addInstruction(jmp);
		semanticStack.add(jmp);
//		System.err.println("if jmp " + semanticStack);
	}

}
