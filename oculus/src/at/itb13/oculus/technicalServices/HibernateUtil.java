package at.itb13.oculus.technicalServices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import at.itb13.oculus.technicalServices.exceptions.NoDatabaseConnectionException;

/**
 * Singleton for org.hibernate.SessionFactory, which should be instantiated only once as it's expensive doing so.
 * 
 * @author Daniel Scheffknecht
 * @date 04.04.2015
 */
public class HibernateUtil {
	private static SessionFactory _sessionFactory;
	private static final Logger _logger = LogManager.getLogger(HibernateUtil.class.getName());
	
	private HibernateUtil() { }
	
	static {
		try {
			init();
		} catch (NoDatabaseConnectionException e) {
			_sessionFactory = null;
		}
	}
	
	public static void init() throws NoDatabaseConnectionException {
		if(_sessionFactory == null) {
			try {
				System.out.println("1");
				Configuration config = new Configuration();
				System.out.println("2");
				config.configure("hibernate.cfg.xml");
				System.out.println("3");
				StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(config.getProperties()).build();
				System.out.println("4");
				_sessionFactory = config.buildSessionFactory(serviceRegistry);
				System.out.println("5");
				_logger.info("_sessionFactory has been initialized.");
			} catch (Throwable t) {
				_logger.fatal("Failed to initialize SessionFactory", t);
				throw new NoDatabaseConnectionException(t);
			}
		}
	}
	
	/**
	 * 
	 * @return always returns the same SessionFactory (Singleton)
	 */
	public static SessionFactory getSessionFactory() {
		return _sessionFactory;
	}
}
