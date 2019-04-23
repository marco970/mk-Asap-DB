package pl.test;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShowBaseForm extends JFrame {
	
	//private JFrame frame;
	private FormContent contentPane;
	
	public ShowBaseForm(FormContent panel)	{
		this.contentPane = panel; 
		JPanel pnl = panel.getContent();
		add(pnl);

		add(new JLabel("message 2"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		setVisible(true);
		panel.setVisible(true);
		
	}

	public static void main(String[] args) {
		FormContent panel = new FormContent("message 1");
		new ShowBaseForm(panel);

	}

}
