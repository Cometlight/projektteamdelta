package at.itb13.oculus.technicalServices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import at.itb13.oculus.application.exceptions.InvalidInputException;

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
		} catch (InvalidInputException e) {
			_logger.error(e);
			_sessionFactory = null;
		}
	}
	
	public static void init() throws InvalidInputException {
		if(_sessionFactory == null) {
			try {
				Configuration config = new Configuration();
				config.configure("hibernate.cfg.xml");
				StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(config.getProperties()).build();
				_sessionFactory = config.buildSessionFactory(serviceRegistry);
				_logger.info("_sessionFactory has been initialized.");
			} catch (Throwable t) {
				_logger.fatal(t);
				throw new InvalidInputException();
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
