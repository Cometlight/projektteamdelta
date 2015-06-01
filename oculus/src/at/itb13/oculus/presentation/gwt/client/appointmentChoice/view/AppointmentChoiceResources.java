package at.itb13.oculus.presentation.gwt.client.appointmentChoice.view;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface AppointmentChoiceResources extends ClientBundle {
	public interface Style extends CssResource {
		String labelTest();

		String box();

		String background();
	}

	@Source("AppointmentChoice.css")
	Style style();
}
