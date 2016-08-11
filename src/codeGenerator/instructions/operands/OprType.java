package codeGenerator.instructions.operands;

public enum OprType{
	INT("i", 4), FLOAT("f", 4), BOOL("b", 1), VOID("void", 0);
	
	private final String text;
	private final int size;
	
	private OprType(final String text, final int size) {
		this.text = text;
		this.size = size;
	}
	
	public int getSize(){
		return size;
	}
	@Override
    public String toString() {
        return text;
    }
}
