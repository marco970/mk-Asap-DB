package pl.asap.transactions.timesheet;

import java.util.ArrayList;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.asap.entity.Config;

public class TimeSheetRead2 {
	
	private ArrayList<String[]> entries;
	private int month;

	
	public TimeSheetRead2(int mounth, int year) {
		
		this.entries = new ArrayList<>();
		this.month = mounth;
		
		Configuration conf = new Configuration();
		Config config = new Config();
		conf.configure(config.getHibernateXML());
		config.setAnnotatedClass(conf);
		
		SessionFactory factory = conf.buildSessionFactory();
		Session session = factory.getCurrentSession();

		String hql2 = "select t.lista.ZZ, t.lista.Nazwa, t.sapNr, t.dateEntry, t.timePassed from TimeSheetEntity as t ";
				
		session.beginTransaction();		
		Query query = session.createQuery(hql2);
		@SuppressWarnings("unchecked")
		ArrayList<String[]> entryRow = (ArrayList<String[]>) query.getResultList();
		
		for (int i = 0; i<entryRow.size(); i++)	{
			Object[] atom = (Object[]) entryRow.get(i);
			String[] atomS = new String[atom.length];
			if(checkDate(atom[3].toString(), month, year))	{
				for (int j = 0; j < atom.length; j++)	{
					atomS[j] = atom[j].toString();
				}
				entries.add(atomS);
			}
		}
		session.getTransaction().commit();
	}
	public ArrayList<String[]> getEntryMatrix()	{
		return entries; 
	}
	public boolean checkDate(String date, int month, int year)	{

		if (date.substring(3, 5).equals(monthString(month)) && date.substring(6, 10).equals(year+"")) {
			return true;
		}
		else return false;
		
	}
	public String monthString(int month)	{
		String monthStr;
		if (month<10) monthStr = "0"+month;
		else monthStr = ""+month;
		return monthStr;
	}

	public static void main(String[] args) {
		new TimeSheetRead2(6, 2019);
	}

}
