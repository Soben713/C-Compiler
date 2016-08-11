package codeGenerator.symbolTable.symbol;

import java.util.ArrayList;

import codeGenerator.instructions.operands.Operand;
import codeGenerator.instructions.operands.OprLevel;
import codeGenerator.instructions.operands.OprType;
import codeGenerator.symbolTable.Scope;

public class Variable {
	public Scope scope;
	public String name;
	public int line;
	public OprType type;
	public OprLevel level;
	boolean isArray = false;
	public ArrayList<Operand> dimensions = new ArrayList<Operand>();
	public Integer address = null;
	
	public Variable(OprType type, String lexeme, int line) {
		this.name = lexeme;
		this.line = line;
		this.type = type;
	}
	
	public void setScope(Scope scope){
		this.scope = scope;
	}
	public boolean equals(Object obj) {
		if(obj instanceof String)
			return this.name.equals((String)obj);
		else if(obj instanceof Variable)
			return this.name.equals(((Variable)obj).name);
		return false;
	}	
	
	@Override
	public String toString() {
		String ret = "(" + type + ", " + name;
		if(this.address!=null)
			ret += ", address:" + address;
		if(this.isArray)
			ret += ", dimension:" + dimensions;
		ret+=")";
		return ret;
	}
	
	public int getSize(){
		int size = 1;
		if(isArray)
			for(int i=0; i<dimensions.size(); i++)
				size*=(Integer)dimensions.get(i).value;
		return size;
	}
	
	public void setAddress(Integer address) {
		this.address = address;
	}

	public void makeArray(ArrayList<Operand> dimensions){
		this.dimensions = dimensions;
		this.isArray = true;
	}
}
