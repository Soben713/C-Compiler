package scanner;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import scanner.statics.Statics;


public class InputReader {
	String input = "";
	public ArrayList<Integer> lineOf = new ArrayList<Integer>();
	int begin=0, end=0;
	
	public InputReader(InputStreamReader reader) throws IOException{

		int line = 1;
		while(true){
			int c = (reader.read());
			if(c!=-1){
				char ch = (char)(c);
				input += ch;
				lineOf.add(line);
				String n = System.getProperty("line.separator");
				if(n.equals(""+ch))
					line++;
			}
			else
				break;
		}
	}
	public String generateErrorMessage(){
		return "Invalid Token "+getLexeme();
	}
	public int getErrorLine(){
		return lineOf.get(begin);
	}
	public void moveForward(){
		end++;
	}
	public void moveBackward(){
		end--;
	}
	public void reset(){
		begin = end;
	}
	public char current(){
		if(end>=input.length())
			return Statics.EOF;
		return input.charAt(end);
	}
	public String getLexeme(){
		return input.substring(begin, end);
	}
	public int getLexemeLine(){
		return lineOf.get(begin);
	}
}
