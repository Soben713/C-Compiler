package scanner.typeGroups;

public class Whitespace extends TypeGroup{
	boolean contains(char c){
		if (c==' ' || c=='\n' || c=='\t')
			return true;
		return false;
	}
}
