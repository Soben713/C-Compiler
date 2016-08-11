package codeGenerator.actions.expr.eqOp;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.expr.operation.BinaryOperateReturnBool;
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
			throw new SemanticErrorException("Eq operation called on operands with different types", scanner.getLine());
		scanner.increaseLookAhead(2);
	}

}
