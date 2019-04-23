package pl.test;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javafx.scene.control.Separator;

public class NotesScreen extends JFrame {
	
	
	public NotesScreen()	{
		
		super("Notatki");
		SingleNote sn1 = new SingleNote("text1");
		SingleNote sn2 = new SingleNote("text2");
		int m = 10;
		int n = m*120;
		
		Container cp =  this.getContentPane();
		cp.setLayout(new GridLayout(m, 1));
		add(new JLabel("temat 1"));
		add(sn1);
		//add(new JLabel());
		add(sn2);
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, n);
		pack();
		setVisible(true);
		
	}

	public static void main(String[] args) {
		new NotesScreen();
		

	}

}
