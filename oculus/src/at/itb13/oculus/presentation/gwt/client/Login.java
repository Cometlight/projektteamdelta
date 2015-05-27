package at.itb13.oculus.presentation.gwt.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
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
		 
		    Button submit = new Button("Submit");
		    submit.addClickHandler(new ClickHandler() {
		        public void onClick(ClickEvent event) {
		            FlowControl.go(new AppointmentOverview());             
		        }           
		    });
		    panel.add(submit);
		 
		 initWidget(panel);
	}
}
