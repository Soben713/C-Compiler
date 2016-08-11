package scanner.typeGroups;

public class Letter extends TypeGroup{
	boolean contains(char c){
		if ((c>='a' && c<='z') || (c>='A' && c<='Z'))
			return true;
		return false;
	}
}
