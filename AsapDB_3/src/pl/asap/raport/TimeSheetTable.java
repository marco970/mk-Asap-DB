package pl.asap.raport;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import pl.asap.models.TimeSheetModel;

public class TimeSheetTable extends JFrame implements ActionListener {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTable table;


	public TimeSheetTable(int month, int year)	{
		super("Edycja Czasu Pracy");
		TimeSheetModel tsm =new TimeSheetModel(month, year);
		table = new JTable(tsm);
		
		EntryMouseListener eml = new EntryMouseListener(table, tsm);
		table.addMouseListener(eml);
		
		JPopupMenu popup = new JPopupMenu();
		
		doMassAddMenu(popup, new String[]{"edytuj czas pracy"});
		
		table.setComponentPopupMenu(popup);

		table.setCellSelectionEnabled(true);
		
		TableColumnModel tcm = table.getColumnModel();	
		
	    MultiLineHeaderRenderer renderer = new MultiLineHeaderRenderer();
	    Enumeration<TableColumn> e = table.getColumnModel().getColumns();
	    while (e.hasMoreElements()) {
	      ((TableColumn) e.nextElement()).setHeaderRenderer(renderer);
	    }
		
		
		
		JScrollPane sc = new JScrollPane(table);
		
		
		add(sc, BorderLayout.CENTER);
		
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1500, 700);
		setVisible(true);
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		String u = e.getActionCommand();
		if (u.equals("edytuj czas pracy"))	{
			System.out.println("jak dalej?");
			int selectedRow = table.getSelectedRow();
			int selectedCol = table.getSelectedColumn();
			
			System.out.println("MouseEvent: row "+selectedRow+" Col: "+selectedCol);
		}
		
	}
		
		public void doMassAddMenu(JPopupMenu popup, String...args)	{
			for (int i =0; i<=args.length-1; i++)	{
				JMenuItem menuItem = mi(args[i]);	
				popup.add(menuItem);
			}
		}
		
		public JMenuItem mi(String str)	{
			JMenuItem mi = new JMenuItem(str);
			mi.addActionListener(this);	
			mi.setActionCommand(str);
			return mi;
		}
		
		
		public static void main(String[] args) {
			new TimeSheetTable(9, 2019);
		}

}
