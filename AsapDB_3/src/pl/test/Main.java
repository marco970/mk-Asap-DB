package pl.test;

import pl.asap.DB.DBConnect;
import pl.asap.entity.Lista;
import pl.asap.logic.DataModel;
import pl.asap.logic.EkranGlowny;
import pl.asap.logic.MainTableModel;
import pl.asap.transactions.ReadTrans;
import pl.asap.transactions.SaveTrans;
import pl.asap.transactions.UpdateTrans;

public class Main {

	public static void main(String[] args) {
		
		//String[] values = {"ZZ/CPO0003498","PZ/0000002345"," "," ","aktywne","przemiot zak","Durex"," "," ","11.02.2019","11.03.2019"," "," "," "};
		//System.out.println(values.length);
		
		DBConnect dbConnect = new DBConnect();
		//Lista lista = new Lista();
		//UpdateTrans ut = new UpdateTrans(lista);
		//ut.upadateCell("PZ", "PZ/0000008767", 10);
		//ut.upadateCell("WP", "WP/PLI0008767", 10);
		//ut.upadateCell("przedmiot_zakupu", "przedmiot", 16);
		//ut.updateRow(values, 16);
		//Object[] pola = lista.getArray();
		//for (Object el: pola)	{
			//System.out.println(" main el "+el);
		//}
		
		//SaveTrans st = new SaveTrans(lista);
		//st.saveRow(values);
		//st.saveField("dsZZ", "07.03.2019");
		
		
		
		//ReadTrans readTrans = new ReadTrans(lista);
		//MainTableModel mtm = new MainTableModel();
		//DataModel dataModel = new DataModel(readTrans);
		EkranGlowny eg = new EkranGlowny(dbConnect);
		//EkranGlowny eg = new EkranGlowny(dataModel);
		
		
	}

}
