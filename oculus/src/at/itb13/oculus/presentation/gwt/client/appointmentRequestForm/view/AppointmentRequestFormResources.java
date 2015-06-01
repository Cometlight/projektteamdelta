package at.itb13.oculus.presentation.gwt.client.appointmentRequestForm.view;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface AppointmentRequestFormResources extends ClientBundle {
	public interface Style extends CssResource {
		String labelTest();

		String box();

		String background();
	}

	@Source("AppointmentRequestForm.css")
	Style style();
}
