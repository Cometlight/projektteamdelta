import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 03.04.2015
 */
public class TestClass {
	static final Logger logger = LogManager.getLogger(TestClass.class.getName());

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		logger.trace("Entering Example");
		if (true) {
			logger.error("Oh noes! ERROR!!!");
		}
		logger.trace("exist example");
	}

}
