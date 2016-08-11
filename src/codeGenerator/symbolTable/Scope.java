package codeGenerator.symbolTable;

import java.util.ArrayList;

import codeGenerator.symbolTable.symbol.Variable;

import exceptions.SemanticErrorException;

public class Scope {
	ArrayList<Variable> symbolTable = new ArrayList<Variable>();
		
	@Override
	public String toString() {
		String ret = "{";
		for(int j=0; j<symbolTable.size(); j++)
			ret+=symbolTable.get(j).toString();
		ret+= "}";
		return ret;
	}

	public void insertVariable(Variable s) throws SemanticErrorException{
		for(int i=0; i<symbolTable.size(); i++)
			if(s.equals(symbolTable.get(i)))
				throw new SemanticErrorException("Redundant decleration of identifier "+s.name, s.line);
		symbolTable.add(s);
		s.setScope(this);
	}
	
	public Variable lookup(String lexeme) {
		for(int i=0; i<symbolTable.size(); i++)
			if(symbolTable.get(i).equals(lexeme))
				return symbolTable.get(i);
		return null;
	}

}
