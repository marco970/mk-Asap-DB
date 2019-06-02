package pl.test.notes;

import java.awt.Container;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import pl.asap.entity.Notes;
import pl.asap.models.NotesModel;

@SuppressWarnings("serial")
public class NotesView extends Container implements PropertyChangeListener {
	
	private ArrayList<Notes> notes;
	private int idPostepowanie;
	
	public NotesView(NotesModel nm, int idPostepowanie)	{
		
		this.notes = nm.getNotes();
		
		this.setLayout(new GridLayout(notes.size(),1));

//		int j = 2;
		for (Notes el: notes)	{
			SingleNote sno = new SingleNote(el.getDateOpen(), el.getDateModified(), el.getNoteId().toString(), el.getNote()); 
			this.add(sno);
//			j++;
		}

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
//		this.add((Component) evt.getNewValue());
		System.out.println("PCE - jestem!!! "+evt.getPropertyName());
		System.out.println("### "+evt.getNewValue().toString());
		ReadNotes rn = new ReadNotes(idPostepowanie); //to do modelu
		notes = rn.getNotes(); //to tez?
		for (Notes el: notes)	{
			SingleNote sno = new SingleNote(el.getDateOpen(), el.getDateModified(), el.getNoteId().toString(), el.getNote()); 
			this.add(sno);
		}

		
		
		
	}
}
