package pl.test.table;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

public class TableGui extends JFrame implements ActionListener{
	
	
	public TableGui(TableBean tb, TableElement te)	{
		super("Notatki - ");
		
		JMenu menu = new JMenu("start");
		JMenuBar menuBar = new JMenuBar();

		JMenuItem mi = mi("dodaj nową notatkę");
		menu.add(mi);

		menuBar.add(menu);
		setJMenuBar(menuBar);
		int rc = tb.getAtm().getRowCount();
		
		if(tb.getAtm().getRowCount()==0)	{
			JLabel brakNotatek = new JLabel("w postępowaniu nie ma jeszcze notatek...");
//			formatuj(brakNotatek);
			add(brakNotatek, BorderLayout.NORTH);
		}
		else	{
			JScrollPane scrollPane = new JScrollPane(te);
			add(scrollPane, BorderLayout.CENTER);
		}
	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1200, 700);
		setVisible(true);
	}
	public void doMassAddMenu(JMenuBar mb, String...args)	{
		JMenu menu = new JMenu(args[0]);
		mb.add(menu);
		for (int i =1; i<=args.length-1; i++)	{
				JMenuItem menuItem = mi(args[i]);
				menu.add(menuItem);
		}
	}
	
	public JMenuItem mi(String str)	{
		JMenuItem mi = new JMenuItem(str);
		mi.addActionListener(this);	
		mi.setActionCommand(str);
		return mi;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("dodaj nową notatkę")) {
			System.out.println("z komendą działa");
//			notesModel.newNote();
		}
		
	}
}
