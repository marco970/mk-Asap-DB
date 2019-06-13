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
import javax.swing.text.TableView;
import javax.swing.text.TableView.TableRow;

import pl.test.notes.NoteUpdateTest;

@SuppressWarnings("serial")
public class NotesScreenTable extends JFrame implements FocusListener {
	
	private JTable table;
	private AbstractTableModel notesModel;
	
	public NotesScreenTable(AbstractTableModel notesModel, String frameTitle)	{
		super(frameTitle);
		
		this.notesModel = notesModel;
		table = new JTable(notesModel);
		table.getModel().addTableModelListener(new NoteUpdateTest());
		//table.addFocusListener(this);
//		table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		
		if (table.isEditing())	{
			System.out.println("uwaga...");
			table.getCellEditor().stopCellEditing();
		}
		
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
		System.out.println("FGained --- wiersz: "+table.getSelectedRow()+" kolumna: "+ 
				table.getSelectedColumn()+" wartość: "+
				notesModel.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		
		System.out.println("FLost --- wiersz: "+table.getSelectedRow()+" kolumna: "+ 
		table.getSelectedColumn()+" wartość: "+
		notesModel.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
		
	}

}
