package codeGenerator;

import java.lang.reflect.Constructor;

import codeGenerator.actions.Action;
import codeGenerator.actions.NoAction;

public class NonTerminalToActionFactory {
	
	CodeGenerator codeGenerator;
	
	public NonTerminalToActionFactory(CodeGenerator codeGenerator){
		this.codeGenerator = codeGenerator;
	}
	
	public Action getActionOf(String symbol) {
		if (symbol.charAt(0)!='@') // Meaning it's not an action symbol
			return new NoAction(codeGenerator);
		/*
		 * Here we create the needed Action class by its name specified in grammar using java reflection
		 */
		symbol = symbol.substring(1);
		try{
			Class<?> clazz = Class.forName("codeGenerator.actions."+symbol);
			Constructor<?> ctor = clazz.getConstructor(CodeGenerator.class);
			return (Action)(ctor.newInstance(new Object[] { codeGenerator }));
		} catch (Exception e){
			System.err.println(e.getMessage());
		}
		return null;
	}
	
}
