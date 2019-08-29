package pl.transactions.tests;

import java.util.ArrayList;
import java.util.HashMap;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.asap.entity.Config;

public class TimeSheetReadTest2 {
	
	private ArrayList<String[]> entries;
	private int month;

	
	public TimeSheetReadTest2(int mounth, int year) {
		
		// TODO Auto-generated constructor stub
//		SELECT T.`id_postepowanie` , L.`nazwa` , T.`sap_nr` , T.`date_entry`
//		FROM `time_sheet` AS T
//		LEFT JOIN `lista` AS L ON L.`id_postepowanie` = T.`id_postepowanie`
//		WHERE L.`Status` = 'aktywne'
		
		this.entries = new ArrayList<>();
		this.month = mounth;
		
		Configuration conf = new Configuration();
		Config config = new Config();
		conf.configure(config.getHibernateXML());
		config.setAnnotatedClass(conf);
		
		SessionFactory factory = conf.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
//		Query query = session.createQuery("select m.name, m.department.name from com.baeldung.hibernate.entities.DeptEmployee m");
//		List managers = query.list();
//		Object[] manager = (Object[]) managers.get(0);
//		assertEquals("John Smith", manager[0]);
//		assertEquals("Sales", manager[1]);

		String hql2 = "select t.lista.ZZ, t.lista.Nazwa, t.sapNr, t.dateEntry, t.timePassed from TimeSheetEntity as t ";
				
//		String hql = "from Lista";
				
		session.beginTransaction();		
		Query query = session.createQuery(hql2);
		ArrayList<String[]> entryRow = (ArrayList<String[]>) query.getResultList();
//		entries = (ArrayList<String[]>) wynik;
		
		for (int i = 0; i<entryRow.size(); i++)	{
			Object[] atom = (Object[]) entryRow.get(i);
			String[] atomS = new String[atom.length];
			if(checkDate(atom[3].toString(), month, year))	{
				for (int j = 0; j < atom.length; j++)	{
					atomS[j] = atom[j].toString();
//					System.out.print(" | "+atom[j]+" - "+j);
				}
//				System.out.println("wynik "+ checkDate(atom[3].toString(), month, year));
				entries.add(atomS);
			}
//			System.out.println("entries --> "+i+" - "+entries.get(i));

//			System.out.println(" | kolejna linia -> "+i);
		}
	
		session.getTransaction().commit();
		
		for (String[] el1:entries) {
			for (String el2: el1)	{
				System.out.print("|| "+el2);
			}
			System.out.println(" --------- ");
		}

	
	}
	public ArrayList<String[]> getEntryMatrix()	{
		return entries; 
	}
	public boolean checkDate(String date, int month, int year)	{
//		System.out.println();
//		System.out.println("year-> "+date.substring(6, 10));
		if (date.substring(3, 5).equals(monthString(month)) && date.substring(6, 10).equals(year+"")) {
			
			return true;
		}
		else return false;
		
	}
	public String monthString(int month)	{
		String monthStr;
		if (month<10) monthStr = "0"+month;
		else monthStr = ""+month;
//		System.out.println("month-> "+monthStr);
		return monthStr;
	}

	public static void main(String[] args) {
		new TimeSheetReadTest2(8, 2019);

	}

}
