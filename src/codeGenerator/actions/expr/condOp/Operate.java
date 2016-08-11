package codeGenerator.actions.expr.condOp;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.expr.operation.BinaryOperateReturnBool;
import codeGenerator.instructions.operands.OprType;
import exceptions.SemanticErrorException;

public class Operate extends BinaryOperateReturnBool{

	public Operate(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void checkCompileErrors() throws SemanticErrorException {
		scanner.decreaseLookAhead(2);
		if(this.o1.oprType!=this.o2.oprType)
			throw new SemanticErrorException("Conditional operation called on operands with different types", scanner.getLine());
		if(o1.oprType!=OprType.BOOL)
			throw new SemanticErrorException("Conditional operation should only be called on boolean operands", scanner.getLine());
		scanner.increaseLookAhead(2);
	}

}
