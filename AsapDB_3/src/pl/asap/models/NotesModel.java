package pl.asap.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import pl.asap.entity.Notes;
import pl.test.notes.NewNote;
import pl.test.notes.ReadNotes;

public class NotesModel {
	
	
	private ArrayList<Notes> notes;
	private PropertyChangeSupport propertyChange = new PropertyChangeSupport(this);
	private int idPostepowanie;
	
	public NotesModel() {
		notes = null; 
	}
	public NotesModel(ArrayList<Notes> notes, int idPostepowanie) {
		this.notes=notes;
//		setNotes(notes);
		this.idPostepowanie = idPostepowanie;
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
		System.out.println("odpalam metodę z fire...");
		ArrayList<Notes> oldNotes = getNotes();
		this.notes = notes;
		propertyChange.firePropertyChange("notes", oldNotes, notes);
		System.out.println("+++nwe+++ "+notes.toString());
		System.out.println("+++old+++ "+oldNotes.toString());
	}
	public void addNote()	{
		
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy.MM.dd");  
		Date date = new Date(System.currentTimeMillis());  
		String data = formatter.format(date);  
		
		System.out.println("***"+data);
		
		Notes nowaNotka = new Notes("",data, data, 0);
		ArrayList<Notes> newNotesList = notes;
//		new NewNote(idPostepowanie,nowaNotka);
		newNotesList.add(nowaNotka);
		
		
		setNotes(newNotesList);
		for(Notes el: newNotesList)	{
			System.out.println("--- "+el.toString());
		}
			
	}
	public void deleteNote()	{
		
	}


	

}
