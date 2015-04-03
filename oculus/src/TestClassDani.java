import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.domain.Eventtype;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 03.04.2015
 */
public class TestClassDani {
	static final Logger logger = LogManager.getLogger(TestClassDani.class.getName());

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Eventtype eventtype = new Eventtype();
		eventtype.setEventTypeName("Doomsday");
		eventtype.setEstimatedTime(30);
		eventtype.setDescription("Hi there! Here you could write down a useful description. Really!");
	}

}
