package codeGenerator.actions.block;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import exceptions.SemanticErrorException;

public class Close extends Action{

	public Close(CodeGenerator codeGenerator) {
		super(codeGenerator);
	}

	@Override
	public void run() throws SemanticErrorException {
//		System.err.println("}");
		this.cg.symbolTableManager.getCurrentMethod().popScope();
	}
}
