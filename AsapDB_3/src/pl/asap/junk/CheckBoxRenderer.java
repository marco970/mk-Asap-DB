package pl.asap.junk;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
public class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {
	
	private JCheckBox cb;
	
	public CheckBoxRenderer(boolean isOpen)	{
		cb = new JCheckBox("", isOpen); //true oznacza open, zakreślone
		
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		
		return this;
	}

}
