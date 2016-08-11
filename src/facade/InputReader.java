package facade;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

import exceptions.NoInputException;


public class InputReader {
	public InputStreamReader input, grammar, parseTable;
	private String args[];
	boolean developmentTime, creatingJarFile;
	
	public InputReader(boolean developmentTime, boolean creatingJarFile, String inputPath, String args[]) throws FileNotFoundException, NoInputException{
		this.developmentTime = developmentTime;
		this.creatingJarFile = creatingJarFile;
		this.args = args;
		
		if(developmentTime || !creatingJarFile){
			this.grammar = new FileReader("src/facade/grammar.grm");
			this.parseTable = new FileReader("src/facade/parseTable.txt");
		} else {
			this.grammar = new InputStreamReader(this.getClass().getResourceAsStream("grammar.grm"));
			this.parseTable = new InputStreamReader(this.getClass().getResourceAsStream("parseTable.txt"));
		}
		
		setInput(inputPath);
	}
	public void setInput(String inputPath) throws FileNotFoundException, NoInputException{
		if(this.developmentTime || !this.creatingJarFile){
			this.input = new FileReader(inputPath);
		} else {
			if(args.length<1)
				throw new NoInputException();
			this.input = new FileReader(args[0]);
		}
	}
}
