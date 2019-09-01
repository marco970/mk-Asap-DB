package pl.asap.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.table.AbstractTableModel;

import antlr.collections.impl.Vector;
import pl.asap.raport.CalendarInside;
import pl.asap.transactions.timesheet.TimeSheetRead;


public class TimeSheetModel extends AbstractTableModel  {
	
	private static String[] fixedNames = {"ZZ", "Nazwa", "Status", "Numer Wpisu"};
	private List<String> ColumnNames;
	private List<List<Object>> daneEntries;
	
	public TimeSheetModel(int month, int year)	{
		TimeSheetRead tsr = new TimeSheetRead(month, year);
		
		List<String[]> leftSide = tsr.getEntryMatrix();
		List<Integer[]> rightSide = new ArrayList<Integer[]>();
		
		CalendarInside ci = new CalendarInside(year, month);
		this.ColumnNames = new ArrayList<>();
		this.daneEntries = new ArrayList<>();
		for(String el: fixedNames)	{		//dodanie lewej strony do headera
			ColumnNames.add(el);		
		}
		
		for (String el: ci.getAllDays())	{		//dodanie prawej strony do headera
			ColumnNames.add(el);
		}
//		utworzyć source
		
//		for(String[] el: leftSide)	{
//			for (String elem: el)	{
//				System.out.print(elem+" ||| ");
//			}
//			System.out.println("---source----");
//		}

//		utworzyć leftModel
		List<List<String>> leftModel = new ArrayList<>();	//pewnie nie będzie potrzebne
		Set<List<String>> leftSet = new HashSet<>();
		
		for(String[] el: leftSide)	{
			List<String> leftModelRow = new ArrayList<>();
			for (int i=0; i<6; i++)	{
				if (i<=2 || i==5) {
					leftModelRow.add(el[i]);
				}
			}
			leftModel.add(leftModelRow);
			leftSet.add(leftModelRow);
		}
		for(List<String> el: leftModel)	{
			for (String elem: el)	{
				System.out.print(elem+" ||| ");
			}
			System.out.println("---leftModel----");
		}
		for(List<String> el: leftSet)	{
			for (String elem: el)	{
				System.out.print(elem+" ||| ");
			}
			System.out.println("---leftSet----");
		}

//		utworzyć rightModel i skleić
		String dayEntry;
		for(List<String> el: leftSet)	{
			 List<Object> rowEntries = new ArrayList<Object>();	
//			 dayEntry = el.get(3).substring(0, 2);
//			 int colPosition = Integer.valueOf(dayEntry)+3;
			 //iteracja po "lewej stronie"
			 rowEntries.add(el.get(0));
			 rowEntries.add(el.get(1));
			 rowEntries.add(el.get(3));
			 rowEntries.add(el.get(2));
			 for (Object elem: ci.getAllDays())	{
				 rowEntries.add(0);
			 }
//			 rowEntries.set(colPosition, el.get(4));

			 for (Object elem: rowEntries)	{
				 System.out.print(elem+ " | ");
			 }
			 daneEntries.add(rowEntries);

			 System.out.println("------x"+daneEntries.size()+" -- "+daneEntries.get(daneEntries.size()-1).size()+"x------");
		}
//		aktualizacja rightModel

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
		return daneEntries.get(row).get(col);
	}
	public static void main(String[] args) {
		new TimeSheetModel(8, 2019);
	}

}
