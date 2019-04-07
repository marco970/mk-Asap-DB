package pl.asap.transactions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import pl.asap.entity.Lista;

public class SaveTrans extends TransBlank {
	
	private Method method = null;

	public SaveTrans (Object bean)	{
		super(bean);
		
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
		factory.close();
	}
	public void saveRow(Object[] values)	{
		
		int i=0;
		for (Object el: array)	{
			fieldLogic((String) el, values[i].toString());
			i++;
		}
		session.beginTransaction();
		session.save(bean);
		session.getTransaction().commit();
		factory.close();
	}
}
