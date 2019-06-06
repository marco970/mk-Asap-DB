package pl.asap.entity;

import java.lang.reflect.Field;

public abstract class EntityBase {
	
	
	public Object[] getArray()	{
		//zwraca nazwy pól klasy entity
		Class c = getClass();
		Field[] fields = c.getDeclaredFields();
		int length = fields.length;
		Object[] array = new Object[length-1];
		for (int i =0; i<= length -2; i++)	{
			try {
				String[] a = null;
				if (i+1<= length -1) {
					a = fields[i + 1].toString().split("[.]{1}");
					int k = a.length;
					array[i] = a[k-1];
			}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		return array;
	}
}
