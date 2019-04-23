package pl.asap.DB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DBConnect {   
	static JFrame frame;
	static JLabel lab;
	String defaultPath;
	Process proc;

public DBConnect()        {
	
	defaultPath = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
	
	if (processCheck("mysqld_usbwv8.exe")==false) {
		try {
			proc = Runtime.getRuntime().exec(defaultPath + "\\000_rough\\mysql\\bin\\mysqld_usbwv8.exe");
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
	}
	
	
	for (int i = 0; i < 4; i++) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		if (processCheck("mysqld_usbwv8.exe")) {
			
			String jdbcUrl = "jdbc:mysql://localhost:3307/portableu?useSSL=FALSE&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String user = "root";
			String password = "usbw";

			try {
				DriverManager.getConnection(jdbcUrl, user, password);
				System.out.println("połączono");
				break;
			} catch (SQLException exc) {
				System.out.println("Nieudane połączenie z " + jdbcUrl);
			}
		} 
	}
}

	public Process getProcess()	{
		return proc;
	}
	public boolean processCheck(String processName)	{
		String findProcess = processName;
		String filenameFilter = "/nh /fi \"Imagename eq "+findProcess+"\"";
		String tasksCmd = System.getenv("windir") +"/system32/tasklist.exe "+filenameFilter;

		Process p = null;
		try {
			p = Runtime.getRuntime().exec(tasksCmd);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));

		ArrayList<String> procs = new ArrayList<String>();
		String line = null;
		try {
			while ((line = input.readLine()) != null) 
			    procs.add(line);
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		try {
			input.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		Boolean processFound = procs.stream().filter(row -> row.indexOf(findProcess) > -1).count() > 0;
		
		System.out.println(processFound);
		
		return processFound;
	}
	
	
}
