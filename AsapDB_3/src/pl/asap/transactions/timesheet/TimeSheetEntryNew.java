package pl.asap.transactions.timesheet;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.asap.entity.Config;
import pl.asap.entity.Lista;
import pl.asap.entity.TimeSheetEntity;

public class TimeSheetEntryNew {
	
	public TimeSheetEntryNew(int idPostepowanie, String nrSap, String dateEntry, int timePassed) {
		
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
		
		TimeSheetEntity entry = new TimeSheetEntity(nrSap, dateEntry, timePassed);
		
		
		lista.addEntry(entry);
		session.persist(entry);
		session.getTransaction().commit();
		factory.close();
	}

	public static void main(String[] args) {
		new TimeSheetEntryNew(37, "ZZ/PLI0003499", "25.07.2019", 3);
		new TimeSheetEntryNew(37, "PZ/0000008878", "29.07.2019", 3);

	}

}
