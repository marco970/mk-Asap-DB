package pl.asap.junk;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

class MyEditor extends DefaultCellEditor implements FocusListener {
	  public MyEditor() {
	    super(new JTextField());
	  }

	  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
	      int row, int column) {
	    JTextField editor = (JTextField) super.getTableCellEditorComponent(table, value, isSelected,
	        row, column);
	    editor.addFocusListener(this);

	    if (value != null)
	      editor.setText(value.toString());
	    if (column == 0) {
	      editor.setHorizontalAlignment(SwingConstants.CENTER);
	      editor.setFont(new Font("Serif", Font.BOLD, 14));
	    } else {
	      editor.setHorizontalAlignment(SwingConstants.RIGHT);
	      editor.setFont(new Font("Serif", Font.ITALIC, 12));
	    }
	    return editor;
	  }

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		System.out.println("Edytor");
		
	}
	}