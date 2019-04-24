package pl.test;

import javax.swing.JLabel;

public class NoteForm extends BasicForm {

	
	public NoteForm(String nazwa)	{
		super();
		setTitle(nazwa);
		getContentPane().add(new JLabel("dupa"));
		add(new JLabel("Pipa"));
		setVisible(true);
		
	}
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		new NoteForm("łyse dziady");

	}

}
