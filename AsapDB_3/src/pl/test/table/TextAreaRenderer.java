package pl.test.table;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableCellRenderer;

class TextAreaRenderer extends JScrollPane implements TableCellRenderer, FocusListener
{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textarea;
  
   public TextAreaRenderer() {
      textarea = new JTextArea();
      textarea.setLineWrap(true);
      textarea.setWrapStyleWord(true);
//      textarea.setBorder(new TitledBorder("This is a JTextArea"));
      textarea.addFocusListener(this);
      getViewport().add(textarea);
   }
  
   public Component getTableCellRendererComponent(JTable table, Object value,
                                  boolean isSelected, boolean hasFocus,
                                  int row, int column)
   {
      if (isSelected) {
         setForeground(table.getSelectionForeground());
         setBackground(table.getSelectionBackground());
         textarea.setForeground(table.getSelectionForeground());
         textarea.setBackground(table.getSelectionBackground());
      } else {
         setForeground(table.getForeground());
         setBackground(table.getBackground());
         textarea.setForeground(table.getForeground());
         textarea.setBackground(table.getBackground());
      }
  
      textarea.setText(value.toString());
      textarea.setCaretPosition(0);
      return this;
   }


//?wywalić to?
@Override
public void focusGained(FocusEvent eGained) {
	// TODO Auto-generated method stub
	
}

@Override
public void focusLost(FocusEvent eLost) {
	System.out.println("Hejka "+textarea.getText());
	
}
}
