package pl.asap.transactions.timesheet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import pl.asap.entity.Config;

public class TimeSheetEntryUpdate {
	
	Config config;
	String hibernateConf;
	Configuration conf;
	SessionFactory factory;
	Session session;
	
	public TimeSheetEntryUpdate()	{
	}
	public void carryOutUpdate() {
		
		config = new Config();

		conf = new Configuration();
		conf.configure(config.getHibernateXML());
		config.setAnnotatedClass(conf);

		factory = conf.buildSessionFactory();
		session = factory.getCurrentSession();

		session.beginTransaction();
	}
	public void timePassedUpdate(int timePassed, int entryId)	{
		carryOutUpdate();
		String update = "update TimeSheetEntity t set t.timePassed=:timePassed "
				+ "where t.entryId=:entryId";
		Query query = session.createQuery(update);
		query.setParameter("timePassed", timePassed);
		query.setParameter("entryId", entryId);
		int recordNo = query.executeUpdate();
		session.getTransaction().commit();
		System.out.println(recordNo);
		factory.close();
	}
	
	
	
	
	
	

	public static void main(String[] args) {
		new TimeSheetEntryUpdate().timePassedUpdate(2, 9);

	}

}
