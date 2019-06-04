/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.asap.transactions;

import org.hibernate.query.Query;

import pl.asap.entity.Lista;
import pl.asap.entity.Notes;

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
        //System.out.println("str= "+str  + " field= "+field+ "newValue= "+newValue);
        String clause = " where id_postepowanie=:id";       
//        String update = "update "+str+" set "+ field+"=:"+field+ " where "+clauseLeft+"=:"+clauseRight;
        String update = "update "+str+" set "+ field+"=:"+field+ " where id_postepowanie=:id";
//        String update2 = "update table =: table set "+ field+ "=:"+field+ " where id_postepowanie=:id";
//        String update = "update "+str+" set "+ field+"=:"+field+ " where id_postepowanie=:id";
        System.out.println(update+" ---------- val= "+newValue);
      
        query = session.createQuery(update);
//        query.setParameter("table", str);
        query.setParameter("id", id);
//        query.setParameter("field", (String) field);
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
    	
    	System.out.println("UpdateTrans----updateRow---id---- "+id);
    	session.beginTransaction();
    	
    	Object[] a = ((Lista) bean).getArray();

    	for (int i = 0; i<=a.length-2; i++)	{
    		if (savedRow[i]==null) savedRow[i] = "";
    		updateLogic(a[i], savedRow[i], id);   		
    	}
        session.getTransaction().commit();
        factory.close();
    }
    public void updateNote(Notes notka, int postepowanieId)	{
    	System.out.println("UpdateTrans ---- updateNote()...");
    	if (bean.getClass().equals("Notes"))	System.out.println("1 "+bean.getClass());
    	else System.out.println("2 "+bean.getClass());
    }
}
