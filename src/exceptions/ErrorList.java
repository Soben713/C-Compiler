package exceptions;

import java.util.ArrayList;


public class ErrorList {
	
	public class Error {
		public Error( String messeage, int line) {
			this.message = messeage;
			this.line = line;
		}
		public String message;
		public int line;
	}
	
	public ArrayList<Error> errorList = new ArrayList<Error>();
	
	public String toString(){
		String finalMessage = "";
		for(int i=0; i<errorList.size(); i++)
			finalMessage+="[Line:"+errorList.get(i).line+"]: "+errorList.get(i).message + "\n";
		return finalMessage;
	}

	public void addError(Error error) {
		errorList.add(error);
	}
}