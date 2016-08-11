package codeGenerator.actions.definition.method;

import codeGenerator.CodeGenerator;
import codeGenerator.instructions.Opcode;
import codeGenerator.instructions.operands.Operand;
import codeGenerator.instructions.operands.OprType;
import exceptions.SemanticErrorException;

public class DefaultReturn extends Return{

	public DefaultReturn(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		if(symbolTableManager.getCurrentMethod().returnType!=OprType.VOID)
			cg.addInstruction(Opcode.ERROR, Operand.getImmediate(symbolTableManager.getCurrentMethod().line), Operand.getImmediate(1));
		super.run();
		symbolTableManager.closeCurrentMethod();
	}
	
}
