package codeGenerator.actions.expr.location;

import codeGenerator.CodeGenerator;
import codeGenerator.actions.Action;
import codeGenerator.symbolTable.symbol.Variable;
import codeGenerator.utils.Location;
import exceptions.SemanticErrorException;

public class Push extends Action{

	public Push(CodeGenerator codeGenerator) {
		super(codeGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws SemanticErrorException {
		scanner.decreaseLookAhead(2);
		Variable s = null;
		try {
			s = symbolTableManager.lookupVariable(scanner.getLexeme());
		} catch (Exception e){
			throw new SemanticErrorException(e.getMessage(), scanner.getLine());
		}
		Location l = new Location(cg);
		l.setLine(scanner.getLine());
		l.setName(s.name);
		semanticStack.push(l);
		scanner.increaseLookAhead(2);
	}

}
