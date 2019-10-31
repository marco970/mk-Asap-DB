package pl.asap.raport;
//
//import org.apache.log4j.BasicConfigurator;
//import org.apache.log4j.PropertyConfigurator;

import java.util.ArrayList;
import java.util.List;

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
			}
		}
		
		for (int i = 0; i < tsm3.getRowCount(); i++)	{
			for (int j = 0; j < tsm3.getColumnCount(); j++) {
				if (!(j == 1 || j == 2)) {
				}
				if (j > 3) {
					dcl.get(j - 4).addContent(tsm3.getValueAt(i, 3).toString(),
					Integer.valueOf(tsm3.getValueAt(i, j).toString()));
				}
			}   
		}	
	}

	public String[] getExRow(String string) {
		List<String> extRow = new ArrayList<>();
		for (int i = 0; i<dcl.size();i++)	{
			extRow.add(dcl.get(i).getContent(string));
		}
		
		String[] extRowArr = new String[extRow.size()];
		for (int i = 0; i<dcl.size();i++)	{
			extRowArr[i] = extRow.get(i);
		}
		return extRowArr;
	}
	
	public Integer[] getExHours(String string)	{
		Integer[] extHoursArr = new Integer[dcl.size()];
		for (int i = 0; i<dcl.size(); i++)	{
			extHoursArr[i] = dcl.get(i).getHours(string);
		}
		return extHoursArr;		
	}
}
