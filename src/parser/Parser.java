package parser;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

import parser.ParseTableCell.ParseAction;

import codeGenerator.CodeGenerator;

import exceptions.AcceptException;
import exceptions.CompileException;
import exceptions.ParsingErrorException;
import exceptions.ErrorList;
import exceptions.ParsingException;
import exceptions.ParsingRecovarableErrorException;

import scanner.Scanner;

public class Parser {
	ParseTable parseTable;
	Stack<Integer> parseStack = new Stack<Integer>();
	CodeGenerator codeGenerator;
	Scanner scanner;
	
	public Parser(InputStreamReader grammerInput, InputStreamReader parseTableInput) throws ParsingException{
		this.parseTable = new ParseTable(this, grammerInput, parseTableInput);
	}
	public void setCodeGenerator(CodeGenerator codeGenerator) {
		this.codeGenerator = codeGenerator;		
	}
	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
	public void parse() throws CompileException{
		parseStack.push(0);
		int tokenLine = scanner.getLine();
		String token = scanner.getToken();
		scanner.increaseLookAhead();
		ErrorList errorList = new ErrorList();
		while(true){
//			System.out.println("Token:"+token+" Line:"+tokenLine);
			int state = parseStack.lastElement();
			ParseTableCell ptcell = parseTable.table[state][parseTable.tokenToColumnId(token)];
			try{
//				System.out.println("Stack:"+parseStack+" Current Token:'"+token+"'");
				ptcell.runAction(parseStack);
				if(ptcell.action!=ParseTableCell.ParseAction.REDUCE){
					if(!scanner.hasNextToken())
						break;
					tokenLine = scanner.getLine();
					token = scanner.getToken();
					scanner.increaseLookAhead();
				}
			}
			catch (AcceptException e){
				break;
			}
			catch (ParsingErrorException e){
				ErrorList.Error error = errorList.new Error("Parse error at token "+token, tokenLine);
				errorList.addError(error);
				ArrayList<String> nonEmptyGotoTableColumns = parseTable.getNonEmptyGotoTableColumns(state);
				//this while pops from parseStack
				while(nonEmptyGotoTableColumns.size() == 0){
					if(parseStack.size()==0)//meaning nothing could be reduced
						throw new ParsingErrorException(errorList);
					state = parseStack.pop();
					nonEmptyGotoTableColumns = parseTable.getNonEmptyGotoTableColumns(state);
				}
				//this while moves the lookahead
				while(true){
					ptcell = parseTable.table[state][parseTable.tokenToColumnId(token)];
					if(ptcell.action==ParseAction.REDUCE){
						int reduceRule = ptcell.index;
						String LHS = parseTable.LHSList.get(reduceRule-1); 
						if(nonEmptyGotoTableColumns.contains(LHS)){
							ptcell.runAction(parseStack);
							break;
						}
					}
					if(!scanner.hasNextToken())
						throw new ParsingErrorException("Unrecoverable parse error");
					scanner.decreaseLookAhead(1);
					scanner.destroyTokenUnderLookAhead();
					tokenLine = scanner.getLine();
					token = scanner.getToken();
					scanner.increaseLookAhead();
				}
			}
			
		}
		if(errorList.errorList.size()>0)
			throw new ParsingRecovarableErrorException(errorList);
		else
			System.out.println("Accepted");
	}
}
