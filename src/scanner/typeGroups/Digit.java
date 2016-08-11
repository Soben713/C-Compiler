package scanner.typeGroups;

public class Digit extends TypeGroup {
	boolean contains(char c){
		if (c>='0' && c<='9')
			return true;
		return false;
	}
}
