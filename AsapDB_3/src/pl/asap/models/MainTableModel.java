package pl.asap.models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import pl.asap.DB.DBConnect;
import pl.asap.entity.Lista;
import pl.asap.transactions.lista.ReadTrans;
import pl.asap.transactions.lista.SaveTrans;
import pl.asap.transactions.lista.UpdateTrans;
import pl.asap.transactions.timesheet.TSEQueryGet;
import pl.asap.transactions.timesheet.TimeSheetEntryNew;
import pl.asap.transactions.timesheet.TimeSheetEntryUpdate;

public class MainTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private static String[] nazwyKolumn = {"ZZ", "PZ", "WP", "DK", "Status", 
			"Przemiot Zakupu", "Dostawca", "Nazwa", "Tryb postępowania", "Spółka", 
			"dsZZ", "dsPZ", "dsWP", "dsDK" };
	private String current = "Current4.txt";	
	private Object[][] dane = null;
	@SuppressWarnings("unused")
	private Object[][] adane = null;
	private Object[] ids;
	private Lista lista;
	
	public MainTableModel() 	{
		
		//do wywalenia ale najpierw przepisać jednorazowo plik do BD - moze oddzielna klasa?
//		try {
//			this.adane=readFile(current);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		lista = new Lista();
//		DBConnect dbConnect = new DBConnect();
		ReadTrans readDB = new ReadTrans(lista);
		this.dane=readDB.getMatrix();
		this.ids = readDB.getIDs();
		
//		i - wiersze
		int i = dane.length;
//		j - kolumny
		int j = nazwyKolumn.length;

		for(int m = 0; m<=i-1; m++)	{	
			for (int n =0; n<=j-1; n++)	{
				if (n==0)	{							
				}
			}
		}	
	}

	public Integer getId(int rowNr)	{
		int id;
		Object[] cids = new ReadTrans(lista).getIDs();
//		System.out.println("n -> "+rowNr+ " il.pozycji w DB -> "+cids.length+" max index w DB -> "+cids[cids.length-1]);
		if (rowNr<=getRowCount()) {
			id = (Integer) cids[rowNr];
		}
		else id = (Integer) cids[cids.length-1];
		return id;
	}
	
	public String getPath()	{
		return current;
	}
	public Object[][] getMatrix()	{
		return dane;
	}
	public Object[] getRowData(int i)	{
		return dane[i];		
	}
	public String[] getColumns()	{
		return nazwyKolumn ;
	}
	public String[] getColumnsNoDs()	{
		String[] colNoDs = new String[nazwyKolumn.length-getNumberDs()];
		for (int i=0; i<=nazwyKolumn.length-getNumberDs()-1; i++)	{
			colNoDs[i] = nazwyKolumn[i];
		}
		return nazwyKolumn ;
	}
	public String getColumnName(int i)	{
		return nazwyKolumn[i];	
	}
	public boolean doesElExists(int i, int j)	{
		boolean a = true;
		if (i <= getRowCount())	{
			if ((getValueAt(i,j)==null)||"".equals(getValueAt(i,j))||" ".equals(getValueAt(i,j))) a = false;
			else a = true;
		}
		else a = false;
		return a;
	}
	public int getColumnPosition(String ColName)	{
		int j=100;
		for (int i=0; i<=nazwyKolumn.length-1; i++)	{
			if(nazwyKolumn[i].equals(ColName))	{
				j=i;
			}
		}
		return j;	
	}
	@Override
	public int getColumnCount() {
		return nazwyKolumn.length;
		
	}

	@Override
	public int getRowCount() {
		return dane.length;
	}

	@Override	
	public Object getValueAt(int row, int col) {

		if (row<=getRowCount() && col<=getColumnCount()) return dane[row][col];
		else return "";
	}
	//----------------------------------------------------------potrzebne do migracji danych
	public Object[][] readFile(String filePath) throws IOException {
		  FileReader fileReader = new FileReader(filePath);
		  BufferedReader bufferedReader = new BufferedReader(fileReader);
		  
		  String textLine = bufferedReader.readLine();
		  ArrayList<String> linia = new ArrayList<String>();	  
		  do {
		    linia.add(textLine);
		    textLine = bufferedReader.readLine();	    
		  } while(textLine != null);

		  bufferedReader.close();
		  int i =0;
		  Object[][] sdane = new String[linia.size()][nazwyKolumn.length];
		  for (String el: linia)	{
			  	String[] row=el.split(";");
			  	for (int j=0; j<=row.length-1; j++)	{	  		
			  		sdane[i][j]=row[j];
			  		if (sdane[i][j]==null) sdane[i][j]="";
			  	}
			  i++;
		  }
		  int n=1;
		  List<Object[]> wiersz = new ArrayList<Object[]>();
		  wiersz.add(sdane[n]);
		  return sdane;
		}
	public int getNumberDs() {
		int a = 0;
		for (Object el: nazwyKolumn)	{
			if ("ds".equals(((String) el).substring(0, 2))) {
				a++; 
			}
		}
		return a;
	}
	public void recordAdd(Object[] savedRow) {	//--zapis do DB
		int n = getRowCount()+1;
		
		//System.out.println("recordAdd, n: "+n + " ilość wierszy :"+getRowCount());
		Object[][] daneUpd = new Object[n][nazwyKolumn.length];
		for (int i = 0; i<= n-1; i++)	{
			if (i<=n-2) daneUpd[i]=dane[i];
			else 		daneUpd[i]=savedRow;
		}
		dane=daneUpd;
		SaveTrans st = new SaveTrans(lista);
		st.saveRow(savedRow);
		fireTableRowsInserted(n-1, n-1);
		fireTableDataChanged();
		Object[] cids = new ReadTrans(lista).getIDs();
		int a = (int) cids[cids.length-1];
		new TimeSheetEntryNew(a, (String) savedRow[0], (String) savedRow[10], 1);
	}
	public void recordUpdate(Object[] savedRow, int rowNr) { //--zapis do DB
		ArrayList<Object[]> rowList = new ArrayList<Object[]>();
		
		for (int i = 0; i<=getRowCount()-1; i++)	{
			rowList.add(dane[i]);
		}
		
		String nrSap = "";
		String dateEntry = "";
		for (int i=0; i<savedRow.length; i++)	{
			if (!savedRow[i].equals(dane[rowNr][i]) && i<=3)	{
				if (i==2) {		//jeśli WP nie ma
//					nrSap = (String) savedRow[i-1];		//to do usunięcia
//					System.out.println("WP? "+savedRow[i-1]);
					//tu mozemy wrzucić aktualizację wpisu
					
					
//					//uzyskanie entyId
//					System.out.println("idPost -> "+this.getId(rowNr));
//					System.out.println("PZ -> "+this.getValueAt(rowNr, 1).toString());
					
					TSEQueryGet tse = new TSEQueryGet(this.getValueAt(rowNr, 1).toString());
					int entryId = tse.getEntryId();
					if (entryId==0)	nrSap = (String) savedRow[i-1];	
					else	{
						int timePassed = tse.getTimePassed();
						String opis = tse.getOpis();
//						System.out.println("entryId-> "+entryId+" timePassed-> "+timePassed);
						//aktualizacja entry
						new TimeSheetEntryUpdate(entryId, timePassed+1);
					}
				}
				else nrSap = (String) savedRow[i];
				dateEntry = (String) savedRow[i+10];
			}
		}
//		System.out.println("nrSap -> "+nrSap+" --> "+dateEntry);
//		System.out.println("MainTabModel ----- recordUpdate rowNr: "+rowNr + " ----id: "+getId(rowNr));
		rowList.set(rowNr, savedRow);
		Object[][] daneUpd = new Object[rowList.size()][nazwyKolumn.length];
		int j = 0;
		for (Object[] el: rowList)	{
			daneUpd[j]=el;
			j++;
		}

		dane=daneUpd;
		fireTableRowsUpdated(rowNr, rowNr);
		fireTableDataChanged();	
		UpdateTrans ut = new UpdateTrans(lista);
		ut.updateRow(savedRow, getId(rowNr));
		
		if (!"".equals(nrSap)) new TimeSheetEntryNew(getId(rowNr), nrSap, dateEntry, 1);

	}
	public void cellUpdate(Object value, int rowNr, int kolNr)	{ //--zapis do DB

		dane[rowNr][kolNr] = value;
		fireTableCellUpdated(rowNr, kolNr); 
		UpdateTrans ut = new UpdateTrans(lista);
		String field = getColumnName(kolNr);
		int id = getId(rowNr);
//		System.out.println("cellUpdate id= "+id);
		ut.upadateCell(field, value, id);
		
	}
	public boolean isCellEditable(int row, int col) {
		return true;
	}
}
