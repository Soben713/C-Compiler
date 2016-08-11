package codeGenerator.actions;

import codeGenerator.CodeGenerator;
import exceptions.SemanticErrorException;

public class NoAction extends Action {

	public NoAction(CodeGenerator codeGenerator) {
		super(codeGenerator);
	}

	@Override
	public void run() throws SemanticErrorException {
		
	}

}
