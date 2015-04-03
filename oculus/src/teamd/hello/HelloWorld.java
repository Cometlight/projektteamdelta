package teamd.hello;

import java.util.LinkedList;
import java.util.List;

/**
 * TODO: Class to test if Git + Github is working and if Java8 is installed properly.
 * 
 * @author Daniel Scheffknecht
 * @since 28.03.2015
 */
public class HelloWorld {

	/**
	 * @param args command line parameters
	 */
	public static void main(String[] args) {
		System.out.println("Hello World!");
		
		List<String> java8 = new LinkedList<String>();
		java8.add("Java 8 ");
		java8.add("is working ");
		java8.add(":D");
		java8.forEach(System.out::print);
		
		System.out.println("Der Wiener war hier.");
		
		System.out.println("Commit Nummero 2 Hehehehe");
		
		System.out.println("Das Plugin funktioniert gut, ist genehmigt :D");
		
		System.out.println("Caros TEST");
		System.out.println("Florin auch! Glaub schon.");
		System.out.println("Hi there, du Opfer");
		
		
		System.out.println("Ein nächtlicher Push hält dich in Schuss!");
		
		System.out.println("Ein Push am Morgen vertreibt Kummer und Sorgen!");
		
		//TODO Write Java 8 Tests
		// Really?
	}

}
