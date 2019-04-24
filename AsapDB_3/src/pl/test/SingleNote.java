package pl.test;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;


public class SingleNote extends Container implements ActionListener, FocusListener	{
	private JCheckBox chBox;
	private JTextArea ta;
	
	public SingleNote(String dateStart, String lastChangeDate, String noteId)	{
		super();
		
		this.setLayout(new GridLayout(1,3));
		JLabel messLab = new JLabel(dateStart);
		JLabel messLab2 = new JLabel(lastChangeDate);
		chBox = new JCheckBox("Zamknij");
		formatuj(messLab);
		formatuj(messLab2);
		formatuj(chBox);
		
		messLab.setFont(new Font("sansserif", Font.PLAIN, 12));
		messLab2.setFont(new Font("sansserif", Font.PLAIN, 12));
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(4, 1));
		leftPanel.add(messLab);
		leftPanel.add(messLab2);
		leftPanel.add(chBox);
		
		//chBox.setName(message);
		chBox.addActionListener(this);
		
		//chBox.setActionCommand(message);
		
		
		ta = new JTextArea(5,30);
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
	private void deactivate(JTextArea ta2)	{
		//ta.setContentType("text/html");
		ta2.setEditable(false);
		ta2.setBackground(null);
		ta2.setBorder(null);
	}
	private void activate(JTextArea ta2)	{
		ta2.setEditable(true);
		ta2.setBackground(Color.white);
		Border blackline = BorderFactory.createLineBorder(Color.black);
		ta2.setBorder(blackline);
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
		System.out.println(e.getActionCommand()+a);
		
	}
	@Override
	public void focusGained(FocusEvent arg0) {
		
		
	}
	@Override
	public void focusLost(FocusEvent ev) {
		System.out.println(ta.getName() +" focusLostAction " + ta.getText());
		
	}


}
