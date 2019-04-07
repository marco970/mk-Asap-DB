package pl.asap.transactions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import pl.asap.entity.Lista;

public class SaveTrans extends TransBlank {
	
	private Method method = null;
	//private Object[] array;
	//private Lista bean;

	public SaveTrans (Object bean)	{
		super(bean);
		
		//this.bean=(Lista) bean;
	}
	public void fieldLogic(String fieldName, String value)	{
		String methodName = "set"+fieldName;
		if(value==null || "".equals(value)) value = "";
		//System.out.println(value +" * ");
		//System.out.println(bean.getClass().toString() +" ** ");
		//System.out.println(methodName +" *methodName " + String.class);
		try {
			method = bean.getClass().getMethod(methodName, String.class);
			//System.out.println(method.toString() +" * ");
		} catch (NoSuchMethodException | SecurityException e) {
		}
		try {
			method.invoke(bean, value);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
		}
	}
	public void saveField(String fieldName, String value)	{
		//System.out.println(value);
		fieldLogic(fieldName, value);
		session.beginTransaction();
		session.save(bean);
		session.getTransaction().commit();
		factory.close();
	}
	public void saveRow(Object[] values)	{
		
		int i=0;
		for (Object el: array)	{
			//System.out.println(values[i].toString() +" - "+i+" -- "+ el);
			fieldLogic((String) el, values[i].toString());
			
			i++;
		}
		session.beginTransaction();
		session.save(bean);
		session.getTransaction().commit();
		factory.close();
	}
}
