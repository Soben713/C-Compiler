package codeGenerator.instructions.operands;


public class Operand {

	public OprLevel level;
	public OprType oprType;
	public Object value;
	
	public Operand(OprLevel level, OprType oprType, Object value) {
		this.level = level;
		this.oprType = oprType;
		this.value = value;
	}
	
	@Override
	public String toString(){
		return level + "_" + oprType + "_" + value;
	}
	public static Operand getImmediate(Object value){
		/*
		 * Checks the type of value and creates the proper immediate operand
		 */
		OprType type;
		if(value instanceof Integer)
			type = OprType.INT;
		else if(value instanceof Boolean)
			type = OprType.BOOL;
		else if(value instanceof Float)
			type = OprType.FLOAT;
		else{
			System.err.println("Invalid value passed to getImmediate");
			return null;
		}
		return new Operand(OprLevel.IMMEDIATE, type, value);
	}
	
}
