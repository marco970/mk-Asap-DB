package pl.test.notes;

import java.awt.Component;
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
	
	public NotesView(NotesModel nm)	{
		
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
		
		this.add((Component) evt.getNewValue());

		
		
		
	}
}
