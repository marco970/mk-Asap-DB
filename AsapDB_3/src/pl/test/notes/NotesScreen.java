package pl.test.notes;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import pl.asap.entity.Notes;
import pl.asap.logic.MainTableModel;

@SuppressWarnings("serial")
public class NotesScreen extends JFrame {
	
	
	public NotesScreen(int rowNr, MainTableModel dane)	{
		
		super("Notatki");
		int idPostepowanie = dane.getId(rowNr);
		ReadNotes rn = new ReadNotes(idPostepowanie);
		ArrayList<Notes> notes = rn.getNotes();
		for (Notes el: notes)	{
			System.out.println(el.getNote()+" "+el.getNoteId());
			
		}
		
		
		SingleNote sn1 = new SingleNote("2019.02.30", "2019.04.11", "3", "");
		SingleNote sn2 = new SingleNote("2019.03.22", "2019.04.15", "5", "coś");
		SingleNote sn3 = new SingleNote("2019.03.11", "2019.04.21", "9", "nic na razie");

		JPanel jpa = new JPanel();
		
		jpa.setLayout(new MigLayout("", "[grow]", "[grow][]"));
		JScrollPane jscrollpane = new JScrollPane();
		
		
		String ZZPZ = dane.getValueAt(rowNr, 0)+", "+dane.getValueAt(rowNr, 1);
		jpa.add(new JLabel(ZZPZ),"cell 0 0" );
		jpa.add(sn1, "cell 0 1");
		jpa.add(sn2, "cell 0 2");
		jpa.add(sn3, "cell 0 3");
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
