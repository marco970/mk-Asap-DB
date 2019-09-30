package pl.asap.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;

import pl.asap.entity.Lista;
import pl.asap.raport.CalendarInside;
import pl.asap.transactions.lista.ReadTrans;
import pl.asap.transactions.timesheet.TimeSheetEntryDelete;
import pl.asap.transactions.timesheet.TimeSheetEntryNew;
import pl.asap.transactions.timesheet.TimeSheetEntryUpdate;
import pl.asap.transactions.timesheet.TimeSheetRead;
import pl.asap.transactions.timesheet.TimeSheetReadActive;


@SuppressWarnings("serial")
public class TimeSheetModel3 extends AbstractTableModel  {
	
	private static String[] fixedNames = {"ZZ", "Nazwa", "Status", "Numer Wpisu"};
	private List<String> ColumnNames;
	private List<List<Object>> daneEntries;
	private TimeSheetRead tsr;
	private String monthYear;
	private List<String[]> leftSide;
	private CalendarInside ci;
	private List<Integer> totalDayTime;
	
	public TimeSheetModel3(int month, int year, String u, String w, String v)	{
		super();
		this.ci = new CalendarInside(year, month);
		this.ColumnNames = new ArrayList<>();
		this.daneEntries = new ArrayList<>();
		this.monthYear = "."+numString(month)+"."+year;	//data nie zawiera dnia
//		System.out.println("tsm3, monthYear-"+monthYear);
		
		this.tsr = new TimeSheetRead(month, year);
		
		//source of data
		this.leftSide = tsr.getEntryMatrix(); 
		List<String[]> leftSideActive = new TimeSheetReadActive().getEntryMatrix();
		leftSide.addAll(leftSideActive);
		
//		tu do leftSide dodać wszystkie postępowania aktywne, których jeszcze nie ma na liście 
//		to mgą byc jedynie postępowania PZ
		
		
		
		MainTableModel mtm = new MainTableModel();
		Object[][] matrix = mtm.getMatrix();
		
		
		
		for(String el: fixedNames)	{		//dodanie lewej strony do headera
			ColumnNames.add(el);		
		}
		
		for (String el: ci.getAllDays())	{		//dodanie prawej strony do headera
			ColumnNames.add(el);
		}
//		utworzyć leftModel
		Set<List<String>> leftSet = new HashSet<>();
		List<List<String>> checkList = new ArrayList<>();
		
//		for(String[] el: leftSide)	{
//			for (int i=0; i<el.length; i++)	{
//				System.out.println(i+" leftSide: "+el[i]);
//			}
//		}
		
		for(String[] el: leftSide)	{
			List<String> leftModelRow = new ArrayList<>();
			List<String> checkRow = new ArrayList<>();
			for (int i=0; i<6; i++)	{
//				System.out.println(i+" leftSide: "+el[i]);
				if (i<=2 || i==5) {
					leftModelRow.add(el[i]);
				}
				if (i==2 || i==3 || i==4)	{
					checkRow.add(el[i]);
				}
			}
			leftSet.add(leftModelRow);
			checkList.add(checkRow);
		}

		String dayEntry;
		//iteracja po "lewej stronie"
//		System.out.println("leftSet-size "+leftSet.size());
		for(List<String> el: leftSet)	{
			 List<Object> rowEntries = new ArrayList<Object>();	 
			 rowEntries.add(el.get(0));
			 rowEntries.add(el.get(1));
			 rowEntries.add(el.get(3));
			 rowEntries.add(el.get(2));
//			 System.out.println("leftSet "+el.get(0).toString()+" "+el.get(3));
			 
			 for (Object elem: ci.getAllDays())	{		//tworzenie rightModel
				 rowEntries.add(0);
			 }
			 
//			 aktualizacja rightModel (Model) tu: 
			 for (List<String> checkEl: checkList)	{
				 if (checkEl.get(0).equals(rowEntries.get(3)))	{
					 dayEntry = checkEl.get(1).substring(0,2);
					 int colPosition = Integer.valueOf(dayEntry)+3;
					 rowEntries.set(colPosition, checkEl.get(2));
				 }
			 }
//			 System.out.println("tsm3-> testy-"+el.get(0).toString());
			 String company = el.get(0).toString().substring(3, 6);
			 boolean companyCondition = !company.equals(u) && !company.equals(w) && !company.equals(v);
			 
//			 System.out.println("rowEntries-size "+rowEntries.size());
			 
//			 System.out.println();
//			 System.out.println("company-"+company+" u-"+u+" w-"+w+" v-"+v+" condit-"+companyCondition);
			 if (companyCondition) {
				daneEntries.add(rowEntries);
//				System.out.println("------x"+daneEntries.size()+" -- "+daneEntries.get(daneEntries.size()-1).size()+"x------");
			}
		}
//		List<Object>
		totalDayTime = new ArrayList<>();
		for(int i=0; i<getColumnCount()-4;i++)	{
			
			totalDayTime.add(i, 0);
		}
		for(List<Object> elR: daneEntries)	{
//			System.out.println();
			for(int i=0; i<getColumnCount()-4;i++)	{
				int val = Integer.valueOf(elR.get(i+4).toString());			
				int dayTime = totalDayTime.get(i);			
//				System.out.print(val+" | ");
				addDayTime(i, val);
			}
		}
//		System.out.println();
//		System.out.println("------------------");
//		
//		for(int i=0; i<getColumnCount()-4;i++)	{
//			System.out.print(totalDayTime.get(i)+" | ");
//		}
//		System.out.println();
	}

