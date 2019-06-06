package pl.test.notes;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import pl.asap.entity.Notes;
import pl.asap.transactions.UpdateTrans;


@SuppressWarnings("serial")
public class SingleNote extends Container implements ActionListener, FocusListener	{
	private JCheckBox chBox;
	private TextArea ta;
	
	private String dateStart;
	private String dateLastChange;
	private String noteId;
	private String noteText;
	private Notes notka;
	private int isOpen;
	
	
	private int idPostepowanie;
	
	
	public SingleNote(String dateStart, String dateLastChange, String noteId, String noteText, int idPostepowanie)	{
		super();
		this.dateStart = dateStart;
		this.dateLastChange = dateLastChange;
		this.noteId = noteId;
		this.noteText = noteText;	
		this.notka = new Notes(noteText, dateStart, dateLastChange, 0); //?czy to potrzebne
		this.idPostepowanie = idPostepowanie;
		this.isOpen = 0;
		construct();
	}
	public SingleNote(Notes note, int postepowanieId)	{
		super();
		this.dateStart = note.getDateOpen();
		this.dateLastChange = note.getDateModified();
		this.noteId = note.getNoteId().toString();
		this.noteText = note.getNote();	
		this.idPostepowanie = idPostepowanie;
		this.notka = note;
		this.isOpen = note.getIsOpen();
		construct();
	}
	private void construct()	{
		this.setLayout(new GridLayout(1,3));
		JLabel messLab = new JLabel(dateStart);
		JLabel messLab2 = new JLabel(dateLastChange);

		chBox = new JCheckBox("Zamknij");
		chBox.setName(noteId);
		formatuj(messLab);
		formatuj(messLab2);
		formatuj(chBox);

		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(5, 1));
		leftPanel.add(messLab);
		leftPanel.add(messLab2);
		leftPanel.add(chBox);

		chBox.addActionListener(this);
		
		ta = new TextArea(5, 40);
		ta.addFocusListener(this);
		ta.setName(noteId);
		ta.setText(noteText);
		ta.setBackground(Color.WHITE);
		ta.setCaretPosition(ta.getText().length());
		
		JPanel panel = new JPanel();
		panel.add(leftPanel);
		panel.add(ta);
		this.add(panel);
	}
	private void formatuj (JComponent c)	{
		c.setFont(new Font("sansserif", Font.PLAIN, 12));
	}
	private void deactivate(TextArea ta2)	{
		//ta.setContentType("text/html");
		ta2.setEditable(false);
		ta2.setBackground(null);
		ta2.removeFocusListener(this);

	}
	private void activate(TextArea ta2)	{
		ta2.setEditable(true);
		ta2.setBackground(Color.WHITE);
		ta2.addFocusListener(this);
		ta2.setCaretPosition(ta2.getText().length());

	}
	@Override
	public void actionPerformed(ActionEvent e) {

		String a = "";
		if(chBox.isSelected()) {
			a = " zaznaczone";
			deactivate(ta);
		}
		else {
			a = " niezaznaczone"; 
			activate(ta);
		}
		System.out.println(chBox.getName()+" "+a);
		
	}
	@Override
	public void focusGained(FocusEvent arg0) {
		
		
	}
	@Override
	public void focusLost(FocusEvent ev) {
		System.out.println(ta.getName() +" focusLostAction " + ta.getText());
		this.noteText = ta.getText();
		
		Object[] note = {ta.getText(), ta.getName(), };
		notka.setNote(ta.getText());
		UpdateTrans updateNote = new UpdateTrans(notka);
		updateNote.updateNote(notka, Integer.parseInt(ta.getName()));
//		String[] note = n
		
	}


}
