package pl.asap.transactions;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.asap.entity.Config;
import pl.asap.entity.Lista;

public abstract class TransBlank {
	
		final SessionFactory factory;
		final Session session;
		
		final Object bean;
		final Object[] array;
	
	public TransBlank(Object bean)		{
		Config config = new Config();
		String hibernateConf = config.getHibernateXML();

		Configuration conf = new Configuration();
		conf.configure(hibernateConf);
		this.bean = bean;
		array = ((Lista) bean).getArray();
		
		Class<? extends Object> beanClass = bean.getClass();
		
		conf.addAnnotatedClass(beanClass);
		factory = conf.buildSessionFactory();
		session = factory.getCurrentSession();

	}

}
