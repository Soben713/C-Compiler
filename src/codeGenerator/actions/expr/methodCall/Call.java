package codeGenerator.actions.expr.methodCall;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.utils.MethodCall;
import exceptions.SemanticErrorException;

public class Call extends Action{

	public Call(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		MethodCall mc = (MethodCall) semanticStack.pop();
		mc.generateCode(false);
	}

}
