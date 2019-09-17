package pl.asap.raport;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import pl.asap.models.TimeSheetModel3;

public class TimeSheetTable extends JFrame implements ActionListener {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTable table;
	TimeSheetModel3 tsm;
	
	public TimeSheetTable(int month, int year)	{
		super("Edycja Czasu Pracy");
		this.tsm =new TimeSheetModel3(month, year);
		this.table = new JTable(tsm);
		
		
		
		EntryMouseListener eml = new EntryMouseListener(table, tsm, this);
		table.addMouseListener(eml);

		table.setCellSelectionEnabled(true);
		
		TableColumnModel tcm = table.getColumnModel();	
		tcm.getColumn(0).setPreferredWidth(230);
		tcm.getColumn(1).setPreferredWidth(200);
		tcm.getColumn(2).setPreferredWidth(100);
		tcm.getColumn(3).setPreferredWidth(230);
		
	    DefaultTableCellRenderer rendererAlign = new DefaultTableCellRenderer();
	    rendererAlign.setHorizontalAlignment(JLabel.CENTER);
	    for (int i =4; i<tsm.getColumnCount(); i++)	{
	    	tcm.getColumn(i).setCellRenderer(rendererAlign);
	    }
	    DefaultTableCellRenderer rendererDayOff = new DefaultTableCellRenderer();
	    rendererDayOff.setBackground(Color.lightGray);
	    rendererDayOff.setHorizontalAlignment(JLabel.CENTER);
	    for (int el: tsm.getCi().getWeekendDays())	{
	    	tcm.getColumn(el+3).setCellRenderer(rendererDayOff);
	    }
		
		
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
