package at.itb13.oculus.presentation.gwt.client.appointmentOverview.view;

import java.util.ArrayList;
import java.util.List;

import at.itb13.oculus.presentation.gwt.client.Index;
import at.itb13.oculus.presentation.gwt.client.appointmentChoice.view.AppointmentChoice;
import at.itb13.oculus.presentation.gwt.client.appointmentOverview.rpc.AppointmentOverviewService;
import at.itb13.oculus.presentation.gwt.client.appointmentOverview.rpc.AppointmentOverviewServiceAsync;
import at.itb13.oculus.presentation.gwt.client.appointmentRequestForm.view.AppointmentRequestForm;
import at.itb13.oculus.presentation.gwt.shared.CalendarEvent;
import at.itb13.oculus.presentation.gwt.shared.Patient;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class AppointmentOverview extends Composite {

	private int _calEventId;
	private static AppointmentOverviewUiBinder uiBinder = GWT.create(AppointmentOverviewUiBinder.class);
	final AppointmentOverviewServiceAsync appointmentOverviewAsyncService = GWT
			.create(AppointmentOverviewService.class);
	private Patient _patient;

	@UiTemplate("AppointmentOverview.ui.xml")
	interface AppointmentOverviewUiBinder extends UiBinder<Widget, AppointmentOverview> {
	}

	@UiField(provided = true)
	final AppointmentOverviewResources res;
	
	public AppointmentOverview(Patient patient) {
		this.res = GWT.create(AppointmentOverviewResources.class);
		
		res.style().ensureInjected();
		initWidget(uiBinder.createAndBindUi(this));
		_patient = patient;
//		AsyncCallback<String[]> callbackPatient = new AsyncCallback<String[]>() {
//
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert("AppointmentOverview - Failed to connect to server. Please try again in a few minutes, or contact the system administrator.");
//			}

//			@Override
//			public void onSuccess(String[] result) {
				nameLabel.setText(_patient.getName());
				sinLabel.setText(_patient.getSin());
				doctorLabel.setText(_patient.getDoctor());
//			}
//			
//		};
		AsyncCallback<CalendarEvent> callbackAppointment = new AsyncCallback<CalendarEvent>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("AppointmentOverview - Failed to connect to server. Please try again in a few minutes, or contact the system administrator.");
			}

			@Override
			public void onSuccess(CalendarEvent cal) {
				_calEventId = cal.getId();
				datecell.setText(cal.getDate());
				doctorcell.setText(cal.getDoctor());
				typecell.setText(cal.getType());
				reasoncell.setText(cal.getReason());
				newAppointmentButton.setEnabled(false);
			}
			
		};
		
		appointmentOverviewAsyncService.getPatientAppointment(_patient, callbackAppointment);
		
	}
	
	@UiField
	Label nameLabel;
	@UiField
	Label sinLabel;
	@UiField
	Label doctorLabel;
	@UiField
	Label datecell;
	@UiField
	Label doctorcell;
	@UiField
	Label typecell;
	@UiField
	Label reasoncell;
	

	@UiField
	Button logoutButton;
	@UiField
	Button deleteButton;
	@UiField
	Button newAppointmentButton;
	@UiField
	Button appointmentChoiceButton;
	
	@UiHandler("newAppointmentButton")
	void onClicknewAppointmentButton(ClickEvent event) {
		

//			AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
//				@Override
//				public void onFailure(Throwable caught) {
//					Window.alert("Failed to connect to server. Please try again in a few minutes, or contact the system administrator.");
//				}
//
//				@Override
//				public void onSuccess(Boolean loginCredentialsValid) {
//					if (loginCredentialsValid) {
						Index.forward(new AppointmentRequestForm());
//					} else {
//						
//					}
//				}
//				
//			};
	}
	
	@UiHandler("deleteButton")
	void onClickDeleteButton(ClickEvent event){
		//delete CalendarEvent
		
		AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("DELETE- Failed to connect to server. Please try again in a few minutes, or contact the system administrator.");
		}
		
		@Override
		public void onSuccess(Boolean b) {
			Window.alert("Event has been deleted");
			datecell.setText("-");
			doctorcell.setText("-");
			typecell.setText("-");
			reasoncell.setText("-");
//			if (loginCredentialsValid) {
//			//	Index.forward(new AppointmentRequestForm());
//			} else {				
//			}
		}
	};
		
		appointmentOverviewAsyncService.deleteAppointment(_calEventId, callback);
		
		deleteButton.setEnabled(false);
		newAppointmentButton.setEnabled(true);
	}
	@UiHandler("appointmentChoiceButton")
	void onClickAppointmentChoiceButton(ClickEvent event) {
			CalendarEvent event1 = new CalendarEvent(25, "25.06.2015", "Dr.Tavolato", "First visit", "some reason");
			CalendarEvent event2 = new CalendarEvent(26, "25.06.2015", "Dr Ruben", "First visit", "some reason");
			CalendarEvent event3 = new CalendarEvent(27, "25.06.2015", "Dr Other", "First visit", "some reason");
			List<CalendarEvent> events = new ArrayList<>();
			events.add(event1);
			events.add(event2);
			events.add(event3);
			Index.forward(new AppointmentChoice(_patient, events));

	}
}
