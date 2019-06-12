package pl.test.table;

import java.awt.BorderLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

@SuppressWarnings("serial")
public class NotesScreenTable extends JFrame implements FocusListener {
	
	private JTable table;
	
	public NotesScreenTable(AbstractTableModel notesModel, String frameTitle)	{
		super(frameTitle);
		
		table = new JTable(notesModel);
		table.addFocusListener(this);
		TableColumnModel tcm = table.getColumnModel();
		TableColumn tc = tcm.getColumn(2);
		
		tc.setCellRenderer(new TextAreaRenderer());
		tc.setCellEditor(new TextAreaEditor());
		
		table.setRowHeight(80);
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(700, 500);
		setVisible(true);
		
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		System.out.println("Witam "+table.getCellEditor());
		
	}

}
