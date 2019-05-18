package pl.test.notes;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import pl.asap.entity.Notes;
import pl.asap.models.MainTableModel;

@SuppressWarnings("serial")
public class NotesScreen extends JFrame {
	
	
	public NotesScreen(int rowNr, MainTableModel dane)	{
		
		super("Notatki");
		int idPostepowanie = dane.getId(rowNr);
		ReadNotes rn = new ReadNotes(idPostepowanie); //to do modelu
		ArrayList<Notes> notes = rn.getNotes(); //to tez?

		JPanel jpa = new JPanel();
		
		jpa.setLayout(new MigLayout("", "[grow]", "[grow][]"));
		JScrollPane jscrollpane = new JScrollPane();
		
		
		String ZZPZ = dane.getValueAt(rowNr, 0)+", "+dane.getValueAt(rowNr, 1);
		jpa.add(new JLabel(ZZPZ),"cell 0 0" );
		int j = 1;
		for (Notes el: notes)	{
			System.out.println(el.getNote()+" "+el.getNoteId());
			SingleNote sno = new SingleNote(el.getDateOpen(), el.getDateModified(), el.getNoteId().toString(), el.getNote()); 
			jpa.add(sno, "cell 0 "+j );
			j++;
		}

		jscrollpane.getViewport().add(jpa, null);
		add(jscrollpane, BorderLayout.CENTER);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 110, 110);
		pack();
		setVisible(true);
		
	}
	

	public static void main(String[] args) {
		new NotesScreen(3, new MainTableModel());
		

	}


}
