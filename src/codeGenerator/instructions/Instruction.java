package codeGenerator.instructions;

import java.util.ArrayList;

import codeGenerator.instructions.operands.Operand;
import exceptions.SemanticErrorException;

public class Instruction {
	Opcode opcode;
	public ArrayList<Operand> operands = new ArrayList<Operand>();
	
	public Instruction(Opcode opcode, Operand...operands) throws SemanticErrorException{
		this.opcode = opcode;
		for(Operand operand : operands)
			this.operands.add(operand);
//		if(this.operands.size() != opcode.numberOfOperands)
//			throw new SemanticErrorException("Invalid number of operands to "+opcode);
	}
	
	@Override
	public String toString(){
		String ret = opcode.toString();
		for(int i=0; i<operands.size(); i++)
			ret+=" " + operands.get(i);
		return ret;
	}
}
