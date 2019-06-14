package pl.asap.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.table.AbstractTableModel;

import pl.asap.entity.Notes;
import pl.asap.transactions.AddNewNote;
import pl.asap.transactions.NewNote;
import pl.asap.transactions.ReadNotes;
import pl.test.notes.SingleNote;

@SuppressWarnings("serial")
public class NotesModel extends AbstractTableModel   {
	
	private ArrayList<Notes> notes;
	private Notes note;
	private PropertyChangeSupport propertyChange = new PropertyChangeSupport(this);
	private int idPostepowanie;
	private Object[][] dane;
	private String[] columns = {"data utworzenia", "data modyfikacji", "notatka", "zamknięta"};
	
	public NotesModel() {
	}
	public NotesModel(ArrayList<Notes> notes, int idPostepowanie) {
		this.notes=notes;
		this.idPostepowanie = idPostepowanie;
		dane = new Object[notes.size()][columns.length];
		for (int i=0; i<notes.size(); i++)	{	// i - wiersze
			dane[i][0] = notes.get(i).getDateOpen();
			dane[i][1] = notes.get(i).getDateModified();
			dane[i][2] = notes.get(i).getNote();
			dane[i][3] = notes.get(i).getIsOpen();
//			System.out.println("row nr: "+i+" noteID: "+notes.get(i).getNoteId());
		}	
	}
	public ArrayList<Notes> getNotes() {
		return notes;
	}
	public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChange.addPropertyChangeListener(listener);
	}

	public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChange.removePropertyChangeListener(listener);
	}
	public void setNotes(ArrayList<Notes> notes) {
//		System.out.println("odpalam metodę z fire z listą notatek: ");
		ArrayList<Notes> oldNotes = getNotes();
		this.notes = notes;
		propertyChange.firePropertyChange("notes", oldNotes, notes);
//		System.out.println("+++nowe+++ "+notes.toString());
//		System.out.println("+++old+++ "+oldNotes.toString());
	}
	public Notes getNote() {
		return note;
	}
	public void setNote(Notes note) {
		
		this.note = note;
		Notes oldNote = new Notes("","","",true);
		notes.add(note);
		new AddNewNote(idPostepowanie, note);
		propertyChange.firePropertyChange("note", oldNote, note);
		
	}
	public void addNote()	{
		
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy.MM.dd");  
		Date date = new Date(System.currentTimeMillis());  
		String data = formatter.format(date);  
		Notes nowaNotka = new Notes("",data, data, true);
		setNote(nowaNotka);

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
		if (dane[row][col]!=null)return dane[row][col];
		else return null;
		
	}
    public boolean isCellEditable(int row, int col)
    {
       if (col==2) return true;
       else return false;
    }
    public String getColumnName(int i)	{
    	return columns[i];
    }
    public void setValueAt(Object value, int row, int col) {
        dane[row][col] = value;
        fireTableCellUpdated(row, col);
    }
    public int getNoteId(int row)	{
    	return notes.get(row).getNoteId();
    }
    public void updateNoFire(Object value, int row, int col) {
    	dane[row][col] = value;
    }


	

}
