package codeGenerator.actions.read;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.instructions.Opcode;
import codeGenerator.instructions.operands.OprType;
import codeGenerator.utils.Location;
import exceptions.SemanticErrorException;
import exceptions.SemanticRecovarableErrorException;

public class Int extends Action{

	public Int(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		Location loc = (Location)semanticStack.pop();
		if(loc.getVariable().type==OprType.INT)
			cg.addInstruction(Opcode.READ_INT, loc.generateCodeAndGetOperand());
		else if(loc.getVariable().type==OprType.FLOAT){
			cg.addInstruction(Opcode.READ_FLOAT, loc.generateCodeAndGetOperand());
			scanner.decreaseLookAhead(2);
			int line = scanner.getLine();
			scanner.increaseLookAhead(2);
			throw new SemanticRecovarableErrorException("Using readint's value as a float", line);
		}
		else{
			scanner.decreaseLookAhead(2);
			throw new SemanticErrorException("Readint only reads int", scanner.getLine());
		}
	}

}
