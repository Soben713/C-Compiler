package codeGenerator.actions;

import java.util.Stack;

import scanner.Scanner;
import codeGenerator.CodeGenerator;
import codeGenerator.instructions.Instruction;
import codeGenerator.symbolTable.SymbolTableManager;
import exceptions.SemanticErrorException;

public abstract class Action {
	public CodeGenerator cg;
	public Scanner scanner;
	public SymbolTableManager symbolTableManager;
	public Stack<Object> semanticStack;
	public Stack<Instruction> codeStack;
	
	public Action(CodeGenerator codeGenerator) {
		this.cg = codeGenerator;
		scanner = codeGenerator.scanner;
		symbolTableManager = codeGenerator.symbolTableManager;
		semanticStack = codeGenerator.semanticStack;
	}
	public abstract void run() throws SemanticErrorException;
}
