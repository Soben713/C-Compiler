package exceptions;

public class ScannerException extends CompileException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ScannerException(String message, int line) {
		super("[Line " + line + "]: " + message);
	}

}
