package pl.test;

import java.awt.*;


import javax.swing.*;
import javax.swing.border.Border;


public class SingleNote extends Container
{
	public SingleNote(String message)	{
		super();
		this.setLayout(new GridLayout(1,3));
		JLabel messLab = new JLabel(message);
		JLabel messLab2 = new JLabel(message+"helloł");
		messLab.setFont(new Font("sansserif", Font.PLAIN, 12));
		messLab2.setFont(new Font("sansserif", Font.PLAIN, 12));
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(4, 1));
		leftPanel.add(messLab);
		leftPanel.add(messLab2);
		
		
		JTextArea ta = new JTextArea(5,30);
		JScrollPane sp = new JScrollPane(ta);
		Border border ;

		border = BorderFactory.createLineBorder(Color.black);
		
		JPanel panel = new JPanel();
		//panel.setBorder(border);
		panel.add(leftPanel);
		//panel.add(messLab2);
		panel.add(sp);
		
		this.add(panel);
		
		
		//this.add(sp);
		
		
		
	}
	

}
