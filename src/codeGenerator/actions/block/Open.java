package codeGenerator.actions.block;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;

public class Open extends Action {

	public Open(CodeGenerator codeGenerator) {
		super(codeGenerator);
	}
	
	@Override
	public void run(){
//		System.err.println("{");
		this.cg.symbolTableManager.getCurrentMethod().pushNewScope();
	}
}
