package pl.asap.models;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import pl.asap.entity.Notes;
import pl.test.notes.ReadNotes;

public class NotesModel {
	
	
	private ArrayList<Notes> notes;
	private PropertyChangeSupport propertyChange = new PropertyChangeSupport(this);
	
	public NotesModel() {
		notes = null; 
	}
	public NotesModel(ArrayList<Notes> notes) {
		
//		ReadNotes rn = new ReadNotes(idPostepowanie); //to do modelu
//		notes = rn.getNotes(); //to tez?
		setNotes(notes);
		
	}
	public ArrayList<Notes> getNotes() {
		return notes;
	}
	public void setNotes(ArrayList<Notes> notes) {
		ArrayList<Notes> oldNotes = getNotes();
		this.notes = notes;
		propertyChange.firePropertyChange("notes", oldNotes, notes);
	}
	public void addNote()	{
		
	}
	

}
