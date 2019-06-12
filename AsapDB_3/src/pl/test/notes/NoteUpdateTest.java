package pl.test.notes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import pl.asap.entity.Config;
import pl.asap.entity.Lista;
import pl.asap.entity.Notes;

public class NoteUpdateTest {

	public static void main(String[] args) {
		Config config = new Config();
		String hibernateConf = config.getHibernateXML();
		
		
		Configuration conf = new Configuration();
		conf.configure(hibernateConf);
		conf.addAnnotatedClass(Lista.class);
		conf.addAnnotatedClass(Notes.class);
		SessionFactory factory = conf.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		int noteId = 17;
		String note = "ślusarką nie łykniesz górki";
		session.beginTransaction();
		
		String update = "update Notes e set e.note=:note where e.noteId=:noteId";
		Query query = session.createQuery(update);
		query.setParameter("note", note);
		query.setParameter("noteId", noteId);
		query.executeUpdate();

		

		
		session.getTransaction().commit();
//		System.out.println(recordNo);
	
		// zamknięcie obiektu SessionFactory
		factory.close();

	}

}
