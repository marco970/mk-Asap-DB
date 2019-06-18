package pl.test.table;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import pl.asap.models.NotesModel;
import pl.asap.transactions.NoteUpdateTest;

@SuppressWarnings("serial")
public class TableElement extends JTable implements PropertyChangeListener {
	
	public TableElement(NotesModel atm) {
		super(atm);
		System.out.println("-------te--------> "+this.getClass());
		
		TableColumnModel tcm = getColumnModel();	
		TableColumn tc = tcm.getColumn(2);			
		getModel().addTableModelListener(new NoteUpdateTest(atm));	
				
		tc.setCellRenderer(new TextAreaRenderer());
		tc.setCellEditor(new TextAreaEditor());
		
		setRowHeight(80);		//to do poprawy
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		System.out.println("-------x--------> "+this.getClass());
//		((NotesModel) e.getNewValue()).newNote();
		this.setModel((TableModel) e.getNewValue());
		
	}

}
