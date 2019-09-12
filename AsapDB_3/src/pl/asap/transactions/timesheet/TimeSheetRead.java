package pl.asap.transactions.timesheet;

import java.util.ArrayList;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.asap.entity.Config;

public class TimeSheetRead {
	
	private ArrayList<String[]> entries;
	private int month;

	
	public TimeSheetRead(int mounth, int year) {
		
		this.entries = new ArrayList<>();
		this.month = mounth;
		
		Configuration conf = new Configuration();
		Config config = new Config();
		conf.configure(config.getHibernateXML());
		config.setAnnotatedClass(conf);
		
		SessionFactory factory = conf.buildSessionFactory();
		Session session = factory.getCurrentSession();

//		String hql2 = "select t.lista.ZZ, t.lista.Nazwa, t.sapNr, t.dateEntry, t.timePassed, t.lista.Status "
//				+ "from TimeSheetEntity as t "
//				+ "where t.lista.Status = 'aktywne'";
	
//		String hql2 = "select t.lista.ZZ, t.lista.Nazwa, t.sapNr, t.dateEntry, t.timePassed "
//				+ "from TimeSheetEntity as t ";
	
//		String hql2 = "select t.lista.ZZ, t.lista.Nazwa, t.sapNr, t.dateEntry, t.timePassed "
//				+ "from TimeSheetEntity as t ";

		String hql2 = "select t.lista.ZZ, t.lista.Nazwa, t.sapNr, t.dateEntry, t.timePassed, t.lista.Status, t.lista.idPostepowanie, t.entryId "
				+ "from TimeSheetEntity as t ";
				
		session.beginTransaction();		
		Query query = session.createQuery(hql2);
		@SuppressWarnings("unchecked")
		ArrayList<String[]> entryRow = (ArrayList<String[]>) query.getResultList();
		
		for (int i = 0; i<entryRow.size(); i++)	{
			Object[] atom = (Object[]) entryRow.get(i);
			for (Object el: atom)	{
				System.out.println("---> "+el.toString());
			}
			String[] atomS = new String[atom.length];
			System.out.println(atom.length+" -asd- "+atomS.length);
			if(checkDate(atom[3].toString(), month, year))	{
				for (int j = 0; j < atom.length; j++)	{
					System.out.println(atom[j].toString());
					atomS[j] = atom[j].toString();
				}
				entries.add(atomS);
			}
		}
		session.getTransaction().commit();
		for (String[] el1:entries) {
			for (String el2: el1)	{
				System.out.print("|| "+el2);
			}
			System.out.println(" --------- ");
		}
		session.close();
		factory.close();
	}
	public ArrayList<String[]> getEntryMatrix()	{
		return entries; 
	}
	public int getIdPostepowanie(String sapNr, String date)	{
		int entryId = 0;
		int rowNr = 0;
		for (String[] el: entries)	{
			if (el[2].equals(sapNr) && el[3].equals(date))	rowNr = entries.indexOf(el);
		}
		return Integer.parseInt(entries.get(rowNr)[6]);
	}
	public int getIdEntry(String sapNr, String date)	{
		int entryId = 0;
		int rowNr = 0;
		for (String[] el: entries)	{
			if (el[2].equals(sapNr) && el[3].equals(date))	rowNr = entries.indexOf(el);
		}
		return Integer.parseInt(entries.get(rowNr)[7]);

	}
	public boolean checkDate(String date, int month, int year)	{

		if (date.substring(3, 5).equals(numString(month)) && date.substring(6, 10).equals(year+"")) {
			return true;
		}
		else return false;
		
	}
	  public String numString(int num)	{
			String numStr;
			if (num<10) numStr = "0"+ num;
			else numStr = ""+num;
			return numStr;
		}

	public static void main(String[] args) {
		new TimeSheetRead(2, 2019);
	}

}
