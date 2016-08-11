package codeGenerator.actions.forStatement;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.instructions.Opcode;
import codeGenerator.utils.For;
import exceptions.SemanticErrorException;

public class Continue extends Action{

	public Continue(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		try{
			For f = (For) semanticStack.get(semanticStack.size()-3);
			cg.addInstruction(Opcode.JUMP, f.getStepAssignmentAddress());
		}
		catch(Exception e){
			scanner.decreaseLookAhead(2);
			throw new SemanticErrorException("Continue is not in for", scanner.getLine());
		}
	}

}
