package pl.asap.transactions;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.asap.entity.Lista;
import pl.asap.entity.Notes;

public class ReadNotes {
	
	private static int idPostepowanie;
	private ArrayList<Notes> notes;
	//private static Lista lista = new Lista();
	
	

	public ReadNotes(int idPostepowanie) {
		this.idPostepowanie = idPostepowanie;
		notes = new ArrayList<Notes>();
		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
		conf.addAnnotatedClass(Lista.class);
		conf.addAnnotatedClass(Notes.class);

		SessionFactory factory = conf.buildSessionFactory();
		Session session = factory.getCurrentSession();
		String getPostepowanie = "select c from Lista c where c.idPostepowanie="+idPostepowanie;
		session.beginTransaction();		
		Query query = session.createQuery(getPostepowanie);
		Lista lista = (Lista) query.getSingleResult();		
		//System.out.println(lista.toString());
		
		for(Notes note: lista.getNotes() )	{
			
			notes.add(note);

		}
		
		session.getTransaction().commit();
		factory.close();
	}
	public ArrayList<Notes> getNotes()	{
		return notes;
	}



	public static void main(String[] args) {
		
		new ReadNotes(10);
		


	}

}
