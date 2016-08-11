package codeGenerator.symbolTable;

import java.util.Stack;

import codeGenerator.instructions.operands.Operand;
import codeGenerator.instructions.operands.OprLevel;
import codeGenerator.instructions.operands.OprType;
import codeGenerator.symbolTable.symbol.Variable;
import exceptions.SemanticErrorException;

public class SymbolTableManager {

	/*
	 *  Not exactly the same as what was taught, the Symbol table is inside scopeStack not in a 
	 *  seperate table.
	 */
	private Stack<Method> methodStack = new Stack<Method>();
	private Stack<Variable> globalVariables = new Stack<Variable>();
	
	Method currentMethod;
	public int globalVariablesPointer=0;
	
	public void insertVariable(Variable s) throws SemanticErrorException{
		if(currentMethod!=null){
			currentMethod.insertVariable(s);
			s.setAddress(currentMethod.getLocalMemory(s.type, s.getSize()));
			s.level = OprLevel.LOCAL_DIRECT;
		}
		else{
			globalVariables.push(s);
			s.setAddress(getGlobalMemory(s.type, s.getSize()));
			s.level = OprLevel.GLOBAL_DIRECT;
		}
	}
	
	public Variable lookupVariable(String lexeme) throws Exception{
		Variable ret = null;
		if(currentMethod != null)
			ret = currentMethod.lookup(lexeme); 
		if(ret!=null)
			return ret;
		for(int i=0; i<globalVariables.size(); i++)
			if(globalVariables.get(i).equals(lexeme))
				return globalVariables.get(i);
		throw new Exception("Undefined identifier "+lexeme);
	}
	
	public void insertMethod(Method m) throws Exception{
		for(int i=methodStack.size()-1; i>=0; i--){
			if(methodStack.get(i).equals(m))
				throw new Exception("Redundant declaration of method "+m.name);
		}
		methodStack.push(m);
		currentMethod = m;
	}
	
	public Method lookupMethod(String name) throws Exception{
		for(int i=methodStack.size()-1; i>=0; i--){
			if(methodStack.get(i).equals(name))
				return methodStack.get(i);
		}
		throw new Exception("Undefined method "+name);
	}
	
	public Method getCurrentMethod(){
		return currentMethod;
	}
	
	public void closeCurrentMethod() {
		currentMethod = null;
	}

	public Integer getGlobalMemory(OprType type) {
		return getGlobalMemory(type, 1);
	}

	public Integer getGlobalMemory(OprType type, int number) {
		int ret = globalVariablesPointer;
		int typeSize = type.getSize();
		globalVariablesPointer+=typeSize*number;
		return ret;
	}
	
	public Operand getGlobalDirectOperand(OprType type) {
		return new Operand(OprLevel.GLOBAL_DIRECT, type, getGlobalMemory(type));
	}
	
	@Override
	public String toString() {
		return methodStack.toString();
	}

}
