package scanner;

import java.io.InputStreamReader;

public class Scanner {
	
	public InputReader inputReader;
	public Analyzer analyzer;
	
	public Scanner(InputStreamReader reader) throws Exception{
		inputReader = new InputReader(reader);
		analyzer = new Analyzer(inputReader);
//		System.out.println(analyzer.tokensArray);
//		System.out.println(analyzer.tokensLineArray);
	}
	public boolean hasNextToken(){
		return analyzer.hasNextToken();
	}
	public String getToken(){
		return analyzer.getToken();
	}
	public int getLine(){
		return analyzer.getLine();
	}
	public String getLexeme(){
		return analyzer.getLexeme();
	}
	public void increaseLookAhead(){
		analyzer.increaseLookAhead(1);
	}
	public void increaseLookAhead(int num){
		analyzer.increaseLookAhead(num);
	}
	public void decreaseLookAhead(int num){
		analyzer.decreaseLookAhead(num);
	}
	public void destroyTokenUnderLookAhead() {
		analyzer.destroyTokenUnderLookAhead();
	}
}
