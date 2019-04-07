package pl.asap.logic;

import java.lang.reflect.Field;


public class ValidatioModel {
	 String[] ZZ = {"checkFormat","toShort", "notNull"};
	 String[] PZ = {"doesExist","checkFormat","toShort"};
	 String[] WP = {"doesExist","checkFormat","toShort", "isPredecessor"};
	 String[] DK = {"doesExist","checkFormat","toShort", "isPredecessor"};
	
	public ValidatioModel()	{}
	
	public Field getField(String name) 
			throws NoSuchFieldException, SecurityException	{
		return this.getClass().getDeclaredField(name);
	}
	public String[] getValArray(String name)	{ 
		String[] a=null;
		try {
			a =(String[]) getClass().getDeclaredField(name).get(this);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return a;			
	}	
}
