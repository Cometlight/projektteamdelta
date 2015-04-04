/**
 * 
 */

/**
 * @author Andrew
 *
 */

import org.apache.logging.log4j.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import at.itb13.oculus.domain.Eventtype;

public class TestClassForHibernateAndLogging {
	
	static final Logger logger = LogManager.getLogger(TestClassForHibernateAndLogging.class.getName());
	
	public static void main(String[] args) {
		logger.trace("Starting testclass");
		Session session = null;

		try {
			
			Configuration config = new Configuration();
			config.configure("hibernate.cfg.xml");
			StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(config.getProperties()).build();
			SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
			// SessionFactory should be initialized only once at startup! -- Daniel
			
			session = sessionFactory.openSession();
			session.beginTransaction();

			Eventtype eventType = new Eventtype();
			eventType.setCalendarevents(null);
			eventType.setDescription("Test by Andrew");
			eventType.setEstimatedTime(0);
			eventType.setEventTypeId(1000);
			eventType.setEventTypeName("eventtypename");

			session.save(eventType);
			session.getTransaction().commit();

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
			 logger.error("Ohh!Failed!");
		} finally {
			if (session != null) {
				session.close();
			}
		}
		 logger.trace("Exiting Log4j Example.");
	}
}
