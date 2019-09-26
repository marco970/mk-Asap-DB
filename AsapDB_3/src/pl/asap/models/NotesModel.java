package pl.asap.models;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import pl.asap.entity.Notes;
import pl.asap.transactions.notes.NoteDelete;
import pl.asap.transactions.notes.NoteNew;
import pl.asap.transactions.notes.NoteRead;
import pl.asap.transactions.notes.NoteUpdate;
import pl.asap.transactions.notes.NoteUpdate2;

@SuppressWarnings("serial")
public class NotesModel extends AbstractTableModel   {
	//
	private ArrayList<Notes> notes;
//	private Notes note;
	private int idPostepowanie;
	private Object[][] dane;
	private Color[] rowColor;
	private String[] columns = {"data utworzenia", "data modyfikacji", "notatka", "zamknięta"};
	private NoteRead rn;
	
	public NotesModel(int idPostepowanie) {
			
			this.idPostepowanie = idPostepowanie;
			rn = new NoteRead(idPostepowanie); //to do modelu		
			this.notes=rn.getNotes();
			rowColor = new Color[getRowCount()];
			dane = new Object[notes.size()][columns.length];
			for (int i=0; i<notes.size(); i++)	{	// i - wiersze
				dane[i][0] = notes.get(i).getDateOpen();
				dane[i][1] = notes.get(i).getDateModified();
				dane[i][2] = notes.get(i).getNote();
				dane[i][3] = notes.get(i).getIsOpen();
//				System.out.println("NotesModel-"+"row nr: "+i+" noteID: "+notes.get(i).getNoteId());
//				System.out.println("NotesModel-"+"row nr: "+i+" note: "+notes.get(i).getNote());
//				System.out.println("NotesModel-"+"row nr: "+i+" IsOpen: "+notes.get(i).getIsOpen());
			}	

		}

	
	public void deleteNote(int rowToDelete)	{
//		System.out.println("------deleteNote---before---> "+notes.size()+" i dane "+dane.length+" i rTD "+ rowToDelete);
		int noteId = getNoteId(rowToDelete);
		
		NoteDelete nd = new NoteDelete(getNote(), noteId);
		
		if (nd.getRezultatDelete()) {
			notes.remove(rowToDelete);
			Object[][] daneNew = new Object[notes.size()][columns.length];
			for (int i = 0; i < notes.size(); i++) { // i - wiersze
				daneNew[i][0] = notes.get(i).getDateOpen();
				daneNew[i][1] = notes.get(i).getDateModified();
				daneNew[i][2] = notes.get(i).getNote();
				daneNew[i][3] = notes.get(i).getIsOpen();
				//			System.out.println("row nr: "+i+" noteID: "+notes.get(i).getNoteId());
			}
			dane = daneNew;
//			System.out.println("------deleteNote---after---> " + notes.size() + " i dane " + dane.length + " i rTD "
//					+ rowToDelete);
			if (rowToDelete >= 1) {
				fireTableRowsDeleted(rowToDelete - 1, rowToDelete - 1);
				fireTableDataChanged();
			}
			//		else fireTableRowsDeleted(rowToDelete, rowToDelete);
		}	
	}

	    public void updateNoFire(Object value, int row, int col) {	//do wywalenia
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
			
//			System.out.println("n = "+n);
//			System.out.println("recordAdd, n: "+n + " ilość wierszy :"+getRowCount());
			Object[][] daneUpd = new Object[n][columns.length];
			for (int i = 0; i<n; i++)	{
				if (i<n-1) {
					daneUpd[i]=dane[i];
//					System.out.println("przepisuje "+i+" - ");
				}
				else	{
					daneUpd[i]=noteArray;
//					System.out.println("dodaję "+i);
				}
			}
			dane=daneUpd;
//			System.out.println("teraz wierszy jest "+dane.length);
			n = dane.length-1;
			
//			for (int i = 0; i<dane.length; i++)	{
//				System.out.println("**** "+i);
//				for (Object el: dane[i])	{
//					System.out.print("** "+el+" ; ");
//				}
//			}
			
//			this.setValueAt(dateOpen, n-1, 0);
//			this.setValueAt(dateOpen, n-1, 1);
//			this.setValueAt("", n-1, 2);
//			this.setValueAt(false, n-1, 3);
			
			
//			for (int i = 0; i<dane.length; i++)	{
//				System.out.println("** "+i);
//				for (Object el: dane[i])	{
//					System.out.print("* "+el+" ; ");
//				}
//			}
					
			fireTableRowsInserted(n-1, n-1);
			fireTableDataChanged();
			new NoteNew(idPostepowanie, newNote);
	
	    }
	public void updateNote (Object value, int row, int col) {	// dostosować
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			Date date = new Date(System.currentTimeMillis());
			String dateModified = formatter.format(date);
			System.out.println("NotesModel-updateNote-"+value+" row-"+row+" col-"+col);
			setValueAt(value, row, col);
			setValueAt(dateModified, row, 1);
			
			
			NoteUpdate2 nu2 = new NoteUpdate2();
			if(col==2)	{
				nu2.updateNote(getNoteId(row), value.toString(), dateModified); //-dostosować 
			}
			if(col==3)	{
				nu2.updateIsOpen(getNoteId(row), Boolean.valueOf(value.toString()), dateModified);
			}
			
			fireTableCellUpdated(row, col);
			fireTableDataChanged();
		}
	public void addNote()	{//w najnowszej wersji to może być niepotrzebne
		
		SimpleDateFormat formatter= new SimpleDateFormat("dd.MM.yyyy");  
		Date date = new Date(System.currentTimeMillis());  
		String data = formatter.format(date);  
		Notes nowaNotka = new Notes("",data, data, false);
		new NoteNew(idPostepowanie, nowaNotka);
	
	}
	public void setValueAt(Object value, int row, int col) {
	    dane[row][col] = value;
	    fireTableCellUpdated(row, col);
	}
	@Override
	public Object getValueAt(int row, int col) {
		if (row>=0 && row<this.getRowCount() && col>=0 && col<this.getColumnCount()) {
			if (dane[row][col] != null)
				return dane[row][col];
			else
				return null;
		}
		return null;
	}
	public int getRowNr(int noteId)	{		//problem, to jeśli nie będzie danej noteId wśród rekordów - do ogarniecia
		int row =0;
		for (int i=0; i<getRowCount(); i++)	{
			if(getNoteId(i)==noteId) row=i;
		}
		return row;
	}
	public int getNoteId(int row)	{
			rn = new NoteRead(idPostepowanie); //to do modelu	
			notes = rn.getNotes();
			return notes.get(row).getNoteId();
	}
	
public Object[][]	getNotesMatrix()	{
		return dane;
	}

	public ArrayList<Notes> getNotes() {
		return notes;
	}

	public NotesModel getNotesModel()	{
		return this;
	}
	public Notes getNote() {		
		Notes note = new Notes();		//? i to działa? Tylko chyba w jednym przypadku, bo tam trzeba 
										//tylko podać nazwę klasy a nie instancję z wartośnciami pól
		return note;
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}
	@Override
	public int getRowCount() {
		return notes.size();
	}
	public boolean isCellEditable(int row, int col)
    {
		return false;
    }
    public String getColumnName(int i)	{
    	return columns[i];
    }
    public Class<?> getColumnClass(int column) {
        return (getValueAt(0, column).getClass());
    }
}
