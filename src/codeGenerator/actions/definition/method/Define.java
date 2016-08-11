package codeGenerator.actions.definition.method;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.instructions.operands.OprType;
import codeGenerator.symbolTable.Method;
import exceptions.SemanticErrorException;

public class Define extends Action{

	public Define(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		scanner.decreaseLookAhead(2);
		@SuppressWarnings("unused")
		String name = (String) semanticStack.pop(); //Pushed name is useless for methods, just pop it
		Method m = new Method(cg.codeStack.size(), (OprType)semanticStack.pop(), scanner.getLexeme(), scanner.getLine());
		
		try {
			cg.symbolTableManager.insertMethod(m);
		} catch (Exception e) {
			throw new SemanticErrorException(e.getMessage(), scanner.getLine());
		}
		scanner.increaseLookAhead(2);
	}

}
