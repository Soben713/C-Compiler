package codeGenerator.actions.expr.operation;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.instructions.Opcode;
import codeGenerator.instructions.operands.Operand;
import codeGenerator.instructions.operands.OprLevel;
import codeGenerator.instructions.operands.OprType;
import exceptions.SemanticErrorException;

public abstract class BinaryOperateReturnBool extends Action{

	public Operand o2;
	public Operand o1;
	public Opcode opcode;
	
	public BinaryOperateReturnBool(CodeGenerator codeGenerator) {
		super(codeGenerator);
		o2 = (Operand) semanticStack.pop();
		opcode = (Opcode) semanticStack.pop();
		o1 = (Operand) semanticStack.pop();
	}

	public abstract void checkCompileErrors() throws SemanticErrorException;
	
	@Override
	public void run() throws SemanticErrorException {
		checkCompileErrors();
		Operand ans = new Operand(OprLevel.LOCAL_DIRECT, OprType.BOOL, symbolTableManager.getCurrentMethod().getLocalMemory(OprType.BOOL));
		cg.addInstruction(opcode, o1, o2, ans);
		semanticStack.push(ans);
	}

}
