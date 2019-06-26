package pl.asap.junk;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class MainTableTest {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Hej, testuję JTable");
		TestTableModel ttm = new TestTableModel();
		JTable table = new JTable(ttm);
		
		TableColumnModel tcm = table.getColumnModel();
		TableColumn tc = tcm.getColumn(2);
		
//		tc.setCellRenderer(new TextAreaRenderer());
//		tc.setCellEditor(new TextAreaEditor());
		
		table.setRowHeight(80);
		

//		tc.setCellRenderer(new MyRenderer());
//		tc.setCellEditor(new MyEditor());
		
		
//		table.setDefaultRenderer(Object.class, new MyRenderer());

	    JScrollPane scrollPane = new JScrollPane(table);
	    frame.add(scrollPane, BorderLayout.CENTER);
	    frame.setSize(300, 150);
	    frame.setVisible(true);

	}

}
