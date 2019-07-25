package pl.asap.table;

import java.awt.Component;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;

import pl.asap.models.NotesModel;

public class TextAreaRenderer extends JScrollPane implements TableCellRenderer
{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textarea;
	public TextAreaRenderer() {
//		System.out.println("---> "+this.getClass());
		textarea = new JTextArea();
//		textarea.setLineWrap(true);
//		textarea.setWrapStyleWord(true);
	    textarea.setBorder(null);
	    
	    
//      textarea.setBorder(new TitledBorder("This is a JTextArea"));
      
      getViewport().add(textarea);
   }
//	Border padding = BorderFactory.createEmptyBorder(0, 10, 0, 10);
   public Component getTableCellRendererComponent(JTable table, Object value,
                                  boolean isSelected, boolean hasFocus,
                                  int row, int column)
   {
//      if (isSelected) {
//         setForeground(table.getSelectionForeground());
//         setBackground(table.getSelectionBackground());
//         textarea.setForeground(table.getSelectionForeground());
//         textarea.setBackground(table.getSelectionBackground());
//      } else {
//         setForeground(table.getForeground());
//         setBackground(table.getBackground());
//         textarea.setForeground(table.getForeground());
//         textarea.setBackground(table.getBackground());
//      }
//	  table.getModel().ge.setBackground(Color.RED);
//	  textarea.setMargin(new Insets(10,10,10,10));
//	  setBorder(BorderFactory.createCompoundBorder(getBorder(), padding));
      textarea.setText(value.toString());
      table.setRowHeight(row, getPreferredSize().height+20);
      textarea.setCaretPosition(0);	//dopracować
//      if ((boolean) nm.getValueAt(row, 3))	textarea.setBackground(Color.LIGHT_GRAY);
////      textarea.setEditable(false); - tu się nie da tego zrobić
      return this;
   }

//@Override
public void tableChanged(TableModelEvent e) {
	System.out.println("--------rendererTabChanged--------->"+e.getSource().toString());	//nie wiem, czy ten interfejs potrzebny
	
}

}
