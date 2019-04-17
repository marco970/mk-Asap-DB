package theToDo;

import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;

public class MainToDo extends JFrame {
	
	public MainToDo()	{
		
		String[] zawarto = {"jeden", "dwa", "trzy"};
		

        
        setSize(400,400);  
        setLayout(new GridLayout(2, 1));  
        add(new ToDoItem("Hej Ty!"));  
        setVisible(true);  
		
	}

	public static void main(String[] args) {
		new MainToDo();

	}

}
