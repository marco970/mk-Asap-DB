package pl.asap.start;

import org.apache.log4j.PropertyConfigurator;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



import pl.asap.DB.DBConnect;
import pl.asap.entity.Config;
import pl.asap.logic.EkranGlowny;

public class Main {
	
	

	public static void main(String[] args) {
		
		String log4jConfPath = "D:\\GitRepo\\mk-Asap-DB\\AsapDB_3\\log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		
		DBConnect dbConnect = new DBConnect();
		
		Config config = new Config();
		String hibernateConf = config.getHibernateXML();
		Configuration conf = new Configuration();
		conf.configure(hibernateConf);
		
		config.setAnnotatedClass(conf);
		SessionFactory factory = conf.buildSessionFactory();
		
		new EkranGlowny(dbConnect, factory);

	}

}
