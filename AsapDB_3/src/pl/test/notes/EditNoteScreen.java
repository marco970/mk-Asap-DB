package pl.test.notes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class EditNoteScreen extends JFrame implements ActionListener {
	
	private JButton btnSave = new JButton("Save");
	private JButton btnDelete = new JButton("Delete");
	private SingleNote sno;
	

	public EditNoteScreen(SingleNote sno)	{
		super("Edycja Notatki");
		this.sno = sno;
				
		JPanel jpa = new JPanel();
		jpa.setLayout(new MigLayout());
		
		jpa.add(sno, "wrap");
		jpa.add(btnSave);
		jpa.add(btnDelete);
		
		btnSave.addActionListener(this);
		btnDelete.addActionListener(this);
		add(jpa);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 200, 520, 300);
//		pack();
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(btnSave))	{
			System.out.println("zapisujemy: "+ sno.getNoteText());
		}
		if (e.getSource().equals(btnDelete))	{
			
		}
	}

}
