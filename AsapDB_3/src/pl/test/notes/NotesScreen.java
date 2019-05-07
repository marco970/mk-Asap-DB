package pl.test.notes;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import pl.asap.entity.Lista;
import pl.asap.entity.Notes;

@SuppressWarnings("serial")
public class NotesScreen extends JFrame {
	
	
	public NotesScreen(int idPostepowanie)	{
		
		super("Notatki");
		
		ReadNotes rn = new ReadNotes(idPostepowanie);
		int i = 1;
		SingleNote[] sn = new SingleNote[rn.getNotes().size()];	//spróbuję tez arrayem
		ArrayList<SingleNote> sna = new ArrayList<SingleNote>();
		JPanel jpa = new JPanel();
		jpa.setLayout(new MigLayout("", "[grow]", "[grow][]"));
		jpa.add(new JLabel("numer ZZ, Numer PZ"),"cell 0 0" );
		for(Notes el: rn.getNotes())	{
			SingleNote snEl = new SingleNote(el.getDateOpen(), el.getDateModified(), el.getNoteId().toString(), el.getNote());
			//sna.add(new SingleNote(el.getDateOpen(), el.getDateModified(), el.getNoteId().toString(), el.getNote()));
			jpa.add(snEl, "cell 0 "+i);

			i++;
		}
//		SingleNote sn1 = new SingleNote("2019.02.30", "2019.04.11", "3", "");
//		SingleNote sn2 = new SingleNote("2019.03.22", "2019.04.15", "5", "coś");
//		SingleNote sn3 = new SingleNote("2019.03.11", "2019.04.21", "9", "nic na razie");


		JScrollPane jscrollpane = new JScrollPane();
		
		
//		jpa.add(sn1, "cell 0 1");
//		jpa.add(sn2, "cell 0 2");
//		jpa.add(sn3, "cell 0 3");
		jscrollpane.getViewport().add(jpa, null);
		add(jscrollpane, BorderLayout.CENTER);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 110, 110);
		pack();
		setVisible(true);
		
	}

	public static void main(String[] args) {
		new NotesScreen(1);
		

	}


}
