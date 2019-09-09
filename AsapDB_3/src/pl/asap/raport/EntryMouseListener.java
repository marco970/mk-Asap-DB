package pl.asap.raport;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;

import pl.asap.models.TimeSheetModel;

public class EntryMouseListener extends MouseAdapter implements ActionListener {
	
	private JTable table;
	private TimeSheetModel model;
	JPopupMenu popup;
	TimeSheetTable timeSheetTable;
	
	public EntryMouseListener(JTable table, TimeSheetModel model, TimeSheetTable timeSheetTable)	{
		this.table = table;
		this.model = model;
		this.timeSheetTable = timeSheetTable;
		
		popup = new JPopupMenu();
		doMassAddMenu(popup, new String[]{"edytuj czas pracy"});	
//		table.setComponentPopupMenu(popup);
		

		
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
		 
//		 EntryEditForm.getInstance(this, model.getValueAt(currentRow, 0).toString(), model, currentRow, currentCol);
		 
//		 System.out.println("MouseEvent: "+e.isPopupTrigger());
		 
		 System.out.println("abc -> SwingUtilities.isRightMouseButton(e) == "+SwingUtilities.isRightMouseButton(e));
		 
		 if(SwingUtilities.isRightMouseButton(e) == true)
		 {
			 System.out.println("SwingUtilities.isRightMouseButton(e) == "+SwingUtilities.isRightMouseButton(e));
		 int row = table.rowAtPoint(e.getPoint());
		 int col = table.columnAtPoint(e.getPoint());

		 table.clearSelection();
		 table.addColumnSelectionInterval(col, col);
		 table.addRowSelectionInterval(row,row);
//		 table.setComponentPopupMenu(popup);
		 popup.show(e.getComponent(), e.getX(), e.getY());
		 
		 }
		
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
			int currentRow = table.getSelectedRow();
			int currentCol = table.getSelectedColumn();
			if (true) {		//tu dać warunek dnia roboczego i obecności
				EntryEditForm.getInstance(this, model.getValueAt(currentRow, 0).toString(), model, currentRow,
						currentCol);
				//dodawać do listy wszystkie otwarte instancje
			}
			else JOptionPane.showMessageDialog(new JFrame(), "Tego dnia nie było Cię w pracy!");
			timeSheetTable.addWindowListener(new WindowAdapter() {
	            @Override
	            public void windowClosing(WindowEvent e) {
	               
	            	System.out.println("WindowClosingDemo.windowClosing--->TimeSheetTable");
//	            	przejść przez listę otwartych instancji i wszystkie pozamykać
//	            	frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
//	            	albo frame.dispose...
	            }
	        });
			System.out.println("MouseEvent: row "+currentRow+" Col: "+currentCol);
		}
		
	}

}