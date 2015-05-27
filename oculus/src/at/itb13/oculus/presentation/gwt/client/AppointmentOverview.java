package at.itb13.oculus.presentation.gwt.client;

import javafx.scene.control.Button;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 27.05.2015
 */
public class AppointmentOverview extends Composite {

	public AppointmentOverview(){
		Label header = new Label();
		header.setText("Your next Appointment");
		Button delete = new Button();
		delete.setText("delete");
	}
}
