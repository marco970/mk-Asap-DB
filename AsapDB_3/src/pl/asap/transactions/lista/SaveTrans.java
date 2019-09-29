package pl.asap.transactions.lista;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.hibernate.SessionFactory;

import pl.asap.entity.Lista;
import pl.asap.transactions.TransBlank;

public class SaveTrans extends TransBlank {
	
	private Method method = null;

	public SaveTrans (Object bean, SessionFactory factory)	{
		super(bean, factory);
		
	}
	public void fieldLogic(String fieldName, String value)	{
		String methodName = "set"+fieldName;
		if(value==null || "".equals(value)) value = "";
		try {
			method = bean.getClass().getMethod(methodName, String.class);
		} catch (NoSuchMethodException | SecurityException e) {
		}
		try {
			method.invoke(bean, value);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
		}
	}
	public void saveField(String fieldName, String value)	{
		fieldLogic(fieldName, value);
		session.beginTransaction();
		session.save(bean);
		session.getTransaction().commit();
//		factory.close();
	}
	public void saveRow(Object[] values)	{
		
//		int i=0;
//		for (Object el: array)	{
//			fieldLogic((String) el, values[i].toString());
//			i++;
//		}
		for(int i=0; i<=values.length-2; i++)	{
			String val; 
			if (values[i]==null) val="";
			else val = values[i].toString();
				
			fieldLogic((String) array[i], val);
		}
		
		session.beginTransaction();
		session.save(bean);
		session.getTransaction().commit();
//		factory.close();
	}
}
