package pl.test.table;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.table.AbstractTableModel;

public class TableBean {
	
	private AbstractTableModel atm;
//	private TableBean propertyChange;
	
	
	public TableBean() {
		
	}	
	public TableBean(AbstractTableModel atm)	{
		setAtm(atm);
	}	
	private PropertyChangeSupport propertyChange = new PropertyChangeSupport(this);

	public synchronized void addPropertyChangeListener(PropertyChangeListener l) {
		propertyChange.addPropertyChangeListener(l);
	}
	public synchronized void removePropertyChangeListener(PropertyChangeListener l) {
		propertyChange.removePropertyChangeListener(l);
	}

	public AbstractTableModel getAtm() {
		return atm;
	}
	public synchronized void setAtm(AbstractTableModel atmParam) {
		AbstractTableModel oldModel = atm;
		atm = atmParam;
		propertyChange.firePropertyChange("atm", oldModel, atm);
	}
	


}