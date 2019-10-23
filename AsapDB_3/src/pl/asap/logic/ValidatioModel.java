package pl.asap.logic;

import java.lang.reflect.Field;


public class ValidatioModel {
	 String[] ZZ = {"notNull"};
	 String[] PZ = {"doesExist"};
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
			e.printStackTrace();
		}	
//		
		return a;			
	}	
}
