/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.asap.transactions;

import org.hibernate.query.Query;

import pl.asap.entity.Lista;

/**
 *
 * @author marcin.kuciak
 */
public class UpdateTrans extends TransBlank {
	
	Query<?> query;
    
    public UpdateTrans(Object bean) {
        super(bean);      
    }
    public void updateLogic(Object field, Object newValue, int id)	{
    	String str = bean.getClass().getSimpleName(); //trzeba wykombinować przekazanie nazwy tabeli

        System.out.println("str= "+str);
    	String update = "update "+str+" set "+ field+"=:"+field+ " where id_postepowanie=:id";

        
        query = session.createQuery(update);
        query.setParameter("id", id);
        query.setParameter((String) field, newValue.toString());
        query.executeUpdate();
    }
    public void upadateCell(Object field, Object newValue, int id)	{
    	
    	session.beginTransaction();
    	updateLogic(field, newValue, id); 	
    	
        session.getTransaction().commit();
        factory.close();
    }
    
    public void updateRow(Object[] savedRow, int id)	{
    	
    	session.beginTransaction();
    	
    	Object[] a = ((Lista) bean).getArray();
    	for (int i = 0; i<=a.length-1; i++)	{
    		if (savedRow[i]==null) savedRow[i] = "a";
    		updateLogic(a[i], savedRow[i], id);
    		
    	}
        session.getTransaction().commit();
        factory.close();
    }
}
