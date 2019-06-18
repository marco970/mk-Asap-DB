package pl.asap.junk;

import javax.swing.JTextArea;
import javax.swing.table.AbstractTableModel;

public class TestTableModel extends AbstractTableModel {
	
	private Object[][] dane;

	private Object[] nazwyKolumn = {"Nazwisko", "Imię","Notatka", "Aktywny" };
	
	private JTextArea notatka;
	
	public TestTableModel() {
		
		notatka = new JTextArea();
		notatka.setText("Hej, tu pisz...");
		
		Object[][] dane = {
				{"Kowalski", "Jan", "text1", new Boolean(true)},
				{"Jankowski", "Karol", "text2", new Boolean(false)},
				{"Fajfus", "edward", "text3 ala ma kota", new Boolean(false)},
				};
		this.dane = dane;
		
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
	public Object getValueAt(int arg0, int arg1) {
		return dane[arg0][arg1];
	}
    public boolean isCellEditable(int row, int column)
    {
       return !(column == 0);
    }

}
