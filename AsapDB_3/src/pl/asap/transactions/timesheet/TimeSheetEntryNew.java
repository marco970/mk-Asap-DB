package pl.asap.transactions.timesheet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.asap.entity.Config;

public class TimeSheetEntryNew {
	
	public TimeSheetEntryNew(int idPostepowanie, String nrSap, String dateEntry, int TimePassed) {
		
		Configuration conf = new Configuration();
		Config config = new Config();
		conf.configure(config.getHibernateXML());
		config.setAnnotatedClass(conf);
		
		SessionFactory factory = conf.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		
	}
	
	
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
