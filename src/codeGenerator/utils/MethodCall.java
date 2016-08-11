package codeGenerator.utils;

import java.util.ArrayList;

import codeGenerator.CodeGenerator;
import codeGenerator.instructions.Opcode;
import codeGenerator.instructions.operands.Operand;
import codeGenerator.instructions.operands.OprLevel;
import codeGenerator.instructions.operands.OprType;
import codeGenerator.symbolTable.Method;
import exceptions.SemanticErrorException;

public class MethodCall {
	private String methodName;
	private int line;
	public ArrayList<Operand> arguments = new ArrayList<Operand>();
	CodeGenerator cg;
	
	public MethodCall(CodeGenerator cg, String methodName, int line){
		this.cg = cg;
		this.methodName = methodName;
		this.line = line;
	}
	
	public Method getMethod() throws SemanticErrorException{
		try{
			return cg.symbolTableManager.lookupMethod(methodName);
		} catch(Exception e){
			throw new SemanticErrorException(e.getMessage(), line);
		}
	}

	public void generateCode(boolean pushReturnValueToSemanticStack) throws SemanticErrorException{
		Method callee = this.getMethod();
		Method caller = cg.symbolTableManager.getCurrentMethod();
		
		if(this.arguments.size() != callee.arguments.size())
			throw new SemanticErrorException("Method "+callee.name+" needs "+callee.arguments.size()+" argument(s)", line);
		for(int i=0; i<arguments.size(); i++)
			if(arguments.get(i).oprType != callee.arguments.get(i).type)
				throw new SemanticErrorException("Invalid argument(s) passed to method "+callee.name, line);
		if(pushReturnValueToSemanticStack && callee.returnType==OprType.VOID)
			throw new SemanticErrorException("Expecting a void function to return something?", line);
		
		Operand newSPValue = caller.getLocalDirectOperand(OprType.INT);
		Operand currentPC = cg.symbolTableManager.getGlobalDirectOperand(OprType.INT);
		
		ArrayList<Operand> globalLevelArguments = new ArrayList<Operand>();
		for(int i=0; i<callee.arguments.size(); i++){
			Operand gmp = cg.symbolTableManager.getGlobalDirectOperand(arguments.get(i).oprType);
			cg.addInstruction(Opcode.ASSIGNMENT, arguments.get(i), gmp);
			globalLevelArguments.add(gmp);
		}
		
		Operand localReturnValue = caller.getLocalDirectOperand(callee.returnType);
		
		cg.addInstruction(Opcode.SP_VALUE, newSPValue);
		cg.addInstruction(Opcode.ADD, newSPValue, callee.activationRecordSize, newSPValue);
		cg.addInstruction(Opcode.ASSIGN_SP, newSPValue);
		
		for(int i=0; i<callee.arguments.size(); i++){
			Operand argLocation = new Operand(OprLevel.LOCAL_DIRECT, callee.arguments.get(i).type, callee.arguments.get(i).address);
			cg.addInstruction(Opcode.ASSIGNMENT, globalLevelArguments.get(i), argLocation);
		}
		
		cg.addInstruction(Opcode.PC_VALUE, currentPC);
		//important: 
		//4 because there are 3 instructions after the above instruction, adding any instruction should increase it
		cg.addInstruction(Opcode.ADD, currentPC, Operand.getImmediate(4), currentPC);
		cg.addInstruction(Opcode.ASSIGNMENT, currentPC, callee.whereToReturn);
		cg.addInstruction(Opcode.JUMP, Operand.getImmediate(callee.startInstruction));
		if(pushReturnValueToSemanticStack){
			Operand globalReturnValue = new Operand(OprLevel.GLOBAL_DIRECT, callee.returnType, cg.globalReturnValueAddress);
			cg.addInstruction(Opcode.ASSIGNMENT, globalReturnValue, localReturnValue);
			cg.semanticStack.push(localReturnValue);
		}
	}
}
