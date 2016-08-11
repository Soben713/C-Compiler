package codeGenerator.actions.forStatement;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.utils.For;
import exceptions.SemanticErrorException;

public class AddToSS extends Action{

	public AddToSS(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		For f = new For();
		cg.semanticStack.push(f);
	}

}
