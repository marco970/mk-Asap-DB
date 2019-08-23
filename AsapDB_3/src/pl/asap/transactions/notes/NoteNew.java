package pl.asap.transactions.notes;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.asap.entity.Config;
import pl.asap.entity.Lista;
import pl.asap.entity.Notes;
import pl.asap.entity.TimeSheetEntity;

public class NoteNew {
	
	
	public NoteNew(int idPostepowanie, Notes note)	{
			
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

			lista.addNote(note);
			session.persist(note);
			session.getTransaction().commit();
			factory.close();
//			System.out.println("tu: "+this.getClass());
		}
}
