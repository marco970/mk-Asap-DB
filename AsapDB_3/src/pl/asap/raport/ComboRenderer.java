package pl.asap.raport;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ComboRenderer implements TableCellRenderer {
	
	
	
	
	private JComboBox<Integer> cb;
	
	public ComboRenderer() {
		cb = new JComboBox();
	}

	@Override
	public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4,
			int arg5) {
		// TODO Auto-generated method stub
		return cb;
	}

}
