/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.asap.entity;

/**
 *
 * @author marcin.kuciak
 */
public class Config {
    
    private String hibernateXML = "hibernate.cfg.xml";
    
    //private Object bean = 
    public Config()	{
    	
    }


	public String getHibernateXML() {
		return hibernateXML;
	}

	public void setHibernateXML(String hibernateXML) {
		this.hibernateXML = hibernateXML;
	}

    
}
