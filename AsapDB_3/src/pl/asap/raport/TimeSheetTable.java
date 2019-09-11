package pl.asap.raport;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import pl.asap.models.TimeSheetModel;

public class TimeSheetTable extends JFrame implements ActionListener {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTable table;
	TimeSheetModel tsm;
	
	public TimeSheetTable(int month, int year)	{
		super("Edycja Czasu Pracy");
		this.tsm =new TimeSheetModel(month, year);
		this.table = new JTable(tsm);
		
		EntryMouseListener eml = new EntryMouseListener(table, tsm, this);
		table.addMouseListener(eml);

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
			
			
			SwingUtilities.invokeLater(new Runnable() {
			      @Override
			      public void run() {
			    	  new TimeSheetTable(9, 2019);
			      }
			    });
		}

}
