package pl.test.notes;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.asap.entity.Lista;
import pl.asap.entity.Notes;

public class NewNote {
	
	
	public NewNote(int idPostepowanie, Notes...args)	{
		
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
		System.out.println(lista.toString());
		for (Notes el: args)	{
			lista.addNote(el);
			session.persist(el);
		}
		session.getTransaction().commit();
		factory.close();
	}

}
