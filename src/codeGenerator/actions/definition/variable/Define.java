package codeGenerator.actions.definition.variable;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.instructions.operands.OprType;
import codeGenerator.symbolTable.symbol.Variable;
import exceptions.SemanticErrorException;

public class Define extends Action {
	public Define(CodeGenerator codeGenerator) {
		super(codeGenerator);
	}

	@Override
	public void run() throws SemanticErrorException{
		scanner.decreaseLookAhead(2);
//		System.err.println(semanticStack);
		String name = (String) semanticStack.pop();
		OprType type = (OprType) semanticStack.lastElement();
		Variable var = new Variable(type, name, scanner.getLine());
//		System.err.println(var);
		symbolTableManager.insertVariable(var);
		scanner.increaseLookAhead(2);
	}
}
