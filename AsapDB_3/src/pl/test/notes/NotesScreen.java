package pl.test.notes;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import pl.asap.models.NotesModel;

@SuppressWarnings("serial")
public class NotesScreen extends JFrame implements ActionListener {
	
	private int idPostepowanie;
	private NotesModel nm;
	
	public NotesScreen(String ZZPZ, NotesModel nm, NotesView notesView, int idPostepowanie)	{ //NotesView w konstr
		
		super("Notatki - "+ ZZPZ);
		this.idPostepowanie = idPostepowanie;
		this.nm = nm;
//		ReadNotes rn = new ReadNotes(idPostepowanie); //to do modelu
//		ArrayList<Notes> notes = rn.getNotes(); //to tez?

		JPanel jpa = new JPanel();
		
		jpa.setLayout(new MigLayout("", "[grow]", "[grow][]"));
		JScrollPane jscrollpane = new JScrollPane();
		
//		String ZZPZ = dane.getValueAt(rowNr, 0)+", "+dane.getValueAt(rowNr, 1);
//		jpa.add(new JLabel(ZZPZ),"cell 0 0" );
		
		JButton newButton = new JButton("Dodaj notatkę"); //to musi być menu
		newButton.addActionListener(this);
		jpa.add(newButton, "cell 0 1");
		
		jpa.add(notesView, "cell 0 2");


		jscrollpane.getViewport().add(jpa, null);
		add(jscrollpane, BorderLayout.CENTER);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 110, 110);
		pack();
		setVisible(true);
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		//tu robimy nową notatkę
		/*
		 * 
		 */
		nm.addNote();
		
	}



}
