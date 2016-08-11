package exceptions;

public class ParsingErrorException extends ParsingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public ParsingErrorException(ErrorList errorList) {
		super(errorList.toString());
		// TODO Auto-generated constructor stub
	}
	
	public ParsingErrorException() {
		super("Parse error");
	}

	public ParsingErrorException(String string) {
		super(string);
	}

}
