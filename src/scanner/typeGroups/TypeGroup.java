package scanner.typeGroups;

abstract class TypeGroup{

	abstract boolean contains(char x);
	
	public boolean equals(Object obj) {
		return contains((Character) obj);
	}
	
}
