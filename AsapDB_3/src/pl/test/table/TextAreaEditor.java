package pl.test.table;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

class TextAreaEditor extends DefaultCellEditor {
	   protected JScrollPane scrollpane;
	   protected JTextArea textarea;
	  
	   public TextAreaEditor() {
	      super(new JCheckBox());
	      scrollpane = new JScrollPane();
	      textarea = new JTextArea(); 
	      textarea.setLineWrap(true);
	      textarea.setWrapStyleWord(true);
	      textarea.setBorder(new TitledBorder("This is a JTextArea"));
	      scrollpane.getViewport().add(textarea);
	   }
	  
	   public Component getTableCellEditorComponent(JTable table, Object value,
	                                   boolean isSelected, int row, int column) {
	      textarea.setText(value.toString());
	  
	      return scrollpane;
	   }
	  
	   public Object getCellEditorValue() {
	      return textarea.getText();
	   }
	}
