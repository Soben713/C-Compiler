package codeGenerator.actions.definition.method;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.instructions.Opcode;
import codeGenerator.instructions.operands.Operand;
import codeGenerator.instructions.operands.OprLevel;
import codeGenerator.instructions.operands.OprType;
import codeGenerator.symbolTable.Method;
import exceptions.SemanticErrorException;

public class ReturnVal extends Action{

	public ReturnVal(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		scanner.decreaseLookAhead(2);
		Method currentMethod = symbolTableManager.getCurrentMethod();
//		if(currentMethod.name.equals("main")){
//			Operand endOfCode = cg.codeStackFinalSize;
//			cg.addInstruction(Opcode.JUMP, endOfCode);
//			return;
//		}
		Operand returnValue;
		try{
			returnValue = (Operand) semanticStack.pop();
		}
		catch(Exception e){
			//Exception here means the sS, top element is'nt an operand
			throw new SemanticErrorException("Bad return type", scanner.getLine());
		}
		if(currentMethod.returnType!=returnValue.oprType)
			throw new SemanticErrorException("Bad return type", scanner.getLine());
		
		Operand globalWhereToReturn = symbolTableManager.getGlobalDirectOperand(OprType.INT);
		cg.addInstruction(Opcode.ASSIGNMENT, currentMethod.whereToReturn, globalWhereToReturn);
		
		cg.addInstruction(Opcode.ASSIGNMENT, returnValue, new Operand(OprLevel.GLOBAL_DIRECT, returnValue.oprType, cg.globalReturnValueAddress));
		
		Operand spValue = currentMethod.getLocalDirectOperand(OprType.INT);
		cg.addInstruction(Opcode.SP_VALUE, spValue);
		cg.addInstruction(Opcode.SUBTRACT, spValue, currentMethod.activationRecordSize, spValue);
		cg.addInstruction(Opcode.ASSIGN_SP, spValue);
		
		cg.addInstruction(Opcode.JUMP, globalWhereToReturn);
		scanner.increaseLookAhead(2);
	}

}
