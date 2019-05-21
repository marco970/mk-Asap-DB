package pl.test.notes;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.asap.entity.Lista;
import pl.asap.entity.Notes;

public class AddNote2 {

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

				
				String getPostepowanie = "select c from Lista c where c.idPostepowanie=37";

				session.beginTransaction();
				
				Query query = session.createQuery(getPostepowanie);
				
				Lista lista = (Lista) query.getSingleResult();
				
				System.out.println(lista.toString());
				
				Notes note = new Notes("to jest notatka /na tu druga linijka", "2019.03.18", "2019.04.17", 0);
				
				
				lista.addNote(note);
				List<Notes> list = lista.getNotes();
				for (Notes el: list)	{
					System.out.println(el.toString());
				}
				
				
				session.persist(note);
				
				
				session.getTransaction().commit();
				factory.close();

	}

}
