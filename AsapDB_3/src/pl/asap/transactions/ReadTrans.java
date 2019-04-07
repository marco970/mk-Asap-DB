package pl.asap.transactions;

import java.util.List;

import org.hibernate.query.Query;

import pl.asap.entity.Lista;

public class ReadTrans extends TransBlank{
	
	public Object[][] dane;
	private Object[] ids;
	public int rowNr;

	public ReadTrans(Object bean) {
		super(bean);
		
		session.beginTransaction();
		
		String select = "from Lista";
				
		Query query = session.createQuery(select);
		List<Lista> result = query.getResultList();
		
		session.getTransaction().commit();
		factory.close();
		//System.out.println("is relult empty? "+ result.isEmpty());
		int j;
		if (result.isEmpty()) {
			j = 0;
		}
		else {
			j = result.get(0).getLength()-1;	//kolumny -> wiersze
		}
		int i = result.size();	//wiersze -> kolumny
		//System.out.println("kolumny -> wiersze(ile result na liście) i: "+i);
		//System.out.println("wiersze -> kolumny(wielkość jednego result) j: "+j);
		
		dane = new Object[i][j]; //7,14
		ids = new Object[i];	//14
		int l;
		int k = 0;
		for(Lista values: result) { //resultów jest 7 (wiersze)
			l = 0;
			Object[] obj = values.getRow();
			//System.out.println("długość resultu "+k+" wynosi: "+obj.length);
			for (Object el: obj)	{ //iteracja po jednym resulcie (kolumny)
				if (l==0 )	{
					//System.out.println(k+"|"+l); 
					ids[k]= el;					
					//System.out.println(ids[k]+"*("+k+"|"+l+")");
				}
				if (l>0)	{
				//System.out.println(k+"|"+l);
				if (el==null)	{
					dane[k][l-1]=" ";
				}
				else {
					dane[k][l-1] = el;
				}
				
				//System.out.print(el+"-("+k+"|"+l+") ");
				}
				l++;
			}
			k++;
			//System.out.println("///");
		}
		//System.out.println("kolumny m: "+dane.length);
		//System.out.println("wiersze n: "+dane[0].length);
		
		for(int m = 0; m<=i-1; m++)	{
			for (int n =0; n<=j-1; n++)	{
				if (n==0)	{	
				//System.out.print("ids-"+ids[m]+"("+m+"|"+n+")  ");
				}
				//System.out.print(dane[m][n]+"-("+m+"|"+n+") ");
			}
			//System.out.println();
		}	
	}
	public Object[][] getMatrix()	{
		return dane;
	}
	public int rowNr()	{
		return dane[0].length;
	}
	public Object[] getIDs()	{
		return ids;
	}
	
	/*public int getID(int rowNr)	{
		int id = 0;
		return id;
	}
	public int getID(String r)	{
		int id = 0;
		return id;
	} */
	

}
