package codeGenerator.instructions.operands;

public enum OprLevel{	
	GLOBAL_DIRECT("gd"), 
	GLOABAL_INDIRECT("gi"), 
	LOCAL_DIRECT("ld"), 
	LOCAL_INDIRECT("li"), 
	IMMEDIATE("im");
	
	private final String text;
	private OprLevel(final String text) {
        this.text = text;
    }
    @Override
    public String toString() {
        return text;
    }
}
