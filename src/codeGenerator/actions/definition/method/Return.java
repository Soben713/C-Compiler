package codeGenerator.actions.definition.method;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.instructions.Opcode;
import codeGenerator.instructions.operands.Operand;
import codeGenerator.instructions.operands.OprType;
import codeGenerator.symbolTable.Method;
import exceptions.SemanticErrorException;

public class Return extends Action{

	public Return(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		Method currentMethod = symbolTableManager.getCurrentMethod();
//		if(currentMethod.name.equals("main")){
//			Operand endOfCode = cg.codeStackFinalSize;
//			cg.addInstruction(Opcode.JUMP, endOfCode);
//			return;
//		}
		
		Operand globalWhereToReturn = symbolTableManager.getGlobalDirectOperand(OprType.INT);
		//Pushing currentWhereToReturn to globalWhereToReturn Place, cause the SP is gonna change
		cg.addInstruction(Opcode.ASSIGNMENT, currentMethod.whereToReturn, globalWhereToReturn);
		
		Operand spValue = currentMethod.getLocalDirectOperand(OprType.INT);
		cg.addInstruction(Opcode.SP_VALUE, spValue);
		cg.addInstruction(Opcode.SUBTRACT, spValue, currentMethod.activationRecordSize, spValue);
		cg.addInstruction(Opcode.ASSIGN_SP, spValue);
		
		cg.addInstruction(Opcode.JUMP, globalWhereToReturn);
	}

}
