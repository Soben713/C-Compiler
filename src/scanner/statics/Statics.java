package scanner.statics;

public class Statics {
	public static enum State {
		START, END, SLASH, DIGIT, DOT, COMMENT, COMMENT_MULTILINE, COMMENT_MULTILINE_STAR,
		DOT_DIGIT, LETTER, DIGIT_DOT, LT, GT, EQ, NOT, AND, OR, WHITESPACE;
	}
	
	public static char EOF = (char)26;
	
	public static String[] KeywordList = {"writeint", "writefloat", "void", "return", "readint", "readfloat",
		"true", "false", "else", "if", "continue", "break", "for", "float", "int", "bool"};
}
