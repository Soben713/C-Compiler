package codeGenerator.actions.expr.operation;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.instructions.Opcode;
import codeGenerator.instructions.operands.Operand;
import codeGenerator.instructions.operands.OprLevel;
import exceptions.SemanticErrorException;

public abstract class BinaryOperateReturnNum extends Action{

	public Operand o2;
	public Operand o1;
	public Opcode opcode;
	
	public BinaryOperateReturnNum(CodeGenerator codeGenerator) {
		super(codeGenerator);
		o2 = (Operand) semanticStack.pop();	
		opcode = (Opcode) semanticStack.pop();
		o1 = (Operand) semanticStack.pop();
	}

	public abstract void checkCompileErrors() throws SemanticErrorException;
	
	@Override
	public void run() throws SemanticErrorException {
		checkCompileErrors();
		if(o1.oprType!=o2.oprType)
			throw new SemanticErrorException("Operands not having same type", scanner.getLine());
		Operand ans = new Operand(OprLevel.LOCAL_DIRECT, o1.oprType, symbolTableManager.getCurrentMethod().getLocalMemory(o1.oprType));
		cg.addInstruction(opcode, o1, o2, ans);
		semanticStack.push(ans);
	}

}
