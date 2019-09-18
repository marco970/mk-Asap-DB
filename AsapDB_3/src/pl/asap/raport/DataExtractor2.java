package pl.asap.raport;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

import pl.asap.models.TimeSheetModel3;

public class DataExtractor2 {
	
	
	
	public DataExtractor2(TimeSheetModel3 tsm3)	{
		
//		i - wiersze
//		j - kolumny
		
		for (int i = 0; i < tsm3.getRowCount(); i++)		{
			for (int j = 0; j < tsm3.getColumnCount(); j++)	{
				System.out.print(tsm3.getValueAt(i, j)+" | ");
			}
			System.out.println();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	public static void main(String[] args) {

//		BasicConfigurator.configure();
		String log4jConfPath = "D:\\GitRepo\\mk-Asap-DB\\AsapDB_3\\log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		
		TimeSheetModel3 tsm3 = new TimeSheetModel3(9, 2019);
		new DataExtractor2(tsm3);

	}

	public Object getExRow(String string) {
		
		//deklaracja kolekcji kol
		//lecimy po wierszu od pos 4
		//if string = subStrint sapNr
			//dodajemy do kolekcji sapNr
		//else
			//dodajemy do kolekcji ""
		
		
		
		
		
		return null;
	}

}
