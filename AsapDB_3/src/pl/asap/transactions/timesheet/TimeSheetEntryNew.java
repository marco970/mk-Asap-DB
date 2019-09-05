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
//		new TimeSheetEntryNew(27, "ZZ/CPO0007777", "19.05.2019", 3);
//		new TimeSheetEntryNew(27, "PZ/0000009999", "19.05.2019", 2);
//		new TimeSheetEntryNew(27, "WP/CPO0003344", "07.06.2019", 4);
////		33 	ZZ/PLI0004511 	PZ/0000007733 			aktywne 			Książeczka z Hipciem 	przetarg 	PLI 	16.06.2019 	10.08.2019 	
//		new TimeSheetEntryNew(33, "ZZ/PLI0004511", "16.06.2019", 2);
//		new TimeSheetEntryNew(27, "PZ/0000009999", "20.05.2019", 3);
//		new TimeSheetEntryNew(27, "PZ/0000009999", "26.05.2019", 2);
//		34 	ZZ/PLI0004433 	PZ/0000003998 			aktywne 			gąbka do czesania dla łysych 	przetarg 	PLI 	10.08.2019 	10.08.2019 	
//		16 	ZZ/CPO0003498 	PZ/0000002345 			aktywne 	przemiot zak 	Durex 		NULL		11.02.2019 	11.03.2019 	
		new TimeSheetEntryNew(16, "ZZ/CPO0003498", "11.02.2019", 2);
		new TimeSheetEntryNew(16, "PZ/0000002345", "11.03.2019", 3);
//		new TimeSheetEntryNew(34, "PZ/0000003998", "16.08.2019", 2);

	}

}
