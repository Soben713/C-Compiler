package codeGenerator.actions.expr.methodCall;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.instructions.operands.Operand;
import codeGenerator.utils.MethodCall;
import exceptions.SemanticErrorException;

public class AddArgument extends Action{

	public AddArgument(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
//		System.err.println("============================");
		MethodCall mc = (MethodCall) semanticStack.get(semanticStack.size()-2);
		mc.arguments.add((Operand) semanticStack.pop());
//		System.err.println("methodcall, after add arg SS:"+semanticStack);
	}

}
