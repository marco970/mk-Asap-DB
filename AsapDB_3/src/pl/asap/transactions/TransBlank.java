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
		//String hibernateConf;
		
	
	public TransBlank(Object bean)		{
		Config config = new Config();
		String hibernateConf = config.getHibernateXML();

		Configuration conf = new Configuration();
		conf.configure(hibernateConf);
		this.bean = bean;
		//System.out.println(" super "+((Lista)bean).getClass());
		array = ((Lista) bean).getArray();
		/*
		for (Object el:array)	{
			//System.out.println(" super el "+el);
		}
		*/
		
		Class<? extends Object> beanClass = bean.getClass();
		
		conf.addAnnotatedClass(beanClass);
		factory = conf.buildSessionFactory();
		session = factory.getCurrentSession();

	}

}
