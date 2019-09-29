package pl.asap.transactions.lista;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import pl.asap.entity.Lista;
import pl.asap.transactions.TransBlank;

public class ReadTrans extends TransBlank{
	
	public Object[][] dane;
	private Object[] ids;
	public int rowNr;
	
	List<Lista> result;

	public ReadTrans(Object bean) {
		super(bean);
		
		session.beginTransaction();
		
		String select = "from Lista";
				
		Query query = session.createQuery(select);
		result = query.getResultList();
		
		session.getTransaction().commit();
		factory.close();

		int j;
		if (result.isEmpty()) {
			j = 0;
		}
		else {
			j = result.get(0).getLength()-1;
		}
		int i = result.size();	

		
		dane = new Object[i][j]; 
		ids = new Object[i];	
		int l;
		int k = 0;
		for(Lista values: result) { 
			l = 0;
			Object[] obj = values.getRow();
			for (Object el: obj)	{ 
				if (l==0 )	{
					ids[k]= el;					
				}
				if (l>0)	{
				if (el==null)	{
					dane[k][l-1]=" ";
				}
				else {
					dane[k][l-1] = el;
				}

				}
				l++;
			}
			k++;
		}

		
		for(int m = 0; m<=i-1; m++)	{
			for (int n =0; n<=j-1; n++)	{
				if (n==0)	{	
				}
			}
		}	
	}
	public Object[][] getMatrix()	{
		return dane;
	}
	public List<Lista> getResult()	{
		return result;
	}
	public int rowNr()	{
		return dane[0].length;
	}
	public Object[] getIDs()	{
		return ids;
	}
}
