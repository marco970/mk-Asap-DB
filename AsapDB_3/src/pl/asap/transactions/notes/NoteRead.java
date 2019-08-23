package pl.asap.transactions.notes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.asap.entity.Config;
import pl.asap.entity.Lista;
import pl.asap.entity.Notes;
import pl.asap.entity.TimeSheetEntity;
import pl.asap.transactions.timesheet.TimeSheetRead;

public class NoteRead {
	
	private static int idPostepowanie;
	private ArrayList<Notes> notes;
	//private static Lista lista = new Lista();
	
//	public NoteRead() {
//		Configuration conf = new Configuration();
//		Config config = new Config();
//		conf.configure(config.getHibernateXML());
//		config.setAnnotatedClass(conf);
//
//		SessionFactory factory = conf.buildSessionFactory();
//		Session session = factory.getCurrentSession();
//		String getAllNotes = "from Notes";
//		
//		session.beginTransaction();		
//		Query query = session.createQuery(getAllNotes);
//		Lista lista = (Lista) query.getSingleResult();		
//		System.out.println(lista.toString());
//		
//		for(Notes note: lista.getNotes() )	{
//			
//			notes.add(note);
//			System.out.println(note.toString());
//		}
//		
//		System.out.println("yyyyyyyyyyyyyyyyy-> "+notes.toString());
//		
//		session.getTransaction().commit();
//		factory.close();
//	}

	public NoteRead(int idPostepowanie) {
		this.idPostepowanie = idPostepowanie;
		notes = new ArrayList<Notes>();
		Configuration conf = new Configuration();
		Config config = new Config();
		conf.configure(config.getHibernateXML());
		config.setAnnotatedClass(conf);

		SessionFactory factory = conf.buildSessionFactory();
		Session session = factory.getCurrentSession();
		String getPostepowanie = "select c from Lista c where c.idPostepowanie="+idPostepowanie;
		session.beginTransaction();		
		Query query = session.createQuery(getPostepowanie);
		Lista lista = (Lista) query.getSingleResult();		
//		System.out.println(lista.toString());
		
		for(Notes note: lista.getNotes() )	{
			
			notes.add(note);
			System.out.println(note.toString());
		}
		
		System.out.println("xxxxxxxxxxxxxxx-> "+notes.toString());
		
		session.getTransaction().commit();
		factory.close();
	}
	public ArrayList<Notes> getNotes()	{
		return notes;
	}

	public static void main(String[] args) {
		new NoteRead(45);
	}

}
