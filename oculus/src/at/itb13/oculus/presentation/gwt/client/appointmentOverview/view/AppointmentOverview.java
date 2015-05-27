package at.itb13.oculus.presentation.gwt.client.appointmentOverview.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class AppointmentOverview extends Composite {

	private static AppointmentOverviewUiBinder uiBinder = GWT.create(AppointmentOverviewUiBinder.class);

	@UiTemplate("AppointmentOverview.ui.xml")
	interface AppointmentOverviewUiBinder extends UiBinder<Widget, AppointmentOverview> {
	}

	@UiField(provided = true)
	final AppointmentOverviewResources res;
	
	public AppointmentOverview() {
		this.res = GWT.create(AppointmentOverviewResources.class);
		res.style().ensureInjected();
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiField
	Label label007;
	
	@UiField
	TextBox myTextBox;
	
	@UiHandler("myTextBox")
	void handleInputChange(ValueChangeEvent<String> event) {
		if(event.getValue().length() < 3) {
			label007.setVisible(true);
			label007.setText("Text needs to be >= 3!!!");
		} else {
			label007.setText(":)");
			label007.setVisible(false);
		}
	}

}
