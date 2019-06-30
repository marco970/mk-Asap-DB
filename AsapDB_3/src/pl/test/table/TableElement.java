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
	public void setTableAtributes(JTable table, NotesModel nm)	{
		TableColumnModel tcm = table.getColumnModel();	
		TableColumn tc = tcm.getColumn(2);	
//		TableColumn tcCheckBox = tcm.getColumn(3);	
		table.getModel().addTableModelListener(new NoteUpdate(nm));		
		
		tc.setCellRenderer(new TextAreaRenderer(nm));
		tc.setCellEditor(new TextAreaEditor());
//		tcCheckBox.setCellRenderer(new CheckBoxRenderer(nm.get));
		table.revalidate();
	}

}
