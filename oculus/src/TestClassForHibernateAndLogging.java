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

	private static SessionFactory _sessionFactory = null;

	static final Logger logger = LogManager
			.getLogger(TestClassForHibernateAndLogging.class.getName());

	public static void main(String[] args) {
		logger.trace("Entering Log4j Example.");
		int x = 5;
		if (x == 5) {
			logger.error("Ohh!Failed!");
		}

		Session session = null;

		try {
			session = _sessionFactory.openSession();
			session.beginTransaction();

			// TODO ENTRY
			
			Eventtype event = new Eventtype();
			event.setDescription("test");
			event.setEstimatedTime(0);
//			event.set(0);
//			
//			session.save();
			session.getTransaction().commit();

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			if (session != null) {
				session.close();
			}
		}

		logger.trace("Exiting Log4j Example.");
	}

	public void init() {
		initSessionFactory();
	}

	private void initSessionFactory() {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties()).build();
		_sessionFactory = config.buildSessionFactory(serviceRegistry);
	}
}
