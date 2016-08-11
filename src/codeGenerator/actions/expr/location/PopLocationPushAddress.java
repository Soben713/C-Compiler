package codeGenerator.actions.expr.location;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.utils.Location;
import exceptions.SemanticErrorException;

public class PopLocationPushAddress extends Action{

	public PopLocationPushAddress(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		Location l = (Location) semanticStack.pop();
		semanticStack.push(l.generateCodeAndGetOperand());
	}

}
