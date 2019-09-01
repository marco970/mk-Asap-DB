package pl.asap.raport;

import java.awt.BorderLayout;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import pl.asap.models.TimeSheetModel;

public class TimeSheetTable extends JFrame {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;




	public TimeSheetTable(int month, int year)	{
		super("Edycja Czasu Pracy");
		TimeSheetModel tsm =new TimeSheetModel(month, year);
		JTable table = new JTable(tsm);
		
	    MultiLineHeaderRenderer renderer = new MultiLineHeaderRenderer();
	    Enumeration<TableColumn> e = table.getColumnModel().getColumns();
	    while (e.hasMoreElements()) {
	      ((TableColumn) e.nextElement()).setHeaderRenderer(renderer);
	    }
		
		
		
		JScrollPane sc = new JScrollPane(table);
		
		add(sc, BorderLayout.CENTER);
		
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1800, 700);
		setVisible(true);
	}
	
	
	
	
	public static void main(String[] args) {
		new TimeSheetTable(8, 2019);
	}

}
