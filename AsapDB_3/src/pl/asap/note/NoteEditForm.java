package pl.asap.note;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import pl.asap.models.NotesModel;

public class NoteEditForm extends JFrame implements ActionListener {
	
	private static Set<Integer> checkIfOpen = new HashSet<Integer>();
	
	private int colCount;
	private NotesModel model;
	private int rowNr;
	
	JTextArea ta;
	
	private JButton btnSave;
	private JButton btnCancel;
	
	
	private NoteEditForm(String nazwa, NotesModel model, int rowNr)	{
		super("Postępowanie "+nazwa);
		
		this.model = model;
		this.rowNr = rowNr;
		this.colCount=model.getColumnCount();
		
		ta = new JTextArea(20,25);
		ta.setText(model.getValueAt(rowNr, 2).toString());
		ta.setLineWrap(true);
		JScrollPane sp = new JScrollPane(ta);
		
		

		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
//                if (checkIfOpen.contains(rowNo)) checkIfOpen.remove(rowNo);
            	System.out.println("WindowClosingDemo.windowClosing--->NoteEditForm");
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
		panel.add(new JLabel("Edycja notatki"), "cell 0 0");
		panel.add(new JLabel(model.getColumnName(0)), "cell 0 2");
		panel.add(new JLabel(model.getColumnName(1)), "cell 0 3");
		panel.add(new JLabel(model.getColumnName(2)), "cell 0 4");
		panel.add(new JLabel(model.getColumnName(3)), "cell 0 5");
		panel.add(new JLabel(model.getValueAt(rowNr, 0).toString()), "cell 1 2");
		panel.add(new JLabel(model.getValueAt(rowNr, 1).toString()), "cell 1 3");
		panel.add(sp, "cell 1 4");
		panel.add(new JLabel(model.getValueAt(rowNr, 3).toString()), "cell 1 5");
		
		
		btnSave = new JButton("Zapisz");
		btnCancel = new JButton("Anuluj");
		
		btnSave.addActionListener(this);
		btnCancel.addActionListener(this);
		
		contentPane.add(btnCancel, "cell 0 2");
		contentPane.add(btnSave, "cell 0 2");

		
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 460, 600);
	}
	
	public static synchronized NoteEditForm getInstance(String nazwa, int rowNo, NotesModel mod)	{
		if (!checkIfOpen.contains(rowNo))	{
			checkIfOpen.add(rowNo);
			return new NoteEditForm (nazwa, mod, rowNo);
		}
		else return null;
	}
	
	public static void main(String[] args) {		//testowe, do wywalenia
		NotesModel nm = new NotesModel(46);
		getInstance("ABCDE", nm.getRowNr(7), nm);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Anuluj"))	{
			if (checkIfOpen.contains(rowNr)) checkIfOpen.remove(rowNr);
			this.dispose(); 
		}
		if (e.getActionCommand().equals("Zapisz")) {
			System.out.println("Zapis");
			if (checkIfOpen.contains(rowNr)) checkIfOpen.remove(rowNr);
			model.updateNote(ta.getText(), rowNr, 2);
			
			this.dispose();
		}
		
		
	}
	
	

}
