package pl.asap.raport;
//
//import org.apache.log4j.BasicConfigurator;
//import org.apache.log4j.PropertyConfigurator;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

import pl.asap.models.TimeSheetModel3;



public class DataExtractor2 {
	
	private List<DayContent> dcl;
	
	
	
	public DataExtractor2(TimeSheetModel3 tsm3, String u, String w, String v)	{
		
//		i - wiersze
//		j - kolumny
		
		this.dcl = new ArrayList<>();
		
		for (int j = 0; j < tsm3.getColumnCount(); j++)	{
			if (j>3)	{
			DayContent dc = DayContent.getInstance(j-4);
			dcl.add(dc);
//			System.out.println("dayNrA: "+(j-4));
//			dc.DayContentShow();
			}
		}
		
		for (int i = 0; i < tsm3.getRowCount(); i++)	{
			String company = tsm3.getValueAt(i, 0).toString().substring(3, 6);
			boolean companyCondition = !company.equals(u) && !company.equals(w) && !company.equals(v);
//			System.out.println();
//			System.out.println("row-"+i+" company-"+company+" u-"+u+" w-"+w+" v-"+v+" condit-"+companyCondition);
			if (companyCondition) {
				for (int j = 0; j < tsm3.getColumnCount(); j++) {
					if (!(j == 1 || j == 2)) {
//						System.out.print(tsm3.getValueAt(i, j) + " | ");
					}
					if (j > 3) {

						dcl.get(j - 4).addContent(tsm3.getValueAt(i, 3).toString(),
								Integer.valueOf(tsm3.getValueAt(i, j).toString()));
						//					System.out.println("dayNrB: "+(j-4));
					}

				} 
			}
//			System.out.println();
		}
//		System.out.println("--------------------------------------------------");
		//tu wyślietlamy dla przetestowania
		
//		for (int i=0; i<tsm3.getColumnCount(); i++)	{
//			
//			if (i>3) {
//				dcl.get(i-4).DayContentShow();
////				stąd mozna sobie brać dane odpowiednimi metodami
//			}
//		}
		
		
	}

	public String[] getExRow(String string) {

//		System.out.println();
		List<String> extRow = new ArrayList<>();
		for (int i = 0; i<dcl.size();i++)	{
//			System.out.print(i+" ");
//			System.out.println(dcl.get(i).getContent(string));
			extRow.add(dcl.get(i).getContent(string));
		}
		
		String[] extRowArr = new String[extRow.size()];
		for (int i = 0; i<dcl.size();i++)	{
//			System.out.print(i+" ");
//			System.out.println(extRow.get(i));
			extRowArr[i] = extRow.get(i);
		}
		return extRowArr;
	}
	public Integer[] getExHours(String string)	{
		List<Integer> extHours = new ArrayList<>();
		Integer[] extHoursArr = new Integer[dcl.size()];
		
		for (int i = 0; i<dcl.size(); i++)	{
			extHoursArr[i] = dcl.get(i).getHours(string);
		}
		
		return extHoursArr;		
	}
	
//	public static void main(String[] args) {
//		
//
//
////		BasicConfigurator.configure();
//		String log4jConfPath = "D:\\GitRepo\\mk-Asap-DB\\AsapDB_3\\log4j.properties";
//		PropertyConfigurator.configure(log4jConfPath);
//		
//		TimeSheetModel3 tsm3 = new TimeSheetModel3(9, 2019);
//		DataExtractor2 de2 = new DataExtractor2(tsm3, "PLK", "", "");
//		String[] nrSap = de2.getExRow("ZZ");
//		Integer[] hours = de2.getExHours("ZZ");
//		
//		for(int i=0; i<nrSap.length; i++)	{
//			System.out.println("---> "+i+" "+nrSap[i]+"---> "+hours[i]);
//		}
//		
//
//	}


}
