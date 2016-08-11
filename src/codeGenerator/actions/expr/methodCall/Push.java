package codeGenerator.actions.expr.methodCall;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.utils.MethodCall;
import exceptions.SemanticErrorException;

public class Push extends Action{

	public Push(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		scanner.decreaseLookAhead(2);
		MethodCall mc = new MethodCall(cg, scanner.getLexeme(), scanner.getLine());
		semanticStack.push(mc);
		scanner.increaseLookAhead(2);
	}

}
