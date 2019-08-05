/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.asap.transactions.lista;

import org.hibernate.query.Query;

import pl.asap.entity.EntityBase;
import pl.asap.entity.Lista;
import pl.asap.entity.Notes;
import pl.asap.transactions.TransBlank;

/**
 *
 * @author marcin.kuciak
 */
public class UpdateTrans extends TransBlank {
	
	Query<?> query;
    
    public UpdateTrans(EntityBase bean) {
        super(bean);      
    }
    public void updateLogic(String clause, Object field, Object newValue, int id)	{
    	String str = bean.getClass().getSimpleName(); //trzeba wykombinować przekazanie nazwy tabeli
    	Object newValueModified;
    	if (field.equals("isOpen")) newValueModified = Integer.valueOf((String) newValue);
    	else newValueModified = newValue.toString();
        String update = "update "+str+" set "+ field+"=:"+field+ clause;
        System.out.println(">>>>>>>>>  str= "+str  + " field= "+field+ " newValue= "+newValue.toString());
        System.out.println(update+" <<******** val= "+newValue+" id= "+id);        
        query = session.createQuery(update);
        query.setParameter("id", id);
        query.setParameter((String) field, newValueModified);
        int result = query.executeUpdate();
        System.out.println("<><><>"+result);
    }
    public void upadateCell(Object field, Object newValue, int id)	{
    	
    	session.beginTransaction();
    	updateLogic(" where id_postepowanie=:id", field, newValue, id); 	
    	
        session.getTransaction().commit();
        factory.close();
    }
    
    public void updateRow(Object[] savedRow, int id)	{
    	
    	System.out.println("UpdateTrans----updateRow---id---- "+id);
    	session.beginTransaction();
    	
    	Object[] a = ((EntityBase) bean).getArray();

    	for (int i = 0; i<=a.length-2; i++)	{
    		if (savedRow[i]==null) savedRow[i] = "";
    		updateLogic(" where id_postepowanie=:id", a[i], savedRow[i], id);   		
    	}
        session.getTransaction().commit();
        factory.close();
    }
    public void updateNote(Notes notka, int notkaId)	{ // a moze to id Notki???
    	session.beginTransaction();
    	
    	Object[] a = ((EntityBase) bean).getArray();

    	Object[] b = {notka.getNote()+"", notka.getLista()+"", notka.getDateOpen()+"", notka.getDateModified()+"",  notka.getIsOpen()+""};

    	for (int i=0; i<a.length; i++)	{
    		if (i!=1) {
				if (b[i] == null)	b[i] = "";
				System.out.println("^^^"+b[i].toString());
				updateLogic(" where note_id=:id", a[i], b[i], notkaId);
			}
    	}


    	
    	
    	
    	
    	
    }
}
