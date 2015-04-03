/**
 * 
 */

/**
 * @author Andrew
 *
 */

import org.apache.logging.log4j.*;

public class TestClassForHibernateAndLogging {
	
	static final Logger logger = LogManager.getLogger(TestClassForHibernateAndLogging.class.getName());
	
	public static void main(String[] args) {
		logger.trace("Entering Log4j Example.");
		int x = 5;
		if (x == 5) {
			logger.error("Ohh!Failed!");
		}
		logger.trace("Exiting Log4j Example.");
	}
}
