package pl.asap.table;

import java.awt.Component;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class TextAreaEditor extends DefaultCellEditor {
	   protected JScrollPane scrollpane;
	   protected JTextArea textarea;
	   Border padding = BorderFactory.createEmptyBorder(0, 10, 0, 10);
	   public TextAreaEditor() {
	      super(new JTextField());
//	      System.out.println("---> "+this.getClass());
	      scrollpane = new JScrollPane();
	      textarea = new JTextArea(); 
//	      setBorder(BorderFactory.createCompoundBorder(getBorder(), padding));
	      textarea.setLineWrap(false);		//???
	      textarea.setWrapStyleWord(false);
	      textarea.setBorder(null);
	      textarea.setMargin(new Insets(3,3,3,3));
	      scrollpane.getViewport().add(textarea);
	      scrollpane.setBorder(null);
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
