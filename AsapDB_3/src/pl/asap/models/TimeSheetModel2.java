package pl.asap.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;

import pl.asap.raport.CalendarInside;
import pl.asap.transactions.timesheet.TimeSheetEntryNew;
import pl.asap.transactions.timesheet.TimeSheetRead;


public class TimeSheetModel2 extends AbstractTableModel  {
	
	private static String[] fixedNames = {"ZZ", "Nazwa", "Status", "Numer Wpisu"};
	private List<String> ColumnNames;
	private List<List<Object>> daneEntries;	//dane modelu tabeli
	private TimeSheetRead tsr;
	private String monthYear;
	List<String[]> leftSide;		//dane z odczytu z BD
	
	public TimeSheetModel2(int month, int year)	{
		super();
		CalendarInside ci = new CalendarInside(year, month);
		this.ColumnNames = new ArrayList<>();
		this.daneEntries = new ArrayList<>();
		this.monthYear = "."+numString(month)+"."+year;	//data nie zawiera dnia
		
		this.tsr = new TimeSheetRead(month, year);
		
		//source of data
		this.leftSide = tsr.getEntryMatrix(); 
		
		for(String el: fixedNames)	{		//dodanie lewej strony do headera
			ColumnNames.add(el);		
		}
		
		for (String el: ci.getAllDays())	{		//dodanie prawej strony do headera
			ColumnNames.add(el);
		}



//		utworzyć leftModel
//		List<List<String>> leftModel = new ArrayList<>();	//nie będzie potrzebne - tylko kontrolnie
		Set<List<String>> leftSet = new HashSet<>();
		List<List<String>> checkList = new ArrayList<>();
		Set<String> nrSapSet = new HashSet<>();
		List<String[]> leftComponent = new ArrayList<>();
		
		
		
		for(String[] el: leftSide)	{
			int setBefore = nrSapSet.size();
			nrSapSet.add(el[2]);
			System.out.println("toSet --> " + el[2]);
			int setAfter=nrSapSet.size();
			if (setBefore==(setAfter-1))	{
				leftComponent.add(el);
			}
			
		}
		for(String[] el: leftComponent)	{
			for(int i = 0; i<7; i++)	{
				System.out.print (el[i]+" ,, ");
			}
			System.out.println("----- ");
		}
//		for(List<String> el: leftModel)	{
//			for (String elem: el)	{
//				System.out.print(elem+" ||| ");
//			}
//			System.out.println("---leftModel----");
//		}
		for(List<String> el: leftSet)	{
			for (String elem: el)	{
				System.out.print(elem+", ");
			}
			System.out.println("---leftSet----");
		}
		for(List<String> el: checkList)	{
			for (String elem: el)	{
				System.out.print(elem+" |-| ");
			}
			System.out.println("---checkSList----");
		}
//		utworzyć rightModel i skleić
		String dayEntry;
		//iteracja po "lewej stronie"
		for(List<String> el: leftSet)	{
			 List<Object> rowEntries = new ArrayList<Object>();	 
			 rowEntries.add(el.get(0));
			 rowEntries.add(el.get(1));
			 rowEntries.add(el.get(3));
			 rowEntries.add(el.get(2));
			 for (@SuppressWarnings("unused") Object elem: ci.getAllDays())	{		//tworzenie rightModel
				 rowEntries.add(0);
			 }
			 
//			 aktualizacja rightModel (Model) tu: 
				/*
				 * w każeym wierszu rowEntries aktualizujemy wszystkie daty
				 * idiemy po wierszu rowEntries 
				 * 	idziemy po liście - dla każdego rowEntries
				 * 		sprawdzamy czy sapNr z listy z sapNr z rowEtries - jeśli ok, to nadpisujemy rowEntries
				 */
			 for (List<String> checkEl: checkList)	{
				 if (checkEl.get(0).equals(rowEntries.get(3)))	{
					 dayEntry = checkEl.get(1).substring(0,2);
					 int colPosition = Integer.valueOf(dayEntry)+3;
					 rowEntries.set(colPosition, checkEl.get(2));
				 }
			 }

			 for (Object elem: rowEntries)	{
				 System.out.print(elem+ " | ");
			 }
			 daneEntries.add(rowEntries);
			 System.out.println("------x"+daneEntries.size()+" -- "+daneEntries.get(daneEntries.size()-1).size()+"x------");
		}
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
		
		///
		
		
		////ames
		
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
		if (previous==0)	{
//			System.out.println("new TimeSheetEntryNew("+tsr.getIdPostepowanie(getValueAt(row, 3).toString(), numString(col-3)+monthYear)+", "+ numString(col-3)+monthYear+")");
			new TimeSheetEntryNew(tsr.getIdPostepowanie(getValueAt(row, 3).toString(), numString(col-3)+monthYear), getValueAt(row,3).toString(), numString(col-3)+monthYear, Integer.parseInt(o.toString()));
//			tsr.getIdPostepowanie(getValueAt(row, 3).toString(), numString(col-3)+monthYear) - to mozna inaczej.... -->  leftSide.get(row)[6]
			System.out.println("sprawdzamy: "+tsr.getIdPostepowanie(getValueAt(row, 3).toString(), numString(col-3)+monthYear)+" vs "+leftSide.get(row)[6]);
		}
		if (next==0 && previous>0)	{
			System.out.println("to delete: "+tsr.getIdEntry(getValueAt(row,3).toString(),numString(col-3)+monthYear));
		}
		
		
	}
	  public String numString(int num)	{
			String numStr;
			if (num<10) numStr = "0"+ num;
			else numStr = ""+num;
			return numStr;
		}
	public static void main(String[] args) {
		new TimeSheetModel2(9, 2019);
	}

}
