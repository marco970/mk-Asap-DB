/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.asap.transactions;

import org.hibernate.query.Query;

import pl.asap.entity.EntityBase;
import pl.asap.entity.Lista;
import pl.asap.entity.Notes;

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
        String update = "update "+str+" set "+ field+"=:"+field+ clause;
        System.out.println("str= "+str  + " field= "+field+ "newValue= "+newValue);
        System.out.println(update+" ******** val= "+newValue+" id= "+id);
//        String clause = " where id_postepowanie=:id";       
//        String update = "update "+str+" set "+ field+"=:"+field+ " where "+clauseLeft+"=:"+clauseRight;
//        String update2 = "update table =: table set "+ field+ "=:"+field+ " where id_postepowanie=:id";
//        String update = "update "+str+" set "+ field+"=:"+field+ " where id_postepowanie=:id";
        
        query = session.createQuery(update);
//        query.setParameter("table", str);
        query.setParameter("id", id);
//        query.setParameter("field", (String) field);
        query.setParameter((String) field, newValue.toString());
        query.executeUpdate();
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
    	
    	Object[] a = bean.getArray();

    	for (int i = 0; i<=a.length-2; i++)	{
    		if (savedRow[i]==null) savedRow[i] = "";
    		updateLogic(" where id_postepowanie=:id", a[i], savedRow[i], id);   		
    	}
        session.getTransaction().commit();
        factory.close();
    }
    public void updateNote(Notes notka, int notkaId)	{ // a moze to id Notki???
    	session.beginTransaction();
    	System.out.println("UpdateTrans ---- updateNote()..." +notkaId);
    	System.out.println("Lista iset taka: "+notka.getLista().toString());
    	
    	Object[] a = ((EntityBase) bean).getArray();
    	for (Object el: a)	{
    		System.out.println("@@@ "+el+" @@ "  );
    	}
    	Object[] b = {notka.getNote(), notka.getLista(), notka.getDateOpen(), notka.getDateModified(),  notka.getIsOpen()};
//    	System.out.println(notka.getNote());
//    	System.out.println(notka.getDateOpen());
//    	System.out.println(notka.getDateOpen());
//    	System.out.println(notka.getIsOpen());
//    	note_id
    	for (int i=0; i<a.length; i++)	{
    		if (i!=1) {
				if (b[i] == null)
					b[i] = "";
				updateLogic(" where note_id=:id", a[i], b[i], notkaId);
			}
    	}
//    	updateLogic(" where note_id=:id", a[0], notka.getNote(), notkaId );
//    	updateLogic(" where note_id=:id", a[2], notka.getDateOpen(), notkaId );
//    	updateLogic(" where note_id=:id", a[3], notka.getDateModified(), notkaId );
//    	updateLogic(" where note_id=:id", a[4], notka.getIsOpen(), notkaId );

    	
    	
    	
    	
    	
    }
}
