package pl.test;

import org.hibernate.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.asap.entity.Lista;

public class SaveRow {

	public static void main(String[] args) {
		// stworzenie obiektu Configuration
		Configuration conf = new Configuration();
		// wczytanie pliku konfiguracyjnego
		conf.configure("hibernate.cfg.xml");
		// wczytanie adnotacji
		conf.addAnnotatedClass(Lista.class);
		// stworzenie obiektu SessionFactory
		SessionFactory factory = conf.buildSessionFactory();
		// pobranie sesji
		Session session = factory.getCurrentSession();
		Lista lista = new Lista();
		lista.setZZ("ZZ/PLI0003499");
		session.beginTransaction();
		// zapisanie pracownika
		session.save(lista);
		// zako�czenie transakcji
		session.getTransaction().commit();
		// zamkni�cie obiektu SessionFactory
		factory.close();

	}

}
