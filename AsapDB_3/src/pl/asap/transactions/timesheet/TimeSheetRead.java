package pl.asap.transactions.timesheet;

import java.util.ArrayList;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.asap.entity.Config;
import pl.asap.entity.Lista;
import pl.asap.entity.Notes;
import pl.asap.entity.TimeSheetEntity;
import pl.asap.transactions.TransBlank;

public class TimeSheetRead {
	
	private ArrayList<TimeSheetEntity> entries;
	private int month;

	public TimeSheetRead(int idPostepowanie, int month) {
		
		// TODO Auto-generated constructor stub
		
		this.entries= new ArrayList<>();
		this.month = month;
		
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
		System.out.println(lista.toString());
		
		for(TimeSheetEntity entry: lista.getEntries())	{
			
			if (checkMonth(entry, month)) {
				System.out.println(entry.getDateEntry().substring(3, 5));
				entries.add(entry);
			}
		}
		
		System.out.println(entries.toString());
		
		session.getTransaction().commit();
		factory.close();
		
	}
	public boolean checkMonth(TimeSheetEntity entry, int month)	{
		System.out.println();
		if (entry.getDateEntry().substring(3, 5).equals(monthString(month))) return true;
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
		new TimeSheetRead(10, 7);

	}

}
