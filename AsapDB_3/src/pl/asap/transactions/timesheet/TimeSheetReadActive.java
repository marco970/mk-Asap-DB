package pl.asap.transactions.timesheet;

import java.util.ArrayList;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.asap.entity.Config;

public class TimeSheetReadActive {
	
	private ArrayList<String[]> entries;
	private int month;

	
	public TimeSheetReadActive() {
			
		this.entries = new ArrayList<>();
		
		Configuration conf = new Configuration();
		Config config = new Config();
		conf.configure(config.getHibernateXML());
		config.setAnnotatedClass(conf);
		
		SessionFactory factory = conf.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		String hql2 = "select l.ZZ, l.Nazwa, l.PZ, l.dsPZ, l.Status, l.idPostepowanie "
				+ "from Lista as l "
				+ "where l.PZ <>'' and l.Status = 'aktywne'";
				
		session.beginTransaction();		
		Query query = session.createQuery(hql2);
		
		@SuppressWarnings("unchecked")
		ArrayList<String[]> entryRow = (ArrayList<String[]>) query.getResultList();
		
		for (int i = 0; i<entryRow.size(); i++)	{
			Object[] atom = (Object[]) entryRow.get(i);
//			System.out.println();
//			for (Object el: atom)	{
//				System.out.println("---> "+el.toString());
//			}
			Object[] atomO = new Object[atom.length];
			
			
			ArrayList<Object> atomList = new ArrayList<>();
//			System.out.println(atom.length+" -asd- "+atomS.length);
			if(true)	{
				for (int j = 0; j < atom.length; j++)	{
//					System.out.print(atom[j].toString()+" | ");
					atomList.add(atom[j]);
					//atomS[j] = atom[j].toString();
					
				}
				System.out.println();
				atomList.add(4,"0");
				String[] atomS = new String[atomList.size()];
				for (int k = 0; k < atomList.size(); k++)	{
					atomS[k]=atomList.get(k).toString();
//					System.out.print(atomS[k]+" || ");
				}
				entries.add(atomS);
			}
		}
		session.getTransaction().commit();
		session.close();
		factory.close();
	}
	public ArrayList<String[] > getEntryMatrix()	{
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
		new TimeSheetReadActive();
	}

}
