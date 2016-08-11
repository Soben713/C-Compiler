package codeGenerator.actions.expr.unaryOp;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.instructions.Opcode;
import codeGenerator.instructions.operands.Operand;
import codeGenerator.instructions.operands.OprLevel;
import codeGenerator.instructions.operands.OprType;
import exceptions.SemanticErrorException;

public class Not extends Action{

	public Not(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		Operand o = (Operand) semanticStack.pop();
		Operand ans = new Operand(OprLevel.LOCAL_DIRECT, OprType.BOOL, symbolTableManager.getCurrentMethod().getLocalMemory(OprType.BOOL));
		cg.addInstruction(Opcode.LOGICAL_NOT, o, ans);
		semanticStack.push(ans);
		
	}

}
