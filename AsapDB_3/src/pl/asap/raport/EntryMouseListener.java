package pl.asap.raport;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import pl.asap.models.TimeSheetModel;

public class EntryMouseListener extends MouseAdapter implements ActionListener {
	
	private JTable table;
	private TimeSheetModel model;
	JPopupMenu popup;
	
	public EntryMouseListener(JTable table, TimeSheetModel model)	{
		this.table = table;
		this.model = model;
		
		popup = new JPopupMenu();
		doMassAddMenu(popup, new String[]{"edytuj czas pracy"});
		
	}
	
	public void popupOnOf(boolean x)	{
		popup.setEnabled(x);
	}


	@Override
	   public void mouseClicked(MouseEvent e) {
		
//		popup.show(e.getComponent(), e.getX(), e.getY());
		 Point point = e.getPoint();
		 
		 int currentRow = table.rowAtPoint(point);
		 int currentCol = table.columnAtPoint(point);
		 
		 EntryEditForm.getInstance(this, model.getValueAt(currentRow, 0).toString(), model, currentRow, currentCol);
		 
//		 System.out.println("MouseEvent: "+e.isPopupTrigger());
		
	}
	
	public void doMassAddMenu(JPopupMenu popup, String...args)	{
		for (int i =0; i<=args.length-1; i++)	{
			JMenuItem menuItem = mi(args[i]);	
			popup.add(menuItem);
		}
	}
	
	public JMenuItem mi(String str)	{
		JMenuItem mi = new JMenuItem(str);
		mi.addActionListener(this);	
		mi.setActionCommand(str);
		return mi;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String u = e.getActionCommand();
		if (u.equals("edytuj czas pracy"))	{
			System.out.println("jak dalej?");
			int selectedRow = table.getSelectedRow();
			int selectedCol = table.getSelectedColumn();
			
			System.out.println("MouseEvent: row "+selectedRow+" Col: "+selectedCol);
		}
		
	}

}
