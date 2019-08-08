package pl.asap.note;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class NotesMouseListener extends MouseAdapter{
	private JTable table;
	private AbstractTableModel model; 

	public NotesMouseListener(JTable table, AbstractTableModel model) {
		super();
		this.table = table;
		this.model = model;
	}
	
	   @Override
	   public void mousePressed(MouseEvent event) {
	       Point point = event.getPoint();
	       int currentRow = table.rowAtPoint(point);

	       if (currentRow<0 || currentRow > model.getRowCount()) currentRow = model.getRowCount()-1;
	       table.setRowSelectionInterval(currentRow, currentRow);
	       
	       int selectedRow = table.getSelectedRow();
	       int realSelectedRow = table.convertRowIndexToModel(selectedRow);
	   }

}
