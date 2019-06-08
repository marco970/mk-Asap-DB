package pl.asap.transactions;

import org.hibernate.query.Query;

import pl.asap.entity.EntityBase;
import pl.asap.entity.Notes;

public class UpdateNote extends TransBlank {

	Query<?> query;
	
	
	public UpdateNote(Notes bean) {
		super(bean);
		// TODO Auto-generated constructor stub
	}
	 public void updateLogic(String clause, Object field, Object newValue, int id)	{
	    	String str = bean.getClass().getSimpleName(); //trzeba wykombinować przekazanie nazwy tabeli
	        String update = "update "+str+" set "+ field+"=:"+field+ clause;
	        System.out.println("str= "+str  + " field= "+field+ "newValue= "+newValue);
	        System.out.println("fieldClass = "+field.getClass()+" newValueClass = "+newValue.getClass());
	        System.out.println(update+" ******** val= "+newValue+" id= "+id);
//	        String clause = " where id_postepowanie=:id";       
//	        String update = "update "+str+" set "+ field+"=:"+field+ " where "+clauseLeft+"=:"+clauseRight;
//	        String update2 = "update table =: table set "+ field+ "=:"+field+ " where id_postepowanie=:id";
//	        String update = "update "+str+" set "+ field+"=:"+field+ " where id_postepowanie=:id";
	        
	        query = session.createQuery(update);
	        query.setParameter("id", id);
	        query.setParameter((String) field, newValue.getClass().cast(newValue));
	        query.executeUpdate();
	    }
	 public void updateNote(Notes notka, int notkaId)	{ // a moze to id Notki???
	    	session.beginTransaction();
	    	System.out.println("UpdateTrans ---- updateNote()..." +notkaId);
//	    	System.out.println("Lista jest taka: "+notka.getLista().toString());
	    	
	    	Object[] a = ((EntityBase) bean).getArray();
	    	for (Object el: a)	{
	    		System.out.println("@@@ "+el+" @@ "  );
	    	}
	    	Object[] b = {notka.getNote(), notka.getLista(), notka.getDateOpen(), notka.getDateModified(),  notka.getIsOpen()};

	    	for (int i=0; i<a.length; i++)	{
	    		if (i!=1) {
					if (b[i] == null)
						b[i] = "";
					updateLogic(" where note_id=:id", a[i], b[i], notkaId);
				}
	    	}   	
	    }
	 public void updateNoteField(Object field, Object newValue, int id)	{
		 	session.beginTransaction();
		 	updateLogic(" where note_id=:id", field, newValue, id);
		 	
		 	
		 	
		 	
	 }
	
	
	

}
