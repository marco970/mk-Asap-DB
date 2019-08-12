package pl.asap.note2;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import pl.asap.logic.PopupContent;
import pl.asap.models.NotesModel;
import pl.asap.note.NoteEditForm;
import pl.asap.note.NotesMouseListener;
import pl.asap.note.TextAreaRenderer;
import pl.asap.transactions.notes.NoteUpdate2;

public class NotesTable extends JFrame implements ActionListener {

	
	private int idPostepowanie;
	private static Set<Integer> checkIfOpen = new HashSet<Integer>();
	
	private JTable nt;
	
	private NotesTable(int idPostepowanie)	{
		super("tu dodać nazwę postępowania");
		this.idPostepowanie=idPostepowanie;
		NotesModel nm = new NotesModel(idPostepowanie);
		
		nt = new JTable(nm);
		
		nt.getModel().addTableModelListener(new NoteUpdate2()); 
		
		TableColumnModel tcm = nt.getColumnModel();	
		TableColumn tc = tcm.getColumn(2);	
		tc.setCellRenderer(new TextAreaRenderer(nm));
		nt.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		tcm.getColumn(0).setPreferredWidth(20);
		tcm.getColumn(1).setPreferredWidth(20);
		tcm.getColumn(2).setPreferredWidth(400);
		tcm.getColumn(3).setPreferredWidth(20);
		
		JMenu menu = new JMenu("start");
		JMenuBar menuBar = new JMenuBar();
		
		
		JMenuItem mi1 = mi("dodaj nową notatkę");
		JMenuItem mi2 = mi("zapisz bierzącą notatkę");
		menu.add(mi1);
		menu.add(mi2);

		
		menuBar.add(menu);
		setJMenuBar(menuBar);
		JScrollPane scrollPane = new JScrollPane(nt);
		add(scrollPane, BorderLayout.CENTER);

		PopupContent pc = new PopupContent(nt, nm, new String[]{"delete", "edit"});
		NotesMouseListener nml = new NotesMouseListener(nt, nm);
		nt.addMouseListener(nml);
		nt.setComponentPopupMenu(pc);
		
		
		this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (checkIfOpen.contains(idPostepowanie)) checkIfOpen.remove(idPostepowanie);
            	System.out.println("WindowClosingDemo.windowClosing");
            }
        });
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1200, 700);
		setVisible(true);
		//------
		System.out.println("-----tmL-------> now below");
		TableModelListener[] tmL = nm.getTableModelListeners();
		for(TableModelListener el: tmL)	{
			System.out.println(el.getClass()+" - "+el.toString());
		}

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
	
	
	
	
	public static synchronized NotesTable getNotesTableInstance(int idPostepowanie)	{
		if (!checkIfOpen.contains(idPostepowanie))	{
			checkIfOpen.add(idPostepowanie);
			return new NotesTable(idPostepowanie);
		}
		else return null;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("dodaj nową notatkę")) {

//			System.out.println("-----nm1---> "+nm.getRowCount());
			int b = ((NotesModel) nt.getModel()).getNotesMatrix().length;
			//System.out.println("qqqqq-----qqqq----> "+b);
			((NotesModel) nt.getModel()).newNote();
			b = ((NotesModel) nt.getModel()).getNotesMatrix().length;
			System.out.println("qqqqq-----qqqq----> "+b+" - "+ (NotesModel) nt.getModel().getValueAt(b-1, 0));
			NoteEditForm.getInstance("", b-1, (NotesModel) nt.getModel());
			
			

		}
		
	}

}
