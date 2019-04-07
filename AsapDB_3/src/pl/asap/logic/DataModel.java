package pl.asap.logic;

import javax.swing.table.AbstractTableModel;

import pl.asap.entity.Lista;
import pl.asap.transactions.ReadTrans;

public class DataModel extends AbstractTableModel {
	
	private ReadTrans readDB;
	private Object[][] dane;

	
	public DataModel(ReadTrans readDB)	{
		this.readDB = readDB;
		this.dane = readDB.getMatrix();
	}
	
	@Override
	public int getColumnCount() {
		
		return dane[0].length;
	}

	@Override
	public int getRowCount() {
		
		return dane.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		return dane[rowIndex][columnIndex];
	}

}
