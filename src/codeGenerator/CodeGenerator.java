package codeGenerator;

import java.io.PrintStream;
import java.util.Stack;

import codeGenerator.instructions.Instruction;
import codeGenerator.instructions.Opcode;
import codeGenerator.instructions.operands.Operand;
import codeGenerator.instructions.operands.OprLevel;
import codeGenerator.instructions.operands.OprType;
import codeGenerator.symbolTable.SymbolTableManager;
import exceptions.CompileException;
import exceptions.ParsingRecovarableErrorException;
import exceptions.SemanticErrorException;

import parser.Parser;
import scanner.Scanner;

public class CodeGenerator {
	public Scanner scanner;
	public Parser parser;
	public Stack<Object> semanticStack = new Stack<Object>();
	public SymbolTableManager symbolTableManager = new SymbolTableManager();
	public NonTerminalToActionFactory nonTerminalToActionFactory;
	public Stack<Instruction> codeStack = new Stack<Instruction>();
	public Operand codeStackFinalSize = new Operand(OprLevel.IMMEDIATE, OprType.INT, 0);
	public int globalReturnValueAddress; //every function returns its value here
	
	public CodeGenerator() {
		nonTerminalToActionFactory = new NonTerminalToActionFactory(this);
	}
	
	public void addInstruction(Instruction inst){
		codeStack.push(inst);
	}
	public void addInstruction(Opcode opc, Operand... operands) throws SemanticErrorException{
		codeStack.push(new Instruction(opc, operands));
	}
	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public void setParser(Parser parser) {
		this.parser = parser;
	}

	public void generateCode(PrintStream printer) throws CompileException {
		//not necessarily integer, but we need 4 bytes
		globalReturnValueAddress = symbolTableManager.getGlobalMemory(OprType.INT);
		try{
			this.parser.parse();
		} catch (ParsingRecovarableErrorException e){
			System.err.println("[Recovered parsing error(s)]:\n" + e.getMessage());
		}
		codeStackFinalSize.value = codeStack.size();
		printer.println(symbolTableManager.globalVariablesPointer);
		try{
			/*
			 * +1 because of the vm bug, if the main method stack
			 * size is x, and you try to decrease stack size by x in
			 * main method there will be an error (negative address)
			*/
			printer.println(symbolTableManager.lookupMethod("main").getLocalVariablesPointer()+1);
			printer.println(symbolTableManager.lookupMethod("main").startInstruction);
		} catch(Exception e){
			throw new SemanticErrorException("No method named main", 0);
		}
		for(int i=0; i<codeStack.size(); i++)
			printer.println(codeStack.get(i).toString());
	}
	
	public static OprType stringToOprType(String type){
		switch(type){
		case "bool":
			return OprType.BOOL;
		case "int":
			return OprType.INT;
		case "float":
			return OprType.FLOAT;
		case "void":
			return OprType.VOID;
		default:
			System.err.println(type + " is not a type");
			return null;
		}
	}
	public static int getSizeOfType(String type){
		return stringToOprType(type).getSize();
	}
}
