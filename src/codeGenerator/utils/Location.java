package codeGenerator.utils;

import java.util.ArrayList;

import codeGenerator.CodeGenerator;
import codeGenerator.instructions.Instruction;
import codeGenerator.instructions.Opcode;
import codeGenerator.instructions.operands.Operand;
import codeGenerator.instructions.operands.OprLevel;
import codeGenerator.instructions.operands.OprType;
import codeGenerator.symbolTable.SymbolTableManager;
import codeGenerator.symbolTable.symbol.Variable;
import exceptions.SemanticErrorException;

public class Location {
	String name;
	boolean isArray = false;
	ArrayList<Operand> dimensions = new ArrayList<Operand>();
	SymbolTableManager symbolTableManager;
	CodeGenerator cg;
	int line;
	
	public Location(CodeGenerator codeGenerator) {
		this.symbolTableManager = codeGenerator.symbolTableManager;
		this.cg = codeGenerator;
	}
	public void setLine(int line){
		this.line = line;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void addDimension(Operand dim) {
		this.isArray = true;
		this.dimensions.add(dim);
	}
	public Variable getVariable() throws SemanticErrorException{
		try{
			return (Variable)symbolTableManager.lookupVariable(name);
		} catch(Exception e){
			throw new SemanticErrorException(e.getMessage(), line);
		}
	}
	public Operand generateCodeAndGetOperand() throws SemanticErrorException{
		if(!isArray){
			if(this.getVariable().dimensions.size()>0)
				throw new SemanticErrorException(this.getVariable().name + " is an array, but treated as a simple variable", line);
			return new Operand(this.getVariable().level, this.getVariable().type, this.getVariable().address);
		}
		if(dimensions.size()!=this.getVariable().dimensions.size())
			throw new SemanticErrorException("Incorrect number of dimensions specified for array", cg.scanner.getLine());
		Variable variable = this.getVariable();
		//retDirect will be a pointer to the address of this location
		Operand retDirect = new Operand(OprLevel.LOCAL_DIRECT, OprType.INT, symbolTableManager.getCurrentMethod().getLocalMemory(OprType.INT));
		Operand index = new Operand(OprLevel.LOCAL_DIRECT, OprType.INT, symbolTableManager.getCurrentMethod().getLocalMemory(OprType.INT));
		Operand tmpInt = new Operand(OprLevel.LOCAL_DIRECT, OprType.INT, symbolTableManager.getCurrentMethod().getLocalMemory(OprType.INT));
		Operand tmpBool = new Operand(OprLevel.LOCAL_DIRECT, OprType.BOOL, symbolTableManager.getCurrentMethod().getLocalMemory(OprType.BOOL));
		
		Operand zeroImm = Operand.getImmediate(0);
		Instruction makeIndexZero = new Instruction(Opcode.ASSIGNMENT, zeroImm, index);
		cg.addInstruction(makeIndexZero);
		
		for(int i=0; i<dimensions.size(); i++){
			cg.addInstruction(Opcode.ASSIGNMENT, dimensions.get(i), tmpInt);
			cg.addInstruction(Opcode.GREATER_THAN_EQUAL, dimensions.get(i), variable.dimensions.get(i), tmpBool);
			//tmpBool: true if the dimension exceeds its size
			cg.addInstruction(Opcode.JUMP_ZERO, tmpBool, Operand.getImmediate(cg.codeStack.size()+2));
			cg.addInstruction(Opcode.ERROR, Operand.getImmediate(line), Operand.getImmediate(0));
			for(int j=i+1; j<variable.dimensions.size(); j++){
				Operand dimSize = variable.dimensions.get(j);
				cg.addInstruction(Opcode.MULTIPLY, tmpInt, dimSize, tmpInt);
			}
			cg.addInstruction(Opcode.ADD, index, tmpInt, index);
		}
		
		Operand typeSize = Operand.getImmediate(variable.type.getSize());
		cg.addInstruction(Opcode.MULTIPLY, index, typeSize, index);
		
		Operand startAddr = Operand.getImmediate(variable.address);
		cg.addInstruction(Opcode.ADD, index, startAddr, retDirect);
		Operand retIndirect;
		if(variable.level==OprLevel.LOCAL_DIRECT)
			retIndirect = new Operand(OprLevel.LOCAL_INDIRECT, variable.type, retDirect.value);
		else
			retIndirect = new Operand(OprLevel.GLOABAL_INDIRECT, variable.type, retDirect.value);
		return retIndirect;
	}
	@Override
	public String toString() {
		String ret = "(" + name;
		if(this.isArray)
			ret += ", dimension:" + dimensions;
		ret+=")";
		return ret;
	}

}
