package parser;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.ParseTableCell.ParseAction;

public class ParseTable {
	
	ParseTableCell[][] table;
	String[] columns;
	ArrayList<Integer> lengthOfRHS = new ArrayList<Integer>(); // lengthOfRHS[i] = number of terminals and nonterminals in rhs of i's rule
	ArrayList<String> LHSList = new ArrayList<String>();
	Parser parser;
	
	public ParseTable(Parser parser, InputStreamReader grammerInput, InputStreamReader parseTableInput) {
		
		this.parser = parser;
		@SuppressWarnings("resource")
		Scanner grammarReader = new Scanner(grammerInput);
		@SuppressWarnings("resource")
		Scanner parseTableReader = new Scanner(parseTableInput);
		
		grammarReader.nextLine();//skip the first line of grammar (the start symbol)
		while(grammarReader.hasNextLine()){
			String tmp = grammarReader.nextLine();
			LHSList.add(tmp.split(" ")[0]);
			lengthOfRHS.add(tmp.split(" ").length-2);
//			System.out.println(LHSList.get(LHSList.size()-1) + ":" + lengthOfRHS.get(lengthOfRHS.size()-1));
		}
		columns = parseTableReader.nextLine().split(" ");
		
		ArrayList<String[]> inputTable = new ArrayList<String[]>(); 
		for(; parseTableReader.hasNextLine();){
			String[] row = parseTableReader.nextLine().split("\\t"); 
			inputTable.add(row);
		}
		
		table = new ParseTableCell[inputTable.size()][columns.length];
		for(int state=0; state<inputTable.size(); state++){
			for(int c=0; c<columns.length; c++){
				ParseTableCell.ParseAction action = null;
				String cellStr = inputTable.get(state)[c];
				switch (cellStr.charAt(1)){
					case 'G':
						action = ParseTableCell.ParseAction.GOTO;
						break;
					case 'E':
						action = ParseTableCell.ParseAction.ERROR;
						break;
					case 'A':
						action = ParseTableCell.ParseAction.ACCEPT;
						break;
					case 'R':
						action = ParseTableCell.ParseAction.REDUCE;
						break;
					case 'S':
						action = ParseTableCell.ParseAction.SHIFT;
						break;
				}
				
				Pattern p = Pattern.compile("[0-9]+");
				Matcher m = p.matcher(cellStr);
			    int index = m.find() ? Integer.parseInt(m.group()) : null;
				table[state][c] = new ParseTableCell(parser, action, index, this);
			}
//			System.out.println(Arrays.deepToString(parseTable[state]));
		}
	}

	public ArrayList<String> getNonEmptyGotoTableColumns(int state){
		ArrayList<String> ret = new ArrayList<String>();
		for(int j=0; j<columns.length; j++){
			if(table[state][j].action == ParseAction.GOTO)
				ret.add(columns[j]);
		}
		return ret;
	}
	
	public int tokenToColumnId(String token){
		for(int i=0; i<columns.length; i++)
			if(columns[i].equals(token))
				return i;
		try {
			throw new Exception("token '" + token + "' not found in parse table");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
		