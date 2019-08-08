package pl.asap.transactions.notes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import pl.asap.entity.Config;
import pl.asap.models.NotesModel;

public class NoteUpdate2 {
	
	Config config;
	String hibernateConf;
	Configuration conf;
	SessionFactory factory;
	Session session;
	

	public NoteUpdate2(NotesModel notesModel)	{
	}
	
	public void carryOutUpdate() {
		
		config = new Config();

		conf = new Configuration();
		conf.configure(config.getHibernateXML());
		config.setAnnotatedClass(conf);

		factory = conf.buildSessionFactory();
		session = factory.getCurrentSession();

		session.beginTransaction();
	}
	public void updateNote(int noteId, String note, String dateModified)	{
		carryOutUpdate();
		String update = "update Notes e set "
				+ "e.dateModified=:dateModified,"
				+ "e.note=:note "
				+ "where e.noteId=:noteId";
		Query query = session.createQuery(update);
		query.setParameter("dateModified", dateModified);
		query.setParameter("note", note);
		query.setParameter("noteId", noteId);
		int recordNo = query.executeUpdate();
		session.getTransaction().commit();
		System.out.println(recordNo);
		factory.close();
	}
	public void updateIsOpen(int noteId, boolean isOpen, String dateModified)	{
		carryOutUpdate();
		String update = "update Notes e set "
				+ "e.dateModified=:dateModified,"
				+ "e.isOpen=:isOpen "
				+ "where e.noteId=:noteId";
		Query query = session.createQuery(update);
		query.setParameter("dateModified", dateModified);
		query.setParameter("isOpen", isOpen);
		query.setParameter("noteId", noteId);
		int recordNo = query.executeUpdate();
		session.getTransaction().commit();
		System.out.println(recordNo);
		factory.close();

	}
}

