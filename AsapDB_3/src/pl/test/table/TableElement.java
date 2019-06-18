package pl.test.table;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class TableElement extends JTable implements PropertyChangeListener {
	
	public TableElement(AbstractTableModel atm) {
		super(atm);
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		
		this.setModel((TableModel) e.getNewValue());
		
	}

}
