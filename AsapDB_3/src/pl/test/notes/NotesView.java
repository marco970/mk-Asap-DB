package pl.test.notes;

import java.awt.Container;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JTextArea;

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
//		if (nm.size()==0) {
//			JTextArea emptyTA = new JTextArea(5,40);
//			emptyTA.setEditable(false);
//			emptyTA.setBackground(null);
//			emptyTA.setBorder(null);
//			emptyTA.setText("Brak Notatek");
//			
//			this.add(emptyTA);
//		}
			
		this.setLayout(new MigLayout("wrap 1"));
		for (Notes el: notes)	{
//			SingleNote sno = new SingleNote(el.getDateOpen(), el.getDateModified(), el.getNoteId().toString(), el.getNote()); 
			SingleNote sno = new SingleNote(el);
			this.add(sno);
		}
		this.setBounds(0, 0, 510, 700);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
//		this.add((Component) evt.getNewValue());
//		System.out.println("PCE - jestem!!! "+evt.getPropertyName());
//		System.out.println("### "+evt.getNewValue().toString());
		Notes newNote = new Notes();
		newNote = (Notes) evt.getNewValue();
//		System.out.println("%%% "+ newNote.toString());
		SingleNote nsno = new SingleNote(newNote.getDateOpen(), newNote.getDateModified(), newNote.getNoteId().toString(), newNote.getNote());
		this.add(nsno, 0);
//		.add(nsno);
		this.revalidate();

		
		
		
	}
}
