package pl.test;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class FormContent extends JPanel implements FormCont {
	
	
	private JPanel contentPane;
	
	public FormContent(String message)	{
		super();
		add(new JLabel("message 3"));
		contentPane = new JPanel(); 
		contentPane.setLayout(new GridLayout(1,1));
		contentPane.add(new JLabel(message));
	}

	@Override
	public JPanel getContent() {
		return contentPane;
	}

}
