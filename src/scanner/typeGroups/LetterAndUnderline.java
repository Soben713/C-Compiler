package scanner.typeGroups;

public class LetterAndUnderline extends TypeGroup{
	boolean contains(char c){
		if ((c>='a' && c<='z') || (c>='A' && c<='Z') || (c=='_'))
			return true;
		return false;
	}
}
