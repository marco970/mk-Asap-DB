package pl.test.table;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import pl.asap.models.NotesModel;
import pl.asap.transactions.NoteUpdate;

@SuppressWarnings("serial")
public class TableElement extends JTable implements PropertyChangeListener {
	
//	private static final TableElement pte = new Table
	
	public TableElement(NotesModel atm) {
		super(atm);
		System.out.println("-------te--------> "+this.getClass());
		setTableAtributes(this, atm);

		
	}
	
	
	
	@Override
	public void propertyChange(PropertyChangeEvent e) {
		System.out.println("-------x--------> "+this.getClass());
//		((NotesModel) e.getNewValue()).newNote();
		this.setModel((NotesModel) e.getNewValue());
		setTableAtributes(this, (NotesModel) e.getNewValue());
	}
	public void setTableAtributes(JTable table, NotesModel nm)	{ //potrzebne? - tak
		
		table.setCellSelectionEnabled(true);
		
		TableColumnModel tcm = table.getColumnModel();	
		TableColumn tc = tcm.getColumn(2);	
		
		table.getModel().addTableModelListener(new NoteUpdate(nm));		
		
		tc.setCellRenderer(new TextAreaRenderer());
		tc.setCellEditor(new TextAreaEditor());

		setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

		tcm.getColumn(0).setPreferredWidth(20);
		tcm.getColumn(1).setPreferredWidth(20);
		tcm.getColumn(2).setPreferredWidth(400);
		tcm.getColumn(3).setPreferredWidth(20);
		
		
		
		table.revalidate();
	}

}
