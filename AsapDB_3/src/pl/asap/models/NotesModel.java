package pl.asap.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import pl.asap.entity.Notes;
import pl.asap.transactions.AddNewNote;
import pl.asap.transactions.NewNote;
import pl.asap.transactions.ReadNotes;
import pl.test.notes.SingleNote;

public class NotesModel   {
	
	private ArrayList<Notes> notes;
	private Notes note;
	private PropertyChangeSupport propertyChange = new PropertyChangeSupport(this);
	private int idPostepowanie;
	
	public NotesModel() {
	}
	public NotesModel(ArrayList<Notes> notes, int idPostepowanie) {
//		super();
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
		Notes oldNote = new Notes("","","",0);
		notes.add(note);
		new AddNewNote(idPostepowanie, note);
		propertyChange.firePropertyChange("note", oldNote, note);
		
	}
	public void addNote()	{
		
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy.MM.dd");  
		Date date = new Date(System.currentTimeMillis());  
		String data = formatter.format(date);  
		Notes nowaNotka = new Notes("",data, data, 0);
		setNote(nowaNotka);

	}
	public void deleteNote()	{
		
	}


	

}
