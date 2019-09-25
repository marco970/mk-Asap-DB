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
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import pl.asap.models.TimeSheetModel3;

public class EntryEditForm extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Set<String> checkIfOpenPara = new HashSet<String>();
	private EntryMouseListener eml;
	private JButton btnSave;
	private JButton btnCancel;
	private TimeSheetModel3 model;
	private int rowNr;
	private int colNr;
	private JComboBox<Integer> jcb;
	
	
	private EntryEditForm(EntryMouseListener eml, String nazwa, TimeSheetModel3 model, int rowNr, int colNr)	{
		super("Czas pracy dla: "+nazwa);
		this.model = model;
		this.rowNr = rowNr;
		this.colNr = colNr;
		
		this.eml = eml;

		int currentValue = Integer.valueOf(model.getValueAt(rowNr, colNr).toString());
		Vector<Integer> vector = new Vector<>();
		for (int i = 0; i<= 8; i++)	{	//wartość początkowa i może być zmieniona
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
//		System.out.println(colName.substring(14, 19)+" "+colName.substring(23, 26));
		
		panel.add(new JLabel(model.getColumnName(colNr)), "cell 0 5");
		panel.add(jcb, "cell 1 5");

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
//		for(String el: checkIfOpenPara)	{
//			System.out.println("set: "+el);
//		}
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
		
//		System.out.println("action: "+u);
		if(u=="Zapisz")	{
//			System.out.println(jcb.getSelectedItem().toString());
			
			model.setValueAt(jcb.getSelectedItem(), rowNr, colNr);
		}
		
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); //to zamiast dispose, bo na to reaguje WindowsCloseListener
	}

}
