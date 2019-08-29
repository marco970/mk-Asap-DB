package pl.transactions.tests;

import java.util.ArrayList;
import java.util.HashMap;
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
	private ArrayList<Integer> idPostepowanieAktywne;
	private ArrayList<Lista> queryArr;
	private HashMap mapa;
	private int mounth;

	public TimeSheetReadTest(int idPostepowanie, int mounth, int year) {
		
		// TODO Auto-generated constructor stub
		
		this.entries = new ArrayList<>();
		this.mounth = mounth;
		this.idPostepowanieAktywne = new ArrayList<>();
		this.queryArr = new ArrayList<>();
		this.mapa = new HashMap();
		
		Configuration conf = new Configuration();
		Config config = new Config();
		conf.configure(config.getHibernateXML());
		config.setAnnotatedClass(conf);
		
		SessionFactory factory = conf.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		String getActivePostepowanie = "select c from Lista c where c.Status='aktywne'";
//		String hql = "from TimeSheetEntity t join ";
		session.beginTransaction();		
		Query query = session.createQuery(getActivePostepowanie);
//		
		List<Lista> lista = query.getResultList();		
		int i = 0;
		for (Lista el: lista)	{
			
			System.out.println("aaa--> "+el.toString());
			idPostepowanieAktywne.add(el.getIdPostepowanie());
			String getEntries = "select c from Lista c where c.idPostepowanie="+el.getIdPostepowanie();
			Query query2 = session.createQuery(getEntries);
			Lista lista2 =  (Lista) query2.getSingleResult();
//			System.out.println("ccc-> "+lista2.getEntries().toString());
			
			if (!lista2.getEntries().toString().equals("[]")) {
				queryArr.add(lista2);
				mapa.put(el.getIdPostepowanie(), lista2);
				
			}	
			lista2 = null;
			query2 = null;
			i++;
		}
		for (Object elem:mapa.keySet())	{
			System.out.println("bbb-> "+elem+" --> "+((Lista) mapa.get(elem)).getEntries().toString());
		}	
		session.getTransaction().commit();
		session.close();
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
		new TimeSheetReadTest(35, 7, 2019);

	}

}
