package exceptions;

public class SemanticErrorException extends CompileException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SemanticErrorException(String message, int line) {
		super("[Line " + line + "]: " + message);
		// TODO Auto-generated constructor stub
	}


}