	public CalendarInside getCi() {
		return ci;
	}
	public boolean isCellEditable(int row, int col)
    {
		if(col>3) return true;
		else return false;
    }
    public Class<?> getColumnClass(int col) {
    	
		if(col>3) return JComboBox.class;
		else return String.class;
        
    }
	@Override
	public int getColumnCount() {
//		System.out.println("ColumnNames "+ColumnNames.size());
		return ColumnNames.size();
	}
	public String getColumnName(int col)	{
		return ColumnNames.get(col);
	}
	public String[] getColumnNames()	{
		return (String[]) ColumnNames.toArray();
	}
	@Override
	public int getRowCount() {
//		System.out.println("daneEntries "+daneEntries.size());
		return daneEntries.size();
	}
	@Override
	public Object getValueAt(int row, int col) {
//		List<Object> rowData = daneEntries.get(row);
		///
		return daneEntries.get(row).get(col);
	}
	public void setValueAt(Object o, int row, int col)	{
//		sprawdzenie:
		
		int previous = Integer.parseInt(getValueAt(row,col).toString());
//		System.out.println("setValueAt test 1: "+previous);
		List<Object> arrRow = daneEntries.get(row);
		arrRow.set(col, o);
		daneEntries.set(row, arrRow);
		fireTableCellUpdated(row, col);
//		System.out.println("setValueAt test 2: "+getValueAt(row,col).toString());
		int next = Integer.parseInt(getValueAt(row,col).toString());
//		this.setDayTime(col-4, next);
		int idPostepowanie = 0;
		if (next > 0 && previous == 0)	{
//			System.out.println("new TimeSheetEntryNew("+tsr.getIdPostepowanie(getValueAt(row, 3).toString(), numString(col-3)+monthYear)+", "+ numString(col-3)+monthYear+")");
			
//			tsr.getIdPostepowanie(getValueAt(row, 3).toString(), numString(col-3)+monthYear) - to mozna inaczej.... -->  leftSide.get(row)[6]
			
			Lista lista = new Lista();
			ReadTrans readDB = new ReadTrans(lista);
			List<Lista> result = readDB.getResult();
			String nrSap = this.getValueAt(row, 3).toString();
			
//			System.out.println("nrSap: "+nrSap);
//			System.out.println("z Listy: "+result.get(row).getZZ());
			for(Lista el: result)	{
//				System.out.println("res-check "+el.toString());
//				tu szukamy idPostepowanie 
				if (nrSap.equals(el.getZZ()) || nrSap.equals(el.getPZ()) || nrSap.equals(el.getDK()))	{
					idPostepowanie = el.getIdPostepowanie();
				}	
			}
//			System.out.println("res-check end; idPostepowanie = "+idPostepowanie);
			new TimeSheetEntryNew(idPostepowanie, getValueAt(row,3).toString(), numString(col-3)+monthYear, Integer.parseInt(o.toString()));
//			System.out.println("sprawdzamy: "+tsr.getIdPostepowanie(getValueAt(row, 3).toString(), numString(col-3)+monthYear)+" vs "+leftSide.get(row)[6]);
			
		}
		if (next == 0 && previous > 0)	{
			int entryId = tsr.getIdEntry(getValueAt(row,3).toString(),numString(col-3)+monthYear);
			new TimeSheetEntryDelete(entryId);
//			System.out.println("to delete: "+idPostepowanie);
//			System.out.println("to delete: "+tsr.getIdEntry(getValueAt(row,3).toString(),numString(col-3)+monthYear));
		}
		if (next > 0 && previous > 0)		{
			int entryId = tsr.getIdEntry(getValueAt(row,3).toString(),numString(col-3)+monthYear);
//			System.out.println("to update :"+entryId);
			new TimeSheetEntryUpdate(entryId, next);
		}	
	}
	public int getTotalDayTime(int i) {
		return totalDayTime.get(i);
	}
	public void addDayTime(int i, int val)	{
		int dayTime = totalDayTime.get(i);
		dayTime = dayTime + val;
//		if ((dayTime+val)<=8) {
//			dayTime = dayTime + val;
//		}
//		else dayTime = 8;
		totalDayTime.set(i, dayTime);		
//		System.out.println("TSM3 add- > ["+i+"] "+totalDayTime.get(i));
	}
	public void setTotalDayTime(int rowNr, int i, int val)	{
		int colNr = i+4;
		int delta = val - Integer.valueOf(daneEntries.get(rowNr).get(colNr).toString()); 
//		System.out.println("delta = "+delta);
		addDayTime(i, delta);
//		System.out.println("TSM3 set- > ["+i+"] "+totalDayTime.get(i));
	}
	public String numString(int num)	{
			String numStr;
			if (num<10) numStr = "0"+ num;
			else numStr = ""+num;
			return numStr;
		}
	public static void main(String[] args) {
		new TimeSheetModel3(9, 2019, "", "", "");
	}
}
