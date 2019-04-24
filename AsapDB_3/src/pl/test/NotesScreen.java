package pl.test;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class NotesScreen extends JFrame {
	
	
	public NotesScreen()	{
		
		super("Notatki");
		SingleNote sn1 = new SingleNote("2019.02.30", "2019.04.11", "3");
		SingleNote sn2 = new SingleNote("2019.03.22", "2019.04.15", "5");
		//int m = 10;
		//int n = m*120;
		
		//Container cp =  this.getContentPane();
		JPanel jpa = new JPanel();
		
		jpa.setLayout(new MigLayout("", "[grow]", "[grow][]"));
		JScrollPane jscrollpane = new JScrollPane();
		
		jpa.add(new JLabel("numer ZZ, Numer PZ"),"cell 0 0" );
		jpa.add(sn1, "cell 0 1");
		jpa.add(sn2, "cell 0 2");
		jscrollpane.getViewport().add(jpa, null);
		add(jscrollpane, BorderLayout.CENTER);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 110, 110);
		pack();
		setVisible(true);
		
	}

	public static void main(String[] args) {
		new NotesScreen();
		

	}


}
