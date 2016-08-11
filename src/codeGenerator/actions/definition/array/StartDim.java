package codeGenerator.actions.definition.array;

import java.util.ArrayList;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.instructions.operands.Operand;
import exceptions.SemanticErrorException;

public class StartDim extends Action{

	public StartDim(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
//		System.out.println("Start reading a dimension");
		semanticStack.push(new ArrayList<Operand>());
	}

}
