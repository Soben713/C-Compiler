package codeGenerator.actions.definition.method;


import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.instructions.Opcode;
import codeGenerator.symbolTable.Method;
import exceptions.SemanticErrorException;

public class MainMethodPreProcess extends Action{

	public MainMethodPreProcess(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		Method currentMethod = symbolTableManager.getCurrentMethod();
		if(currentMethod.name.equals("main"))
			cg.addInstruction(Opcode.ASSIGNMENT, cg.codeStackFinalSize, currentMethod.whereToReturn);
	}

}
