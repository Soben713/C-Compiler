package codeGenerator.actions.definition;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import exceptions.SemanticErrorException;

public class PushType extends Action {

	public PushType(CodeGenerator codeGenerator) {
		super(codeGenerator);
	}

	@Override
	public void run() throws SemanticErrorException {
		scanner.decreaseLookAhead(1);
		semanticStack.push(CodeGenerator.stringToOprType(scanner.getToken()));
//		System.err.println("Push Type:" + scanner.getToken() + " SemanticStack:" + semanticStack);
		scanner.increaseLookAhead(1);
	}
	
}
