package pl.asap.transactions.timesheet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.asap.entity.Config;
import pl.asap.entity.TimeSheetEntity;

public class TimeSheetOpisUpdate {
	
	public TimeSheetOpisUpdate(int entryId, String opis)	{	
		
		Configuration conf = new Configuration();
		Config config = new Config();
		conf.configure(config.getHibernateXML());
		config.setAnnotatedClass(conf);
		
		SessionFactory factory = conf.buildSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		TimeSheetEntity tse = session.get(TimeSheetEntity.class, entryId);
		
		tse.setOpis(opis);
		
		session.getTransaction().commit();
		factory.close();

	}
	

}
