package pl.asap.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import pl.asap.entity.Notes;
import pl.test.notes.ReadNotes;

public class NotesModel {
	
	
	private ArrayList<Notes> notes;
	private PropertyChangeSupport propertyChange = new PropertyChangeSupport(this);
	
	public NotesModel() {
		notes = null; 
	}
	public NotesModel(ArrayList<Notes> notes) {
		setNotes(notes);
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
		ArrayList<Notes> oldNotes = getNotes();
		this.notes = notes;
		propertyChange.firePropertyChange("notes", oldNotes, notes);
	}
	public void addNote()	{
		
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy.MM.dd");  
		Date date = new Date(System.currentTimeMillis());  
		String data = formatter.format(date);  
		
		System.out.println("***"+data);
		
		Notes nowaNotka = new Notes("",data, data, 0);
		ArrayList<Notes> newNotesList = notes;
		newNotesList.add(nowaNotka);
		this.setNotes(newNotesList);
		for(Notes el: newNotesList)	{
			System.out.println("--- "+el.toString());
		}
			
	}
	public void deleteNote()	{
		
	}


	

}
