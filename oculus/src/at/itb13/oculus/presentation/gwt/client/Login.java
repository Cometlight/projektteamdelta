package at.itb13.oculus.presentation.gwt.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date May 27, 2015
 */
public class Login extends Composite {
	private TextBox textBox = new TextBox();
	
	public Login() {
		 VerticalPanel panel = new VerticalPanel();
		 panel.add(textBox);
		 
		 initWidget(panel);
	}
}
