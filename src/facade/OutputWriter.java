package facade;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.lang.ProcessBuilder.Redirect;

import exceptions.NoOutputException;

public class OutputWriter {
	boolean developmentTime;
	SuperCollection collection;
	
	public OutputWriter(boolean developmentTime, boolean creatingJarFile, SuperCollection collection, String[] args) throws Exception {
		this.developmentTime = developmentTime;
		this.collection = collection;
		File output;
		if(developmentTime || !creatingJarFile){
			output = new File("src/generatedCode.txt");
		} else {
			if(args.length<1)
				throw new NoOutputException();
			output = new File(args[1]);
		}
		PrintStream printer = new PrintStream(output);
		collection.codeGenerator.generateCode(printer);
		printer.close();
	}
	public void runVM(PrintStream p) throws IOException{
		ProcessBuilder pb = new ProcessBuilder("java", "-jar", "vm.jar", "generatedCode.txt");
		pb.redirectError(Redirect.INHERIT);
		pb.directory(new File("src"));
		Process proc = pb.start();
		Reader reader = new InputStreamReader(proc.getInputStream());
        int ch;
        while ((ch = reader.read()) != -1){
            p.print((char) ch);
        }
        reader.close();
        p.close();
	}
}
