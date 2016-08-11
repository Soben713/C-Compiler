package parser;

import java.util.Stack;
import exceptions.AcceptException;
import exceptions.CompileException;
import exceptions.ParsingErrorException;
import exceptions.SemanticRecovarableErrorException;


public class ParseTableCell {
	public static enum ParseAction {
		ERROR, 
		SHIFT, 
		REDUCE, 
		ACCEPT,
		GOTO
	}
	ParseAction action;
	int index;
	ParseTable parseTable;
	Parser parser;
	
	public ParseTableCell(Parser parser, ParseAction action, int index, ParseTable parseTable) {
		this.action = action;
		this.index = index;
		this.parseTable = parseTable;
		this.parser = parser;
	}

	@Override
	public String toString() {
		return "[action=" + action + ", index=" + index + "]";
	}

	public boolean runAction(Stack<Integer> parseStack) throws CompileException {
//		System.out.println("Action: "+action+" Index: "+index);
		switch (action){
			case ACCEPT:
				throw new AcceptException();
			case ERROR:
				throw new ParsingErrorException();
			case SHIFT:
				parseStack.push(index);
				break;
			case REDUCE:
//				What should I do? well, you should reduce by i'ths rule. Meaning you should find (i-1)'s rule LHS and then its column and its goto
				int RHSSize = parseTable.lengthOfRHS.get(index-1);
				for(int i=0; i<RHSSize; i++)
					parseStack.pop();
				String LHS = parseTable.LHSList.get(index-1);
				int columnOfLHS = parseTable.tokenToColumnId(LHS);
				try{
					parser.codeGenerator.nonTerminalToActionFactory.getActionOf(LHS).run();
				} catch (SemanticRecovarableErrorException e){
					System.err.println("[Recovered semantic error]:" + e.getMessage());
				}
				parseStack.push(parseTable.table[parseStack.lastElement()][columnOfLHS].index);
		default:
			break;
		}
		return false;
	}
}
