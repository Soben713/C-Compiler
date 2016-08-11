package codeGenerator.actions.expr.methodCall;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.utils.MethodCall;
import exceptions.SemanticErrorException;

public class CallAndPushReturnValue extends Action{

	public CallAndPushReturnValue(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
//		System.err.println("call and ret, ss:"+semanticStack);
		MethodCall mc = (MethodCall) semanticStack.pop();
		mc.generateCode(true);
	}

}
