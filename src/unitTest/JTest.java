package unitTest;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

import facade.Facade;

public abstract class JTest {
	
	public String testName = null;
	
	@org.junit.Test
	public  void test() throws Exception{
		Facade f = new Facade();
		String inputPath = "src/unitTest/io/"+testName+".input";
		String outputPath = "src/unitTest/io/"+testName+".output";
		
		f.start(inputPath, new String[0]);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		
		f.outputWriter.runVM(ps);
		Scanner outputScanner = new Scanner(new File(outputPath));
		Scanner inputScanner = new Scanner(baos.toString());
		
		while(outputScanner.hasNext() && inputScanner.hasNext()){
			String out = outputScanner.next();
			String in = inputScanner.next();
			System.out.println(out);
			System.err.println(in);
			if(!out.equals(in))
				fail("not equal");
		}
		if(outputScanner.hasNext() || inputScanner.hasNext())
			fail("not equal");
		
		inputScanner.close();
		outputScanner.close();
		
		assertTrue(true);
	}

}
