package pl.asap.transactions.timesheet;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.asap.entity.Config;
import pl.asap.entity.TimeSheetEntity;

public class TSEOpisRead {
	
	private String opis;
	private int entryId;
	
	
	public TSEOpisRead(String sapNr, String date)	{
		
		Configuration conf = new Configuration();
		Config config = new Config();
		conf.configure(config.getHibernateXML());
		config.setAnnotatedClass(conf);
		
		SessionFactory factory = conf.buildSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		try {
			TimeSheetEntity tse = (TimeSheetEntity) session.createQuery("from TimeSheetEntity t "
					+ "where t.sapNr='"+sapNr+"' "
					+ "AND t.dateEntry = '"+ date+"'")
					.getSingleResult();
			this.opis = tse.getOpis();
			this.entryId = tse.getEntryId();
		} catch(Exception e) {
			this.opis = "";
			this.entryId = -1;
		}
		
		session.getTransaction().commit();
		factory.close();
	}

	public String getOpis() {
		return opis;
	}

	public int getEntryId() {
		return entryId;
	}
	
	

}
