package pl.test.table;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

@SuppressWarnings("serial")
public class NotesScreenTable extends JFrame {
	
	public NotesScreenTable(AbstractTableModel notesModel, String frameTitle)	{
		super(frameTitle);
		
		JTable table = new JTable(notesModel);
		TableColumnModel tcm = table.getColumnModel();
		TableColumn tc = tcm.getColumn(2);
		
		tc.setCellRenderer(new TextAreaRenderer());
		tc.setCellEditor(new TextAreaEditor());
		
		table.setRowHeight(80);
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(300, 500);
		setVisible(true);
		
	}

}
