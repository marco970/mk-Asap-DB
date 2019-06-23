package pl.test.table;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import pl.asap.models.NotesModel;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class TableBean implements TableModelListener {
	
	private NotesModel atm = null;		//abstract table model

	public TableBean(NotesModel atm, int i) {
		this.atm = atm;
	}	
	public TableBean(NotesModel atm)	{
		setAtm(atm);
	}	
	private PropertyChangeSupport propertyChange = new PropertyChangeSupport(this);

	public synchronized void addPropertyChangeListener(PropertyChangeListener l) {
		propertyChange.addPropertyChangeListener(l);
	}
	public synchronized void removePropertyChangeListener(PropertyChangeListener l) {
		propertyChange.removePropertyChangeListener(l);
	}

	public NotesModel getAtm() {
		return atm;
	}
	public synchronized void setAtm(NotesModel atmParam) {
		System.out.println("-------set--------> "+this.getClass());
		AbstractTableModel oldModel = atm;
		System.out.println("old "+atm.getRowCount());
		atm = atmParam;
		System.out.println("new "+atmParam.getRowCount());
		propertyChange.firePropertyChange("atm", oldModel, atmParam);
	}
	@Override
	public void tableChanged(TableModelEvent e) {		//nie jestem pewny, czy ten interfejs powinien zostać
		System.out.println("-------tChang--------> "+this.getClass());
		setAtm((NotesModel) e.getSource()); 
		
	}
	


}