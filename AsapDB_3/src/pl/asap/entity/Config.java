
package pl.asap.entity;

import org.hibernate.cfg.Configuration;

/**
 *
 * @author marcin.kuciak
 */
public class Config {
    
    private String hibernateXML = "hibernate.cfg.xml";
    
    public Config()	{
    	
    }


	public String getHibernateXML() {
		return hibernateXML;
	}

	public void setHibernateXML(String hibernateXML) {
		this.hibernateXML = hibernateXML;
	}


	public void setAnnotatedClass(Configuration conf) {
		conf.addAnnotatedClass(Lista.class);
		conf.addAnnotatedClass(Notes.class); 
		conf.addAnnotatedClass(TimeSheetEntity.class);
		
	}

    
}
