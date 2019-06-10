package pl.test.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

public class MyRenderer extends JTextArea implements TableCellRenderer {
	
	public MyRenderer()	{
		super(5,40);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
//		JTextArea editor = new JTextArea(5,40);
	    if (value != null) this.setText(value.toString());
	    this.setBackground((row % 2 == 0) ? Color.yellow : Color.cyan);

		return this;
	}
	

}
