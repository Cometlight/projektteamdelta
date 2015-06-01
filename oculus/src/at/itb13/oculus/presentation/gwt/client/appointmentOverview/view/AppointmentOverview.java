package at.itb13.oculus.presentation.gwt.client.appointmentOverview.view;

import at.itb13.oculus.presentation.gwt.client.Index;
import at.itb13.oculus.presentation.gwt.client.appointmentOverview.rpc.AppointmentOverviewService;
import at.itb13.oculus.presentation.gwt.client.appointmentOverview.rpc.AppointmentOverviewServiceAsync;
import at.itb13.oculus.presentation.gwt.client.appointmentRequestForm.view.AppointmentRequestForm;
import at.itb13.oculus.presentation.gwt.shared.CalendarEvent;

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
	private String _email;

	@UiTemplate("AppointmentOverview.ui.xml")
	interface AppointmentOverviewUiBinder extends UiBinder<Widget, AppointmentOverview> {
	}

	@UiField(provided = true)
	final AppointmentOverviewResources res;
	
	public AppointmentOverview(String email) {
		this.res = GWT.create(AppointmentOverviewResources.class);
		
		res.style().ensureInjected();
		initWidget(uiBinder.createAndBindUi(this));
		_email = email;
		AsyncCallback<String[]> callbackPatient = new AsyncCallback<String[]>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("AppointmentOverview - Failed to connect to server. Please try again in a few minutes, or contact the system administrator.");
			}

			@Override
			public void onSuccess(String[] result) {
				nameLabel.setText(result[0]);
				sinLabel.setText(result[1]);
				doctorLabel.setText(result[2]);
			}
			
		};
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
		
		appointmentOverviewAsyncService.getPatientData(_email, callbackPatient);
		appointmentOverviewAsyncService.getPatientAppointment(_email, callbackAppointment);
		
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
	
	
//	@UiField
//	TableColElement dateColumn;
//	@UiField
//	TableColElement doctorColumn;
//	@UiField
//	TableColElement typeColumn;
	@UiField
	Button logoutButton;
	@UiField
	Button deleteButton;
	@UiField
	Button newAppointmentButton;
	
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
}
