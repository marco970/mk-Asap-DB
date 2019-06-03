package pl.test.notes;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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


		JPanel jpa = new JPanel();
		
		jpa.setLayout(new MigLayout("", "[grow]", "[grow][]"));
		JScrollPane jscrollpane = new JScrollPane();
		

		
		JButton newButton = new JButton("Dodaj notatkę"); //to musi być menu
		newButton.addActionListener(this);
		jpa.add(newButton, "cell 0 1");
		
		jpa.add(notesView, "cell 0 2");
		int height = 120+nm.getNotes().size()*150;
		if (height==200) jpa.add(new JLabel("nie ma jeszcze notatek, dodaj pierwszą!"), "cell 0 2");


		jscrollpane.getViewport().add(jpa, null);
		add(jscrollpane, BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 520, height);
//		pack();
		setVisible(true);
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {

		nm.addNote();
		
	}



}
