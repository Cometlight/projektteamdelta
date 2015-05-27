package at.itb13.oculus.presentation.gwt.client;


import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 27.05.2015
 */
public class AppointmentOverview extends Composite {

	public AppointmentOverview() {
		VerticalPanel panel = new VerticalPanel();
		Label header = new Label();
		header.setText("Your next Appointment");
		Button delete = new Button();
		delete.setText("delete");
		panel.add(header);
		panel.add(delete);
		initWidget(panel);
	}
}
