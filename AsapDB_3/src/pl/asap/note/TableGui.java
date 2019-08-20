package pl.asap.note;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import pl.asap.logic.PopupContent;
import pl.asap.models.NotesModel;
import pl.asap.note2.NotesMouseListener;

import javax.swing.FocusManager;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TableGui extends JFrame implements ActionListener{
	
//	private TableBean tb;
	int idPostepowanie;
	private static Set<Integer> checkIfOpen = new HashSet<Integer>();
	NotesModel nm;
	TableElement te;
		
	private TableGui(int idPostepowanie)	{
		super("Notatki - "+idPostepowanie);
		
		System.out.println("---------------> "+this.getClass());
		this.nm = new NotesModel(idPostepowanie);
		te = new TableElement(idPostepowanie);
//		this.tb = tb;
		this.idPostepowanie = idPostepowanie;
		
		JMenu menu = new JMenu("start");
		JMenuBar menuBar = new JMenuBar();

		JMenuItem mi1 = mi("dodaj nową notatkę");
		JMenuItem mi2 = mi("zapisz bierzącą notatkę");
		menu.add(mi1);
		menu.add(mi2);

		menuBar.add(menu);
		setJMenuBar(menuBar);
		JScrollPane scrollPane = new JScrollPane(te);
		add(scrollPane, BorderLayout.CENTER);

		PopupContent pc = new PopupContent(te, nm, new String[]{"delete", "edit"});
		NotesMouseListener nml = new NotesMouseListener(te, nm);
		te.addMouseListener(nml);
		te.setComponentPopupMenu(pc);
				
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1200, 700);
		setVisible(true);
		this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (checkIfOpen.contains(idPostepowanie)) checkIfOpen.remove(idPostepowanie);
            	System.out.println("WindowClosingDemo.windowClosing");
            }
        });
	}
	public static synchronized TableGui getInstance(int idPostepowanie)	{
		if (!checkIfOpen.contains(idPostepowanie))	{
			checkIfOpen.add(idPostepowanie);
			return new TableGui(idPostepowanie);
		}
		else return null;
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
//			System.out.println("-------abc--------> "+this.getClass());
//			System.out.println("z komendą działa");
//			NotesModel nm = tb.getAtm();
			System.out.println("-----nm1---> "+nm.getRowCount());
			nm.newNote();
			
//			NotesModel nm2 = new NotesModel(idPostepowanie);
//			System.out.println("-----nm2---> "+nm2.getRowCount());
//			tb.setAtm(nm2);
//			notesModel.newNote();
		}
		
		if (e.getActionCommand().equals("zapisz bierzącą notatkę")) { //to nie działa
			FocusManager focusMan = FocusManager.getCurrentManager();
			focusMan.focusNextComponent();			//moze dlatego, ze nie ma 
//			transferFocus();
			
		}
		
	}
}
