package pl.asap.transactions.timesheet;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.asap.entity.Config;
import pl.asap.entity.TimeSheetEntity;

public class TSEQueryGet {
	
	
	List<TimeSheetEntity> tse;
	
	
	public TSEQueryGet(String sapNr)	{	
		
		tse = new ArrayList<>();
		
		Configuration conf = new Configuration();
		Config config = new Config();
		conf.configure(config.getHibernateXML());
		config.setAnnotatedClass(conf);
		
		SessionFactory factory = conf.buildSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		tse = 
				(List<TimeSheetEntity>) session
		.createQuery("from TimeSheetEntity t where t.sapNr='"+sapNr+"'")
		.getResultList();
		
		System.out.println(sapNr +" --> "+this.getEntryId());

		
		session.getTransaction().commit();
		factory.close();
//		System.out.println("updated");
		for (TimeSheetEntity el: tse)	{
			System.out.println(el.toString());
		}

	}
	public int getEntryId()	{
		System.out.println("tse.size() -"+tse.size());
//		System.out.println("entryId= "+tse.get(0).getEntryId());
		if (tse.size()>0) {
			return tse.get(0).getEntryId();
			
		}
		else return 0;
	}
	public int getTimePassed()	{
		System.out.println("timePassed= "+tse.get(0).getTimePassed());
		return tse.get(0).getTimePassed();
	}
	public static void main(String[] args) {
		new TSEQueryGet("PZ/0000007931") ;
		
	}
	

}
