package facade;
import codeGenerator.CodeGenerator;
import parser.Parser;
import scanner.Scanner;


public class SuperCollection {
	public Scanner scanner;
	public Parser parser;
	public CodeGenerator codeGenerator;
	
	public SuperCollection(InputReader inputReader) throws Exception{
		scanner = new Scanner(inputReader.input);
		parser = new Parser(inputReader.grammar, inputReader.parseTable);
		codeGenerator = new CodeGenerator();
		
		parser.setCodeGenerator(codeGenerator);
		parser.setScanner(scanner);
		codeGenerator.setParser(parser);
		codeGenerator.setScanner(scanner);
	}
}

