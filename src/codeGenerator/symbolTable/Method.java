package codeGenerator.symbolTable;

import java.util.ArrayList;
import java.util.Stack;


import codeGenerator.instructions.operands.Operand;
import codeGenerator.instructions.operands.OprLevel;
import codeGenerator.instructions.operands.OprType;
import codeGenerator.symbolTable.Method;
import codeGenerator.symbolTable.symbol.Variable;
import exceptions.SemanticErrorException;

public class Method {
	
	private Stack<Scope> scopeStack = new Stack<Scope>();
	public ArrayList<Variable> arguments = new ArrayList<Variable>();
	public String name;
	public Integer line;
	public OprType returnType;
	public Operand whereToReturn;
	
	public int startInstruction;
	private int localVariablesPointer;
	public Operand activationRecordSize = Operand.getImmediate(0);
	
	public Method(int startInstruction, OprType returnType, String name, int line) {
		this.startInstruction = startInstruction;
		this.name = name;
		this.line = line;
		this.returnType = returnType;
		whereToReturn = getLocalDirectOperand(OprType.INT);
	}
	
	public void addArgument(Variable arg) throws SemanticErrorException{
		if(this.name.equals("main"))
			throw new SemanticErrorException("Method main should not have any arguments", line);
		arg.setAddress(localVariablesPointer);
		this.arguments.add(arg);
		this.setLocalVariablesPointer(localVariablesPointer + arg.type.getSize());
	}
	
	public int getLocalVariablesPointer() {
		return localVariablesPointer;
	}

	private void setLocalVariablesPointer(int localVariablesPointer) {
		this.localVariablesPointer = localVariablesPointer;
		this.activationRecordSize.value = localVariablesPointer;
	}

	public Integer getLocalMemory(OprType type) {
		return getLocalMemory(type, 1);
	}

	public Integer getLocalMemory(OprType type, int number) {
		int ret = localVariablesPointer;
		int typeSize = type.getSize();
		setLocalVariablesPointer(localVariablesPointer+typeSize*number);
		return ret;
	}
	
	public Operand getLocalDirectOperand(OprType type) {
		return new Operand(OprLevel.LOCAL_DIRECT, type, getLocalMemory(type));
	}
	
	public void pushNewScope(){
		scopeStack.push(new Scope());
	}
	
	public void popScope(){
		scopeStack.pop();
	}
	
	public Scope getCurrentScope(){
		return scopeStack.lastElement();
	}
	
	public void insertVariable(Variable s) throws SemanticErrorException{
		scopeStack.lastElement().insertVariable(s);
	}
	
	public Variable lookup(String lexeme){
		Variable ret = null;
		for(int i=scopeStack.size()-1; i>=0; i--){
			ret = scopeStack.get(i).lookup(lexeme);
			if(ret!=null)
				return ret;
		}
		for(int i=0; i<arguments.size(); i++)
			if(arguments.get(i).equals(lexeme))
				return arguments.get(i);
		return null;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof String)
			return this.name.equals((String)obj);
		return this.name.equals(((Method)obj).name);
	}
	
	@Override
	public String toString() {
		return name+"()"+scopeStack;
	}
}
