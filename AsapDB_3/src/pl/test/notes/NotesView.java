package pl.test.notes;

import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;

import pl.asap.entity.Notes;
import pl.asap.models.NotesModel;

@SuppressWarnings("serial")
public class NotesView extends Container {
	
	private ArrayList<Notes> notes;
	
	public NotesView(NotesModel nm)	{
		
		this.notes = nm.getNotes();
//		ReadNotes rn = new ReadNotes(idPostepowanie); //to do modelu
//		notes = rn.getNotes(); //to tez?
		this.setLayout(new GridLayout(notes.size(),1));
		
		
		int j = 2;
		for (Notes el: notes)	{
//			System.out.println(el.getNote()+" "+el.getNoteId());
			SingleNote sno = new SingleNote(el.getDateOpen(), el.getDateModified(), el.getNoteId().toString(), el.getNote()); 
			this.add(sno);
			j++;
		}
		
		

	}
}
