package codeGenerator.actions.expr.unaryOp;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.instructions.Opcode;
import codeGenerator.instructions.operands.Operand;
import codeGenerator.instructions.operands.OprLevel;
import exceptions.SemanticErrorException;

public class Negative extends Action{

	public Negative(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		Operand o = (Operand) semanticStack.pop();
		Operand ans = new Operand(OprLevel.LOCAL_DIRECT, o.oprType, symbolTableManager.getCurrentMethod().getLocalMemory(o.oprType));
		cg.addInstruction(Opcode.UNARY_MINUS, o, ans);
		semanticStack.push(ans);
	}

}
