package pl.asap.logic;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import pl.asap.DB.DBConnect;
import pl.asap.models.MainTableModel;
import pl.asap.raport.RaportForm;

	
public class EkranGlowny implements ActionListener {
	
	private JFrame eg;
	private int width, height;
	private String tytul;
	private JMenuBar menuBar;
	private MainTableModel data;
	private JTable lista;
	private JMenu raport=null;
	private ImageIcon img = new ImageIcon("gui-elements/face1.png");

	private String[] start = {"Start", "Nowe postępowanie", "Raport miesięczny", "Exit"};
	private String[] sort = {"Sort","Nieaktywne", "Aktywne","Zawieszone", "Zakończone", "Wszystkie"};
	private String[] toDo = {"ToDo", "Lista", "Notatki"};
	private String[] notatki = {"Notatki","Nowa notatka","Edytuj"};
	private String[] popupStr = {"modyfikacja", "zmień daty", "zakończ postępowanie", "zawieś postepowanie", "otwórz folder","notatki"};
	
	private static String[] nazwaMies = { "styczeń", "luty", "marzec", "kwiecień",
            "maj", "czerwiec", "lipiec", "sierpień",
            "wrzesień", "październik", "listopad", "grudzień"
          };
	
	//sortowanie filtrowanie
	private TableRowSorter<MainTableModel> sorter;
	private RowFilter<Object, Object> filter;
	
	private AbstractTableModel dataModel;
	private DBConnect dbConnect;
	
	private class CloseListener implements ActionListener{	//czy to jest potrzebne?
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        
	        System.exit(0);
	    }
	}
	
	public EkranGlowny(AbstractTableModel dataModel)	{ //do wywalenia
		
		this.dataModel = dataModel;
		//img = new ImageIcon("/gui-elements/sleepy-worker-at-work.png");

		SwingUtilities.invokeLater(new Runnable() {
		      @Override
		      public void run() {
		        createGui(tytul);
		      }
		    });
	}
	
	public EkranGlowny(DBConnect dbConnect) {
		this.dbConnect = dbConnect;
		MainTableModel dane = new MainTableModel();
		data = dane;
		//img = new ImageIcon("gui-elements/sleepy-worker-at-work.png");

		SwingUtilities.invokeLater(new Runnable() {
		      @Override
		      public void run() {
		        createGui(tytul);
		      }
		    });
	}
	
	public void createGui(String tytul)	{

		eg = new JFrame("ASap - Lista Postępowań");
		
		width = data.getColumnCount()*100;
		height=	data.getRowCount()*15+200;	
		eg.setSize(width, height);
		eg.setIconImage(img.getImage());
		
		lista = new JTable(data);

		sorter = new TableRowSorter<MainTableModel>(data);
		
			sorter.setComparator(0, new CompareZZ());
			sorter.setComparator(2, new CompareZZ());
			sorter.setComparator(3, new CompareZZ());
			sorter.setComparator(10, new Compare());
			sorter.setComparator(11, new Compare());
			sorter.setComparator(12, new Compare());
			sorter.setComparator(13, new Compare());
		
		lista.setRowSorter(sorter);

	    filter = new RowFilter<Object, Object>() {
		      public boolean include(Entry<?, ?> entry) {
		        String status = (String) entry.getValue(4);
		        return !("".equals(status) || status == null);
		      }
		    };

		sorter.setRowFilter(filter);
		lista.setRowSorter(sorter);
		Dimension dim = new Dimension(width, height);
		lista.setPreferredSize(dim);
		JScrollPane scroll = new JScrollPane(lista);
		
		eg.add(scroll);
		
		menuBar = new JMenuBar();
		
		doMassAddMenu(menuBar, start);
		doMassAddMenu(menuBar, sort);

		eg.setJMenuBar(menuBar);
		
		PopupMenuBean pmb = new PopupMenuBean(popupStr);
		
		TableMouseListener tbml = new TableMouseListener(lista, data, pmb);
		PopupContent pc = new PopupContent(lista, data, popupStr);
		
		pmb.addPropertyChangeListener(pc);
		
		lista.addMouseListener(tbml);

		lista.setComponentPopupMenu(pc);

		eg.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		eg.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent event) {
	        	
	        	if (dbConnect.getProcess()!=null) {
					dbConnect.getProcess().destroy();
				}
				eg.dispose();
	            System.exit(0);
	        }
	    });
		eg.setVisible(true);
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

		String u = e.getActionCommand();

		if (u.equals(start[3]))	{
			try {
				dbConnect.getProcess().destroy();
			} catch (Exception e2) {
			}
			System.exit(0);
		}

		if (u.equals(start[1]))	{
			new NewForm(data.getRowCount()+1, data);
		}
		if (u.equals(start[2])){
			new RaportForm(data);
		}
		
		if (u.equals(sort[1]))	{
		    filter = new RowFilter<Object, Object>() {
			      public boolean include(Entry<?, ?> entry) {
			        String status = (String) entry.getValue(4);
			        return !("aktywne".equals(status));
			      }
			    };
				sorter.setRowFilter(filter);
				lista.setRowSorter(sorter);

		}
		if (u.equals(sort[2]))	{
		    filter = new RowFilter<Object, Object>() {
			      public boolean include(Entry<?, ?> entry) {
			        String status = (String) entry.getValue(4);
			        return ("aktywne".equals(status));
			      }
			    };
				sorter.setRowFilter(filter);
				lista.setRowSorter(sorter);
		}
		if (u.equals(sort[3]))	{
		    filter = new RowFilter<Object, Object>() {
			      public boolean include(Entry<?, ?> entry) {
			        String status = (String) entry.getValue(4);
			        return ("zawieszone".equals(status));
			      }
			    };
				sorter.setRowFilter(filter);
				lista.setRowSorter(sorter);

		}
		if (u.equals(sort[4]))	{
		    filter = new RowFilter<Object, Object>() {
			      public boolean include(Entry<?, ?> entry) {
			        String status = (String) entry.getValue(4);
			        return ("zakonczone".equals(status));
			      }
			    };
				sorter.setRowFilter(filter);
				lista.setRowSorter(sorter);
		}
		if (u.equals(sort[5]))	{
		    filter = new RowFilter<Object, Object>() {
			      public boolean include(Entry<?, ?> entry) {
			        entry.getValue(4);
			        return (true);
			      }
			    };
				sorter.setRowFilter(filter);
				lista.setRowSorter(sorter);

		}
	}
}
