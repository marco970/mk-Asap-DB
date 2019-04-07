
package pl.asap.entity;

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

    
}
