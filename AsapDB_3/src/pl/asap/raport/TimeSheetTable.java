package pl.asap.raport;

import javax.swing.JFrame;

import pl.asap.transactions.timesheet.TimeSheetRead;

public class TimeSheetTable extends JFrame {
	
	
	public TimeSheetTable(int month, int year)	{
		super("Edycja Czasu Pracy");
		
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1200, 700);
		setVisible(true);
	}
	
	
	
	
	public static void main(String[] args) {
		new TimeSheetTable(8, 2019);
	}

}
