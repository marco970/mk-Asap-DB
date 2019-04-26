package pl.test.notes;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;


public class SingleNote extends Container implements ActionListener, FocusListener	{
	private JCheckBox chBox;
	private TextArea ta;
	
	public SingleNote(String dateStart, String lastChangeDate, String noteId, String text)	{
		super();
			
		this.setLayout(new GridLayout(1,3));
		
		JLabel messLab = new JLabel(dateStart);
		JLabel messLab2 = new JLabel(lastChangeDate);

		chBox = new JCheckBox("Zamknij");
		chBox.setName(noteId);
		formatuj(messLab);
		formatuj(messLab2);
		formatuj(chBox);
		
		//messLab.setFont(new Font("sansserif", Font.PLAIN, 12));
		//messLab2.setFont(new Font("sansserif", Font.PLAIN, 12));
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(5, 1));
		leftPanel.add(messLab);
		leftPanel.add(messLab2);
		leftPanel.add(chBox);
		
		//chBox.setName(message);
		chBox.addActionListener(this);
		
		//chBox.setActionCommand(message);
		
		
		ta = new NoteArea(5, text);
		ta.addFocusListener(this);
		ta.setName(noteId);
		
		JScrollPane sp = new JScrollPane(ta);
		Border border ;

		border = BorderFactory.createLineBorder(Color.black);
		
		JPanel panel = new JPanel();
		
		panel.add(leftPanel);
		
		panel.add(sp);
		
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
		ta2.setBackground(Color.white);
		ta2.addFocusListener(this);

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
		
	}


}
