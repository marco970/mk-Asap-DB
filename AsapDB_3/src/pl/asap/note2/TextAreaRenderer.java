package pl.asap.note2;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;

import pl.asap.models.NotesModel;

public class TextAreaRenderer extends JScrollPane implements TableCellRenderer //, TableModelListener
{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textarea;
	private NotesModel nm;
  
   public TextAreaRenderer() {
	  this.nm = nm;
	  this.setBorder(BorderFactory.createEmptyBorder());
      textarea = new JTextArea();
      textarea.setLineWrap(true);
      textarea.setWrapStyleWord(true);
      
      getViewport().add(textarea);
   }
  


public Component getTableCellRendererComponent(JTable table, Object value,
                                  boolean isSelected, boolean hasFocus,
                                  int row, int column)
   {

      textarea.setText(value.toString());
      table.setRowHeight(row, getPreferredSize().height+10);
      textarea.setCaretPosition(0);	//dopracować
      return this;
   }

//@Override
//public void tableChanged(TableModelEvent e) {		//po co to?
//	System.out.println("--------rendererTabChanged--------->"+e.getSource().toString());
//	
//}

}
