package at.itb13.oculus.presentation.gwt.client.appointmentOverview.view;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface AppointmentOverviewResources extends ClientBundle {
	public interface Style extends CssResource {
		String labelTest();

		String box();

		String background();
	}

	@Source("AppointmentOverview.css")
	Style style();
}
