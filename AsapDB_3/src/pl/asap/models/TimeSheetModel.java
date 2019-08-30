package pl.asap.models;

import javax.swing.table.AbstractTableModel;

import pl.asap.transactions.timesheet.TimeSheetRead;


public class TimeSheetModel extends AbstractTableModel  {
	
	
	
	public TimeSheetModel(int month, int year)	{
		TimeSheetRead tsr2 = new TimeSheetRead(month, year);
		
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
