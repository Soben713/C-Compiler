package codeGenerator.actions.definition;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import exceptions.SemanticErrorException;

public class PopType extends Action {

	public PopType(CodeGenerator codeGenerator) {
		super(codeGenerator);
	}

	@Override
	public void run() throws SemanticErrorException {
//		System.err.print("PopType:" + semanticStack.lastElement());
		semanticStack.pop();
//		System.err.println(" SemanticStack:" + semanticStack);
	}

}
