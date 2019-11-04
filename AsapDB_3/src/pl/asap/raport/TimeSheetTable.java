package pl.asap.raport;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import pl.asap.models.MainTableModel;
import pl.asap.models.TimeSheetModel3;

public class TimeSheetTable extends JFrame implements ActionListener {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTable table;
	private TimeSheetModel3 tsm;

	private int year; 
	private String u; 
	private String w;
	private String v;
	private int month;
	

	public TimeSheetTable(int month, int year, String u, String w, String v)	{
		super("Edycja Czasu Pracy");
		
		this.year = year;
		this.u = u;
		this.w = w;
		this.v = v;
		this.month = month;
		
		this.tsm =new TimeSheetModel3(month, year, u, w, v);
		this.table = new JTable(tsm);
		JMenuBar menuBar = new JMenuBar();
		String[] itemy = {"raport" ,"generuj raport", "powrót do formularza"};
		doMassAddMenu(menuBar, itemy);
		setJMenuBar(menuBar);
		
		EntryMouseListener eml = new EntryMouseListener(table, tsm, this);
		table.addMouseListener(eml);

		table.setCellSelectionEnabled(true);
		
		table.setAutoCreateRowSorter(true);
		
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
		String kom = e.getActionCommand();
		
		if (kom.equals("powrót do formularza"))	{
//			MainTableModel mod = new MainTableModel();
			
			
			SwingUtilities.invokeLater(new Runnable() {
			      @Override
			      public void run() {
			    	  new RaportForm();
			      }
			    });
			dispose();
		}
		if (kom.equals("generuj raport"))	{
			try {
				new RaportExcell("Marcin Kuciak", month, year, u, w, v);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
	}
		
		public void doMassAddMenu(JPopupMenu popup, String...args)	{
			for (int i =0; i<=args.length-1; i++)	{
				JMenuItem menuItem = mi(args[i]);	
				popup.add(menuItem);
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
				
//		public static void main(String[] args) {
//
//			SwingUtilities.invokeLater(new Runnable() {
//			      @Override
//			      public void run() {
//			    	  new TimeSheetTable(9, 2019, "PLK", "", "CPO");
//			      }
//			    });
//		}

}
