package codeGenerator.actions.assignment;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.instructions.Opcode;
import codeGenerator.instructions.operands.Operand;
import codeGenerator.utils.Location;
import exceptions.SemanticErrorException;

public class Assign extends Action{

	public Assign(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		scanner.decreaseLookAhead(2);
		Operand value = (Operand) semanticStack.pop();
		Location location = (Location) semanticStack.pop();
		Operand locationOperand = location.generateCodeAndGetOperand();
		if(locationOperand.oprType!=value.oprType)
			throw new SemanticErrorException("Cannot assign "+value.oprType+" type to "+locationOperand.oprType+" type", scanner.getLine());
		cg.addInstruction(Opcode.ASSIGNMENT, value, location.generateCodeAndGetOperand());
		scanner.increaseLookAhead(2);
	}

}
