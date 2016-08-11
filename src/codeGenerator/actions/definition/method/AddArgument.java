package codeGenerator.actions.definition.method;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.instructions.operands.OprLevel;
import codeGenerator.instructions.operands.OprType;
import codeGenerator.symbolTable.symbol.Variable;
import exceptions.SemanticErrorException;

public class AddArgument extends Action{

	public AddArgument(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		scanner.decreaseLookAhead(2);
		String name = scanner.getLexeme();
		OprType type = (OprType) semanticStack.pop();
		Variable arg = new Variable(type, name, scanner.getLine());
		arg.setAddress(symbolTableManager.getCurrentMethod().getLocalMemory(type));
		arg.level = OprLevel.LOCAL_DIRECT;
		symbolTableManager.getCurrentMethod().addArgument(arg);
		scanner.increaseLookAhead(2);
	}

}
