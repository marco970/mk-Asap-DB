package pl.test.notes;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.asap.entity.Lista;
import pl.asap.entity.Notes;

public class AddNote {
	
	public static void main(String[] args) {
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

		
		String getPostepowanie = "select c from Lista c where c.idPostepowanie=12";

		session.beginTransaction();
		
		Query query = session.createQuery(getPostepowanie);
		
		Lista lista = (Lista) query.getSingleResult();
		
		System.out.println(lista.toString());
		
		Notes note1 = new Notes("Jestem Krasnoludkiem!", "2019.03.21", "2019.04.07", 0);
		Notes note2 = new Notes("Jestem Patafianem z Puszczy", "2019.02.11", "2019.04.07", 0);
		Notes note3 = new Notes("Jestem łysym Dziadem z gór", "2019.03.11", "2019.04.27", 0);
		Notes note4 = new Notes("Jestem szalonym stolarzem z Garwolina", "2019.03.18", "2019.04.17", 1);
		
		//Notes(String note, Integer idPostepowanie, String dateOpen, String dateModified, Integer isOpen)
//		
//		Property property2 = new Property("Gdynia", 30);
//		
		lista.addNote(note1);
		lista.addNote(note2);
		lista.addNote(note3);
		lista.addNote(note4);
//		
		session.persist(note1);
		session.persist(note2);
		session.persist(note3);
		session.persist(note4);
		
		session.getTransaction().commit();



		// zamkniêcie obiektu SessionFactory
		factory.close();

	}

}
