package pl.asap.junk;

import java.awt.Container;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;

import net.miginfocom.swing.MigLayout;
import pl.asap.entity.Notes;
import pl.asap.models.NotesModel;

@SuppressWarnings("serial")
public class NotesView extends Container implements PropertyChangeListener {
	
	private ArrayList<Notes> notes;
	private int idPostepowanie;
	
	public NotesView(NotesModel nm, int idPostepowanie)	{
		
		this.notes = nm.getNotes();
		Collections.reverse(notes);
		this.setLayout(new MigLayout("wrap 1"));
		for (Notes el: notes)	{
//			SingleNote sno = new SingleNote(el.getDateOpen(), el.getDateModified(), el.getNoteId().toString(), el.getNote()); 
//			this.add(sno);
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
//		this.add((Component) evt.getNewValue());
//		System.out.println("PCE - jestem!!! "+evt.getPropertyName());
//		System.out.println("### "+evt.getNewValue().toString());
		Notes newNote = new Notes();
		newNote = (Notes) evt.getNewValue();
//		System.out.println("%%% "+ newNote.toString());
//		SingleNote nsno = new SingleNote(newNote.getDateOpen(), newNote.getDateModified(), newNote.getNoteId().toString(), newNote.getNote());
//		this.add(nsno, 0);
//		.add(nsno);
		this.revalidate();

		
		
		
	}
}
