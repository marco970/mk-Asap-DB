package pl.asap.raport;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import pl.asap.models.TimeSheetModel3;
import pl.asap.transactions.timesheet.TSEOpisRead;
import pl.asap.transactions.timesheet.TimeSheetOpisUpdate;
import pl.asap.transactions.timesheet.TimeSheetRead;

public class EntryEditForm extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Set<String> checkIfOpenPara = new HashSet<String>();
	private EntryMouseListener eml;
	
	private JTextField opisTF;
	private JButton btnSave;
	private JButton btnCancel;
	private TimeSheetModel3 model;
	private int rowNr;
	private int colNr;
	private JComboBox<Integer> jcb;
//	private int entryId;
	
	
	private EntryEditForm(EntryMouseListener eml, String nazwa, TimeSheetModel3 model, int rowNr, int colNr)	{
		super("Czas pracy dla: "+nazwa);
		this.model = model;
		this.rowNr = rowNr;
		this.colNr = colNr;
		
		this.opisTF = new JTextField(15);
		//tu określam, entryId
			//muszę odczytać z modelu SAPnr i datę
//		System.out.println("|||||||||---- "+model.getValueAt(rowNr, 3));
//		System.out.println("|||||||||---- "+model.getColumnName(colNr).substring(13,16)+model.getMonthYear());
			//potem skorzystać z metody klasy TimeSheetEntryRead
//		TimeSheetRead tsr = new TimeSheetRead(model.getMonth(), model.getYear());
	
//		this.entryId = new TSEOpisRead(model.getValueAt(rowNr, 3).toString(),model.getColumnName(colNr).substring(14,16)+model.getMonthYear()).getEntryId();
//		System.out.println("--------- entryId "+entryId);
//		System.out.println("--------- xx "+model.getValueAt(rowNr, 3).toString());
//		System.out.println("--------- yy "+model.getColumnName(colNr).substring(14,16)+model.getMonthYear());
//		System.out.println("--------- opis "+new TSEOpisRead(model.getValueAt(rowNr, 3).toString(),model.getColumnName(colNr).substring(14,16)+model.getMonthYear()).getOpis());
		
		String opis = new TSEOpisRead(model.getValueAt(rowNr, 3).toString(),model.getColumnName(colNr).substring(14,16)+model.getMonthYear()).getOpis();
		
		opisTF.setText(opis);
		this.eml = eml;
		int totalDayTime = model.getTotalDayTime(colNr-4);
		
		
		int startValue = 8;
		

		int currentValue = Integer.valueOf(model.getValueAt(rowNr, colNr).toString());
//		System.out.println("totalDayTime = "+totalDayTime+" currentValue ="+currentValue );
		
		if(totalDayTime > 7)	{
			startValue = currentValue;
			
		}
		else	{
			startValue = 8-totalDayTime+currentValue;
		}
//		System.out.println("totalDayTime = "+totalDayTime+" currentValue = "+currentValue+" startValue = "+startValue );
		Vector<Integer> vector = new Vector<>();
		for (int i = 0; i<= startValue; i++)	{	//wartość początkowa i może być zmieniona
			vector.add(i);
		}

		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (checkIfOpenPara.contains("r"+rowNr+"c"+colNr)) checkIfOpenPara.remove("r"+rowNr+"c"+colNr);
//            	System.out.println("WindowClosingDemo.windowClosing--->EntryEditForm");
            }
        });
		
		//panel
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLayout(new MigLayout("", "[grow]", "[grow][][]"));
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("", ""));
		contentPane.add(panel, "cell 0 0,grow");
		panel.add(new JLabel("Ilość godzin dla "+ nazwa), "cell 0 0");
		
		jcb = new JComboBox<>(vector);
		jcb.setSelectedItem(currentValue);
		String colName = model.getColumnName(colNr);
		
		panel.add(new JLabel(model.getColumnName(colNr)), "cell 0 5");
		panel.add(jcb, "cell 1 5");
		panel.add(new JLabel("opis: "), "cell 0 6");
		panel.add(opisTF, "cell 1 6");

		btnSave = new JButton("Zapisz");
		btnCancel = new JButton("Anuluj");
		
		btnSave.addActionListener(this);
		btnCancel.addActionListener(this);
		
		contentPane.add(btnCancel, "cell 0 2");
		contentPane.add(btnSave, "cell 0 2");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 460, 300);
	}
	
	public static synchronized EntryEditForm getInstance(EntryMouseListener eml, String nazwa, TimeSheetModel3 mod, int rowNo, int colNo)	{

		if (!checkIfOpenPara.contains("r"+rowNo+"c"+colNo))	{
			checkIfOpenPara.add("r"+rowNo+"c"+colNo);
			return new EntryEditForm (eml, nazwa, mod, rowNo, colNo);
		}
		else {
			eml.popupOnOf(false);
			return null;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String u = e.getActionCommand();
//		System.out.println(u+" -----------");

		if(u=="Zapisz")	{
			
//			System.out.println(jcb.getSelectedItem().toString());
			model.setTotalDayTime(rowNr, colNr-4, Integer.valueOf(jcb.getSelectedItem().toString()));
			
			model.setValueAt(jcb.getSelectedItem(), rowNr, colNr);
			
			int entryId;
			entryId = new TSEOpisRead(model.getValueAt(rowNr, 3).toString(),model.getColumnName(colNr).substring(14,16)+model.getMonthYear()).getEntryId();

			if ((Integer.valueOf(jcb.getSelectedItem().toString()) >0 && entryId>0)) {
//				System.out.println("uwaga, zapis z pola tekst " + opisTF.getText());
				new TimeSheetOpisUpdate(entryId, opisTF.getText());
			}
		}
		
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); //to zamiast dispose, bo na to reaguje WindowsCloseListener
	}
	public String numString(int num)	{
		String numStr;
		if (num<10) numStr = "0"+ num;
		else numStr = ""+num;
		return numStr;
}

}
