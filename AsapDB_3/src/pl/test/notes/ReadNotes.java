package pl.test.notes;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.asap.entity.Lista;
import pl.asap.entity.Notes;

public class ReadNotes {
	
	private static int idPostepowanie;
	
	

	public ReadNotes(int idPostepowanie) {
		this.idPostepowanie = idPostepowanie;
	}



	public static void main(String[] args) {
		
		new ReadNotes(11);
		
		// stworzenie obiektu Configuration
		Configuration conf = new Configuration();
		// wczytanie pliku konfiguracyjnego
		conf.configure("hibernate.cfg.xml");
		// wczytanie adnotacji
		conf.addAnnotatedClass(Lista.class);
		conf.addAnnotatedClass(Notes.class);
		//conf.addAnnotatedClass(Property.class);
		// stworzenie obiektu SessionFactory
		SessionFactory factory = conf.buildSessionFactory();
		// pobranie sesji
		Session session = factory.getCurrentSession();

		
		String getPostepowanie = "select c from Lista c where c.idPostepowanie="+idPostepowanie;

		session.beginTransaction();
		
		Query query = session.createQuery(getPostepowanie);
		
		Lista lista = (Lista) query.getSingleResult();
		
		System.out.println(lista.toString());
		
		for(Notes note: lista.getNotes() )	{
			System.out.println(note.toString());
		}
		
		session.getTransaction().commit();



		// zamkniêcie obiektu SessionFactory
		factory.close();

	}

}
