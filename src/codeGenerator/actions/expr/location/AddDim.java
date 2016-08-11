package codeGenerator.actions.expr.location;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.instructions.operands.Operand;
import codeGenerator.instructions.operands.OprType;
import codeGenerator.utils.Location;
import exceptions.SemanticErrorException;

public class AddDim extends Action{

	public AddDim(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		Operand dim = (Operand) semanticStack.pop();
		Location l = (Location) semanticStack.lastElement();
		if(dim.oprType!=OprType.INT)
			throw new SemanticErrorException("Array dimensions should be integer", scanner.getLine());
		l.addDimension(dim);
//		System.out.println("Added dimension "+dim);
	}

}
