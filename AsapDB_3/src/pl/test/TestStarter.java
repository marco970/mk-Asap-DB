package pl.test;

import pl.asap.DB.DBConnect;
import pl.asap.entity.Lista;
import pl.asap.transactions.ReadTrans;
import pl.asap.transactions.SaveTrans;
import pl.asap.transactions.UpdateTrans;

public class TestStarter {

	public static void main(String[] args) {
		
		new DBConnect();
		Lista lista = new Lista();
		
            //new SaveTrans(lista, "ZZ", "ZZ/PLI0006565");
            //UpdateTrans updateTrans = new UpdateTrans(lista, "WP", "WP/PLK0004328", 8);
			new ReadTrans(lista);
	}

}
