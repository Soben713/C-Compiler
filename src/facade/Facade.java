package facade;


import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.ProcessBuilder.Redirect;


public class Facade {
	
	public InputReader inputReader;
	public SuperCollection collection;
	public OutputWriter outputWriter;
	public boolean developmentTime = false, creatingJarFile = false;
	
	public void start(String[] args) throws Exception{
		start("src/input.txt", args);
	}
	public void start(String inputPath, String[] args) throws Exception{
		if(developmentTime)
			runParseTableGenerator();
		inputReader = new InputReader(developmentTime, creatingJarFile, inputPath, args);
		collection = new SuperCollection(inputReader);
		outputWriter = new OutputWriter(developmentTime, creatingJarFile, collection, args);
	}
	public static void runParseTableGenerator() throws IOException{
		/*
		 * Creating parse table based on grammar.txt
		 */
		ProcessBuilder pb = new ProcessBuilder("java", "-jar", "ParseTableGenerator.jar", "grammar.grm", "parseTable.txt");
		pb.redirectOutput(Redirect.INHERIT);
		pb.redirectError(Redirect.INHERIT);
		pb.directory(new File("src"));
		Process proc = pb.start();
		Reader reader = new InputStreamReader(proc.getInputStream());
        int ch;
        while ((ch = reader.read()) != -1)
            System.out.print((char) ch);
        reader.close();
	}
}
