package pl.asap.raport;
//
//import org.apache.log4j.BasicConfigurator;
//import org.apache.log4j.PropertyConfigurator;

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

	public Object getExRow(String string) {
		
//		deklaracje kolekcji 
//		przydałaby się definicja obiektu
//		obiekt jest listą list albo mapą list k-nr dnia V - lista PZ jeszcze nazwa (ZZ, PZ, DK) i druga mapa z godzinami....
		
//		lecimy po cyferkach
		
//		jeśli nrSap = PZ
		
		
		
		
		
		
		
		return null;
	}

	public static void main(String[] args) {
		
		System.out.println("hello");

//		BasicConfigurator.configure();
		String log4jConfPath = "D:\\GitRepo\\mk-Asap-DB\\AsapDB_3\\log4j.properties";
//		PropertyConfigurator.configure(log4jConfPath);
		
		TimeSheetModel3 tsm3 = new TimeSheetModel3(9, 2019);
		new DataExtractor2(tsm3);

	}


}
