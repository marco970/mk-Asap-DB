package pl.asap.logic;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;

import pl.asap.models.MainTableModel;

public class TableMouseListener extends MouseAdapter  {
    
   private JTable table;
   private MainTableModel model;
   private PopupMenuBean pmb;
    
   public TableMouseListener(JTable table, MainTableModel model, PopupMenuBean pmb) {
       this.table = table;
       this.model = model;
       this.pmb = pmb;
   }
    
   @Override
   public void mousePressed(MouseEvent event) {
       Point point = event.getPoint();
       int currentRow = table.rowAtPoint(point);

       if (currentRow < 0 || currentRow > model.getRowCount()) currentRow = model.getRowCount()-1;
       table.setRowSelectionInterval(currentRow, currentRow);
       
       int selectedRow = table.getSelectedRow();
       int realSelectedRow = table.convertRowIndexToModel(selectedRow);
		
       String[] popupStr1 = {"modyfikacja", "zmień daty","otwórz folder","notatki"};
       String[] popupStr2 = {"modyfikacja", "zmień daty", "zakończ postępowanie", "zawieś postepowanie", "otwórz folder","notatki"};
       String[] popupStr3 = {"modyfikacja", "zmień daty", "odwieś postępowanie", "otwórz folder","notatki"};
       
       Object status = model.getValueAt(realSelectedRow, 4);
       
       if (status.equals("aktywne"))	{
    	   pmb.setPopupStr(popupStr2);
       }
       else if (status.equals("zawieszone")) {
    	   pmb.setPopupStr(popupStr3);
       }
       else pmb.setPopupStr(popupStr1);

       
   }

	   
}
