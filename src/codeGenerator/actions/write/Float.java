package codeGenerator.actions.write;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.instructions.Opcode;
import codeGenerator.instructions.operands.Operand;
import codeGenerator.instructions.operands.OprType;
import exceptions.SemanticErrorException;
import exceptions.SemanticRecovarableErrorException;

public class Float extends Action{

	public Float(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		Operand o = (Operand)semanticStack.pop();
		if(o.oprType==OprType.FLOAT)
			cg.addInstruction(Opcode.WRITE_FLOAT, o);
		else if(o.oprType==OprType.INT){
			cg.addInstruction(Opcode.WRITE_INT, o);
			scanner.decreaseLookAhead(2);
			int line = scanner.getLine();
			scanner.increaseLookAhead(2);
			throw new SemanticRecovarableErrorException("Trying to write int value, using writefloat", line);
		} 
		else{
			scanner.decreaseLookAhead(2);
			throw new SemanticErrorException("Cannot write a non float value using writefloat", scanner.getLine());
		}
	}

}
