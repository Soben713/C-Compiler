package codeGenerator.actions.definition.array;

import java.util.ArrayList;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.instructions.operands.Operand;
import codeGenerator.instructions.operands.OprType;
import codeGenerator.symbolTable.symbol.Variable;
import exceptions.SemanticErrorException;

public class Define extends Action{

	public Define(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		scanner.decreaseLookAhead(2);
		@SuppressWarnings("unchecked")
		ArrayList<Operand> dimensions = (ArrayList<Operand>) semanticStack.pop();
		String name = (String) semanticStack.pop();
		OprType type = (OprType) semanticStack.lastElement();
		Variable arr = new Variable(type, name, scanner.getLine());
		arr.makeArray(dimensions);
		symbolTableManager.insertVariable(arr);
		scanner.increaseLookAhead(2);
//		System.out.println("Arr def, " + arr);
	}

}
