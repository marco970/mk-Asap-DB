package pl.asap.transactions;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.asap.entity.Config;
import pl.asap.entity.EntityBase;
import pl.asap.entity.Lista;
import pl.asap.entity.Notes;
import pl.asap.entity.TimeSheetEntity;

public abstract class TransBlank {
	
		protected final SessionFactory factory;
		protected final Session session;
		
		protected final Object bean;
//		final Object[] array;
	
	public TransBlank(Object bean)		{
		Config config = new Config();
		String hibernateConf = config.getHibernateXML();

		Configuration conf = new Configuration();
		conf.configure(hibernateConf);
		this.bean = bean;
//		array = ((EntityBase) bean).getArray();
		
		Class<? extends Object> beanClass = bean.getClass();
		
		config.setAnnotatedClass(conf);
		factory = conf.buildSessionFactory();
		session = factory.getCurrentSession();

	}

}
