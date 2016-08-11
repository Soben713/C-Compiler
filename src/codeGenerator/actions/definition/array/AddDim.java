package codeGenerator.actions.definition.array;

import java.util.ArrayList;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.instructions.operands.Operand;
import codeGenerator.instructions.operands.OprLevel;
import codeGenerator.instructions.operands.OprType;
import exceptions.SemanticErrorException;

public class AddDim extends Action{

	public AddDim(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		scanner.decreaseLookAhead(3);
//		System.out.println("Dim:" + scanner.getLexeme());
		if(Integer.parseInt(scanner.getLexeme()) <= 0)
			throw new SemanticErrorException("Array dimension cannot be nonpositive", scanner.getLine());
		@SuppressWarnings("unchecked")
		ArrayList<Operand> dimArray = (ArrayList<Operand>) semanticStack.lastElement();
		dimArray.add(new Operand(OprLevel.IMMEDIATE, OprType.INT, Integer.parseInt(scanner.getLexeme())));
//		System.out.println(semanticStack);
		scanner.increaseLookAhead(3);
	}

}
