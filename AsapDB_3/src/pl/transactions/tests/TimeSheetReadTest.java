package pl.transactions.tests;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.asap.entity.Config;
import pl.asap.entity.Lista;
import pl.asap.entity.Notes;
import pl.asap.entity.TimeSheetEntity;
import pl.asap.transactions.TransBlank;

public class TimeSheetReadTest {
	
	private ArrayList<TimeSheetEntity> entries;
	private int mounth;

	public TimeSheetReadTest(int idPostepowanie, int mounth, int year) {
		
		// TODO Auto-generated constructor stub
		
		this.entries= new ArrayList<>();
		this.mounth = mounth;
		
		Configuration conf = new Configuration();
		Config config = new Config();
		conf.configure(config.getHibernateXML());
		config.setAnnotatedClass(conf);
		
		SessionFactory factory = conf.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		String getPostepowanie = "select c from Lista c where c.idPostepowanie="+idPostepowanie;
		String hql = "from TimeSheetEntity t join ";
		session.beginTransaction();		
		Query query = session.createQuery(hql);
//		
//		Lista lista = (Lista) query.getSingleResult();		
//		System.out.println(lista.toString());
		
//		TimeSheetEntity lista = session.get(TimeSheetEntity.class, 1);
		List<TimeSheetEntity> lista = query.getResultList();
		
		for(TimeSheetEntity entry: lista)	{
			System.out.println("resultCheck -> "+checkDate(entry, mounth, year)+" --> " +mounth+" vs "+entry.getDateEntry().substring(3, 5));
			System.out.println(" --> " +year+" vs "+entry.getDateEntry().substring(6, 10));
			if (checkDate(entry, mounth, year)) {
				System.out.println(entry.getDateEntry().substring(3, 5));
				entries.add(entry);
			}
		}
		
		System.out.println(lista);
		
		session.getTransaction().commit();
		factory.close();
		
	}
	public boolean checkDate(TimeSheetEntity entry, int month, int year)	{
//		System.out.println();
		System.out.println("year-> "+entry.getDateEntry().substring(6, 10));
		if (entry.getDateEntry().substring(3, 5).equals(monthString(month)) && entry.getDateEntry().substring(6, 10).equals(year+"")) return true;
		else return false;
		
	}
	public String monthString(int month)	{
		String monthStr;
		if (month<10) monthStr = "0"+month;
		else monthStr = ""+month;
		System.out.println(monthStr+" <--");
		return monthStr;
	}
	
	
	public static void main(String[] args) {
		new TimeSheetReadTest(35, 8, 2019);

	}

}
