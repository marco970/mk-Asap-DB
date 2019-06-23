package pl.asap.models;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.AbstractTableModel;

import pl.asap.entity.Notes;
import pl.asap.transactions.AddNewNote;
import pl.asap.transactions.ReadNotes;

@SuppressWarnings("serial")
public class NotesModel extends AbstractTableModel   {
	//
	private ArrayList<Notes> notes;
	private Notes note;
	private int idPostepowanie;
	private Object[][] dane;
	private Color[] rowColor;
	private String[] columns = {"data utworzenia", "data modyfikacji", "notatka", "zamknięta"};
	private ReadNotes rn;
	
	public NotesModel() {
	}
	public NotesModel(int idPostepowanie) {
		
		this.idPostepowanie = idPostepowanie;
		
		rn = new ReadNotes(idPostepowanie); //to do modelu
		
		this.notes=rn.getNotes();
		
		rowColor = new Color[getRowCount()];
		
		dane = new Object[notes.size()][columns.length];
		for (int i=0; i<notes.size(); i++)	{	// i - wiersze
			dane[i][0] = notes.get(i).getDateOpen();
			dane[i][1] = notes.get(i).getDateModified();
			dane[i][2] = notes.get(i).getNote();
			dane[i][3] = notes.get(i).getIsOpen();
//			System.out.println("row nr: "+i+" noteID: "+notes.get(i).getNoteId());
		}	
	}
	public Color getRowColor(int row) {		//zrobić lambdę
		if ((boolean)getValueAt(row,3)) rowColor[row]=Color.LIGHT_GRAY;
		else rowColor[row]=Color.WHITE;
		return rowColor[row];
	}
	public void setRowColor(int row) {
		if ((boolean)getValueAt(row,3)) rowColor[row]=Color.LIGHT_GRAY;
		else rowColor[row]=Color.WHITE;
		fireTableRowsUpdated(row, row);
	}
	public ArrayList<Notes> getNotes() {
		return notes;
	}

	public Notes getNote() {
		return note;
	}

	public void addNote()	{//w najnowszej wersji to może być niepotrzebne
		
		SimpleDateFormat formatter= new SimpleDateFormat("dd.MM.yyyy");  
		Date date = new Date(System.currentTimeMillis());  
		String data = formatter.format(date);  
		Notes nowaNotka = new Notes("",data, data, false);
		new AddNewNote(idPostepowanie, nowaNotka);

	}
	public void deleteNote()	{
		
	}
	@Override
	public int getColumnCount() {
		return columns.length;
	}
	@Override
	public int getRowCount() {
		return notes.size();
	}
	@Override
	public Object getValueAt(int row, int col) {
		if (row>=0 && row<=this.getRowCount() && col>=0 && col<=this.getColumnCount()) {
			if (dane[row][col] != null)
				return dane[row][col];
			else
				return null;
		}
		return null;
	}
    public boolean isCellEditable(int row, int col)
    {
    	if (col==3) return true;
    	if (col==2 && !((Boolean) getValueAt(row,3)).booleanValue()) return true;
       else return false;
    }
    public String getColumnName(int i)	{
    	return columns[i];
    }
    public void setValueAt(Object value, int row, int col) {
        dane[row][col] = value;
        fireTableCellUpdated(row, col);
    }
    
	public Class<?> getColumnClass(int column) {
        return (getValueAt(0, column).getClass());
    }
    public int getNoteId(int row)	{
		rn = new ReadNotes(idPostepowanie); //to do modelu
		
		notes=rn.getNotes();
//    	System.out.println("uwaga" + row);
    	
    	return notes.get(row).getNoteId();
    }
    public void updateNoFire(Object value, int row, int col) {
    	dane[row][col] = value;
    }
    public void newNote()	{
    	SimpleDateFormat formatter= new SimpleDateFormat("dd.MM.yyyy");  
		Date date = new Date(System.currentTimeMillis());  
		String dateOpen = formatter.format(date);
		Notes newNote = new Notes("", dateOpen, dateOpen, false);
		Object[] noteArray = {
				newNote.getDateOpen(),
				newNote.getDateModified(),
				newNote.getNote(),
				newNote.getIsOpen()
				};

		int n = getRowCount()+1;
		
//		System.out.println("n = "+n);
//		System.out.println("recordAdd, n: "+n + " ilość wierszy :"+getRowCount());
		Object[][] daneUpd = new Object[n][columns.length];
		for (int i = 0; i<n; i++)	{
			if (i<n-1) {
				daneUpd[i]=dane[i];
//				System.out.println("przepisuje "+i);
			}
			else	{
				daneUpd[i]=noteArray;
//				System.out.println("dodaję "+i);
			}
		}
		dane=daneUpd;
//		System.out.println("teraz wierszy jest "+dane.length);
		new AddNewNote(idPostepowanie, newNote);
		fireTableRowsInserted(n-1, n-1);
		fireTableDataChanged();

    }
    public NotesModel getNotesModel()	{
    	return this;
    }



	

}
