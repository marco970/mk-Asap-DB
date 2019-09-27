package pl.asap.DB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.asap.entity.Lista;
import pl.asap.transactions.lista.SaveTrans;

public class FileDbMigrator {
	
	
	
	private String current = "Current4.txt";
	private static Object[][] adane = null;
	private Lista lista;
	
	private static String[] nazwyKolumn = {"ZZ", "PZ", "WP", "DK", "Status", 
			"Przemiot Zakupu", "Dostawca", "Nazwa", "Tryb postępowania", "Spółka", 
			"dsZZ", "dsPZ", "dsWP", "dsDK" };
	
	public FileDbMigrator() {
		
		try {
			adane=readFile(current);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		DBConnect dbConnect = new DBConnect();
		lista = new Lista();
//		SaveTrans st = new SaveTrans(lista);
		
//		i - wiersze
		int row = adane.length;
//		j - kolumny
		int col = nazwyKolumn.length;
		
		for(int i = 0; i < row; i++)	{
			SaveTrans st = new SaveTrans(lista);
			st.saveRow(adane[i]);
			
			for(int j = 0; j < col; j++)	{
				System.out.print(adane[i][j]+" | ");
			}
			System.out.println();
		}
		
	}
	
	
	public Object[][] readFile(String filePath) throws IOException {
		  FileReader fileReader = new FileReader(filePath);
		  BufferedReader bufferedReader = new BufferedReader(fileReader);
		  
		  String textLine = bufferedReader.readLine();
		  ArrayList<String> linia = new ArrayList<String>();	  
		  do {
		    linia.add(textLine);
		    textLine = bufferedReader.readLine();	    
		  } while(textLine != null);

		  bufferedReader.close();
		  
//		  for(String el:linia)	{
//			  System.out.println(el);
//		  }
//		  System.out.println();
		  
		  int i =0;
		  Object[][] sdane = new String[linia.size()][nazwyKolumn.length];
		  for (String el: linia)	{
			  	String[] row=el.split(";");
			  	for (int j=0; j<=row.length-1; j++)	{	  		
			  		sdane[i][j]=row[j];
			  		if (sdane[i][j]==null) sdane[i][j]="";
			  	}
			  i++;
		  }
		  int n=1;
		  List<Object[]> wiersz = new ArrayList<Object[]>();
		  wiersz.add(sdane[n]);
		  return sdane;
		}
	
	
	public static void main(String[] args) {
		new FileDbMigrator();
		

	}

}