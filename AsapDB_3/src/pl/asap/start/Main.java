package pl.asap.start;

import org.apache.log4j.PropertyConfigurator;

import pl.asap.DB.DBConnect;
import pl.asap.logic.EkranGlowny;

public class Main {

	public static void main(String[] args) {
		
		String log4jConfPath = "D:\\git\\mk-Asap-DB\\AsapDB_3\\log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		DBConnect dbConnect = new DBConnect();
		new EkranGlowny(dbConnect);

	}

}
