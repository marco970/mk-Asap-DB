package pl.asap.start;

import pl.asap.DB.DBConnect;
import pl.asap.logic.EkranGlowny;

public class Main {

	public static void main(String[] args) {
		
		DBConnect dbConnect = new DBConnect();
		new EkranGlowny(dbConnect);

	}

}
