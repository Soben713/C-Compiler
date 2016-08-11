import facade.Facade;


public class Main {
	public static void main(String[] args) throws Exception{
		Facade f = new Facade();
		f.start(args);
		if(!f.creatingJarFile)
			f.outputWriter.runVM(System.out);
	}
}
