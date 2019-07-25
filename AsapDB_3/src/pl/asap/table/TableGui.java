package pl.asap.table;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import pl.asap.logic.PopupContent;
import pl.asap.models.NotesModel;

import javax.swing.FocusManager;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class TableGui extends JFrame implements ActionListener{
	
	private TableBean tb;
	private TableElement te;
	int idPostepowanie;
	private static Set<Integer> checkIfOpen = new HashSet<Integer>();
		
	private TableGui(TableBean tb, TableElement te, int idPostepowanie)	{
		super("Notatki - "+idPostepowanie);
		System.out.println("---> "+this.getClass());
		this.tb = tb;
		this.te = te;
		this.idPostepowanie = idPostepowanie;
		this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
//                System.out.println("A is closing");
            }

            @Override
            public void windowClosed(WindowEvent e) {
//                System.out.println("A has closed");
                checkIfOpen.remove(TableGui.this.idPostepowanie);
//                showOpenWindows();
            }

        });
		
		
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

		PopupContent pc = new PopupContent(te, tb.getAtm(), new String[]{"delete"});
		NotesMouseListener nml = new NotesMouseListener(te, tb.getAtm());
		te.addMouseListener(nml);
		te.setComponentPopupMenu(pc);
				
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1200, 700);
		setVisible(true);
		
	}
	public static synchronized TableGui getInstance(TableBean tb, TableElement te, int idPostepowanie)	{
		if (!checkIfOpen.contains(idPostepowanie))	{
			checkIfOpen.add(idPostepowanie);
//			showOpenWindows();
			return new TableGui(tb, te, idPostepowanie);
		}
		else return null;
	}
//	private static void showOpenWindows()	{
//		for (Integer el: checkIfOpen)	{
//		System.out.print(el+", ");
//		}
//	}
	
	
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
			System.out.println("-------abc--------> "+this.getClass());
//			System.out.println("z komendą działa");
			NotesModel nm = tb.getAtm();
			System.out.println("-----nm1---> "+nm.getRowCount());
			nm.newNote();
			NotesModel nm2 = new NotesModel(idPostepowanie);
			System.out.println("-----nm2---> "+nm2.getRowCount());
			tb.setAtm(nm2);
//			notesModel.newNote();
		}
		
		if (e.getActionCommand().equals("zapisz bierzącą notatkę")) { //to nie działa
//			FocusManager focusMan = FocusManager.getCurrentManager();
//			focusMan.focusNextComponent();			//moze dlatego, ze nie ma 
//			transferFocus();
//			int col = te.getSelectedColumn()+1;
//			int row = te.getSelectedRow();
//			te.changeSelection(row, 0, true, false);
			if (!te.equals(null))	{
				if (!te.getCellEditor().equals(null)) te.getCellEditor().stopCellEditing();
			}
			

		}
		
	}
}