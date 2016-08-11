package scanner.typeGroups;

public class Eof extends TypeGroup{
	boolean contains(char c){
		if (c==(char)(26))
			return true;
		return false;
	}
}
