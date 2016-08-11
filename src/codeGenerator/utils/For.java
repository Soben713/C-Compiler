package codeGenerator.utils;

import java.util.ArrayList;

import codeGenerator.instructions.Instruction;
import codeGenerator.instructions.operands.Operand;

public class For {
	private Operand nextInsAddress; //Address of the first instruction after for 
	private Operand stepAssignmentAddress; //Address of the last instruction in for
	public ArrayList<Instruction> stepInstructions = new ArrayList<Instruction>();
	public int codeStackSizeAfterCondition;
	
	public For() {
		nextInsAddress = Operand.getImmediate(0);
		stepAssignmentAddress = Operand.getImmediate(0);
	}
	public void setNextInsAddress(Integer address){
		nextInsAddress.value = address;
	}
	public Operand getNextInsAddress(){
		return nextInsAddress;
	}
	public void setStepAssignmentAddress(Integer address){
		stepAssignmentAddress.value = address;
	}
	public Operand getStepAssignmentAddress(){
		return stepAssignmentAddress;
	}
}
