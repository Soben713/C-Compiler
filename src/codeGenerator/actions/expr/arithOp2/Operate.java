package codeGenerator.actions.expr.arithOp2;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.expr.operation.BinaryOperateReturnNum;
import codeGenerator.instructions.operands.OprType;
import exceptions.SemanticErrorException;

public class Operate extends BinaryOperateReturnNum{

	public Operate(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void checkCompileErrors() throws SemanticErrorException {
		scanner.decreaseLookAhead(2);
		if(this.o1.oprType!=this.o2.oprType)
			throw new SemanticErrorException("Binary operation called on operands with different types", scanner.getLine());
		if(o1.oprType!=OprType.INT && o1.oprType!=OprType.FLOAT)
			throw new SemanticErrorException("ArithOps should only be called on int or float operands", scanner.getLine());
		scanner.increaseLookAhead(2);
	}
	
}
