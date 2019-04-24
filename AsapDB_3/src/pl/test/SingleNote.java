package pl.test;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;


public class SingleNote extends Container implements ActionListener	{
	private JCheckBox chBox;
	
	public SingleNote(String message)	{
		super();
		
		this.setLayout(new GridLayout(1,3));
		JLabel messLab = new JLabel(message);
		JLabel messLab2 = new JLabel(message+" helloł");
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
		
		chBox.addActionListener(this);
		chBox.setActionCommand(message);
		
		
		JTextArea ta = new JTextArea(5,30);
		JScrollPane sp = new JScrollPane(ta);
		Border border ;

		border = BorderFactory.createLineBorder(Color.black);
		
		JPanel panel = new JPanel();
		
		panel.add(leftPanel);
		
		panel.add(sp);
		
		this.add(panel);
		
	}
	public void formatuj (JComponent c)	{
		c.setFont(new Font("sansserif", Font.PLAIN, 12));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String a = "";
		if(chBox.isSelected()) {
			a = " zaznaczone";
		}
		else {
			a = " niezaznaczone"; 
		}
		System.out.println(e.getActionCommand()+a);
		
	}


}
