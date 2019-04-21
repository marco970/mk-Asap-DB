package pl.asap.theToDo;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

@SuppressWarnings("serial")
public class ToDoItem extends JPanel {
	
	
	private String content;
	JTextArea notka;
	
	
	public ToDoItem(String content)	{
		
		setLayout(new GridLayout(1, 2));
		JTextArea ta = new JTextArea(3, 1);
		ta.setText(content);
		JPanel subPanel = new JPanel();
		add(ta);
		add(subPanel);
		
		JLabel dataUtwLabel = new JLabel("example-1");
		JTextField dataUtwPole = new JTextField(10);
		subPanel.setLayout(new GridLayout(4, 1));
		subPanel.add(dataUtwLabel);
		subPanel.add(dataUtwPole);
		
	}

	
	

}
