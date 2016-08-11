package codeGenerator.actions.definition;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import exceptions.SemanticErrorException;

public class PushName extends Action{

	public PushName(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		scanner.decreaseLookAhead(1);
		semanticStack.push(scanner.getLexeme());
//		System.out.println("Pushed Name:" + scanner.getLexeme());
		scanner.increaseLookAhead(1);
	}

}
