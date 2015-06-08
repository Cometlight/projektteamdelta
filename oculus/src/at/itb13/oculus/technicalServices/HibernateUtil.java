package at.itb13.oculus.technicalServices;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import at.itb13.oculus.technicalServices.exceptions.NoDatabaseConnectionException;

/**
 * Singleton for org.hibernate.SessionFactory, which should be instantiated only once as it's expensive doing so.
 * An NoDatabaseConnectionException is thrown, if it's not possible to initialize everything that's needed to work with the database.
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
				DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
			} catch (SQLException e1) {
				_logger.fatal("Failed to register MySQL Driver", e1);
			}
			
			Configuration config = new Configuration();
			config.configure("hibernate.cfg.xml");
			
			/**
			 * Unfortunately, config.buildSessionFactory(Configuration) only some Exceptions. Some other exceptions 
			 * are catched inside the function and printed, but not re-thrown. Thus, this workaround is needed in order
			 * to check if it's possible to connect to the database.
			 */
			try {
				checkConnection(config);
			} catch (NoDatabaseConnectionException e) {
				_logger.fatal("Failed to connect to database", e);
				throw e;
			}
			
			try {
				StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(config.getProperties()).build();
				_sessionFactory = config.buildSessionFactory(serviceRegistry);
				_logger.info("_sessionFactory has been initialized successfully.");
			} catch (Throwable t) {
				_logger.fatal("Failed to initialize SessionFactory", t);
				throw new NoDatabaseConnectionException(t);
			}
		}
	}
	
	/**
	 * This method is needed to test if it's possible to connect to the database specified in config
	 * No boolean is returned because we don't want to lose the information of the catched exception.
	 * 
	 * @param config The configuration to be tested
	 * @throws NoDatabaseConnectionException Thrown if it was not possible to connect to the database
	 */
	private static void checkConnection(Configuration config) throws NoDatabaseConnectionException {
		try {
			Properties p = config.getProperties();

			String url = p.getProperty("hibernate.connection.url");
			String user = p.getProperty("hibernate.connection.username");
			String password = p.getProperty("hibernate.connection.password");

			DriverManager.getConnection(url, user, password).close();
		} catch(Throwable t) {
			throw new NoDatabaseConnectionException(t);
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
