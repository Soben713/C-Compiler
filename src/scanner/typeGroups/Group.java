package scanner.typeGroups;

public class Group {
	public static Whitespace WHITESPACE = new Whitespace();
	public static Digit DIGIT = new Digit();
	public static Letter LETTER = new Letter();
	public static LetterAndUnderline LETTER_AND_UNDERLINE = new LetterAndUnderline();
	public static Eof EOF = new Eof();
	public static All ALL = new All();
	
}
