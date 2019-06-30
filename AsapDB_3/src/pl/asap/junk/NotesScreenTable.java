package pl.asap.junk;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import pl.asap.models.NotesModel;
import pl.asap.transactions.NoteUpdate;
import pl.test.table.TextAreaEditor;
import pl.test.table.TextAreaRenderer;

public class NotesScreenTable implements ActionListener, TableModelListener {
	
	private JTable table;
	private NotesModel notesModel;
	private JFrame frame;
	
	public NotesScreenTable(NotesModel notesModel, String frameTitle)	{
		frame = new JFrame(frameTitle);
		
		this.notesModel = notesModel;
		table = new JTable(notesModel);
		table.getModel().addTableModelListener(new NoteUpdate(notesModel));		//to jest istotne
		JMenu menu = new JMenu("start");
		JMenuBar menuBar = new JMenuBar();

		JMenuItem mi = mi("dodaj nową notatkę");
		menu.add(mi);

		menuBar.add(menu);
		frame.setJMenuBar(menuBar);
		
		TableColumnModel tcm = table.getColumnModel();	
		TableColumn tc = tcm.getColumn(2);				
				
		tc.setCellRenderer(new TextAreaRenderer(notesModel));
		tc.setCellEditor(new TextAreaEditor());
		
		table.setRowHeight(80);		//to do poprawy
		
		if(notesModel.getRowCount()==0)	{
			JLabel brakNotatek = new JLabel("w postępowaniu nie ma jeszcze notatek...");
			formatuj(brakNotatek);
			frame.add(brakNotatek, BorderLayout.NORTH);
		}
		else	{
			JScrollPane scrollPane = new JScrollPane(table);
			frame.add(scrollPane, BorderLayout.CENTER);
		}
		

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(1200, 700);
		frame.setVisible(true);
		
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
	
	public JMenuItem mi(String str, String acc, int mnem)	{
		JMenuItem mi = new JMenuItem(str);
		mi.addActionListener(this);	
		mi.setAccelerator(KeyStroke.getKeyStroke(acc));
		mi.setMnemonic(mnem);
		mi.setActionCommand(str);
		return mi;
	}
	public void doMassAddMenu(JMenu nazwa, JMenuItem...args)	{
		for (JMenuItem el: args)	{
			if (el==null)	{
				nazwa.addSeparator();
			}
			else	{
				nazwa.add(el);
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("dodaj nową notatkę")) {
			System.out.println("z komendą działa");
			notesModel.newNote();
			table.revalidate();
			table.repaint();
			frame.repaint();
			frame.revalidate();
		}
			
	}
	private void formatuj (JComponent c)	{
		c.setFont(new Font("sansserif", Font.PLAIN, 12));
	}
	@Override		//do wywalenia
	public void tableChanged(TableModelEvent e) {
		System.out.println("helo zmiana tabeli");

		table.revalidate();
		table.repaint();
		frame.repaint();
		frame.revalidate();
	}


}
