package at.itb13.oculus.presentation.gwt.client.appointmentOverview.view;

import java.util.ArrayList;
import java.util.List;

import at.itb13.oculus.presentation.gwt.client.Index;
import at.itb13.oculus.presentation.gwt.client.appointmentChoice.view.AppointmentChoice;
import at.itb13.oculus.presentation.gwt.client.appointmentOverview.rpc.AppointmentOverviewService;
import at.itb13.oculus.presentation.gwt.client.appointmentOverview.rpc.AppointmentOverviewServiceAsync;
import at.itb13.oculus.presentation.gwt.client.appointmentRequestForm.view.AppointmentRequestForm;
import at.itb13.oculus.presentation.gwt.client.login.view.Login;
import at.itb13.oculus.presentation.gwt.shared.CalendarEvent;
import at.itb13.oculus.presentation.gwt.shared.Patient;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

public class AppointmentOverview extends Composite {

	private int _calEventId;
	private static AppointmentOverviewUiBinder uiBinder = GWT
			.create(AppointmentOverviewUiBinder.class);
	final AppointmentOverviewServiceAsync appointmentOverviewAsyncService = GWT
			.create(AppointmentOverviewService.class);
	private Patient _patient;

	@UiTemplate("AppointmentOverview.ui.xml")
	interface AppointmentOverviewUiBinder extends
			UiBinder<Widget, AppointmentOverview> {
	}

	@UiField
	Label nameLabel;
	@UiField
	Label sinLabel;
	@UiField
	Label doctorLabel;
	// @UiField
	// Label datecell;
	// @UiField
	// Label doctorcell;
	// @UiField
	// Label typecell;
	// @UiField
	// Label reasoncell;
	@UiField
	CellTable<CalendarEvent> existingAppointmentTable;
	@UiField
	VerticalPanel tablePanel;

	@UiField
	Button logoutButton;
	@UiField
	Button deleteButton;
	@UiField
	Button newAppointmentButton;
	@UiField
	Button appointmentChoiceButton;
	
	List<CalendarEvent> _list = null;

	public AppointmentOverview(Patient patient) {
		initWidget(uiBinder.createAndBindUi(this));
		_patient = patient;

		nameLabel.setText(_patient.getName());
		sinLabel.setText(_patient.getSin());
		doctorLabel.setText(_patient.getDoctor());
		

		AsyncCallback<CalendarEvent> callbackAppointment = new AsyncCallback<CalendarEvent>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("AppointmentOverview - Failed to connect to server. Please try again in a few minutes, or contact the system administrator.");
			}

			@Override
			public void onSuccess(CalendarEvent cal) {
				_calEventId = cal.getId();

				existingAppointmentTable = new CellTable<>();

				TextColumn<CalendarEvent> DateColumn = new TextColumn<CalendarEvent>() {
					@Override
					public String getValue(CalendarEvent cal) {
						return cal.getDate();
					}
				};

				TextColumn<CalendarEvent> DoctorColumn = new TextColumn<CalendarEvent>() {
					@Override
					public String getValue(CalendarEvent cal) {
						return cal.getDoctor();
					}
				};
				TextColumn<CalendarEvent> TypeColumn = new TextColumn<CalendarEvent>() {
					@Override
					public String getValue(CalendarEvent cal) {
						return cal.getType();
					}
				};
				TextColumn<CalendarEvent> ReasonColumn = new TextColumn<CalendarEvent>() {
					@Override
					public String getValue(CalendarEvent cal) {
						return cal.getReason();
					}
				};
				existingAppointmentTable.addColumn(DateColumn, "Date");
			
				existingAppointmentTable.addColumn(DoctorColumn,
						"Doctor / Orthoptis");
				existingAppointmentTable.addColumn(TypeColumn,
						"Appointment Type");
				existingAppointmentTable.addColumn(ReasonColumn, "Reason");
				// Create a data provider.
				ListDataProvider<CalendarEvent> dataProvider = new ListDataProvider<CalendarEvent>();

				// Connect the table to the data provider.
				dataProvider.addDataDisplay(existingAppointmentTable);

				// Add the data to the data provider, which automatically pushes
				// it to the
				// widget.
				_list = dataProvider.getList();

				_list.add(cal);
				tablePanel.add(existingAppointmentTable);

				// datecell.setText(cal.getDate());
				// doctorcell.setText(cal.getDoctor());
				// typecell.setText(cal.getType());
				// reasoncell.setText(cal.getReason());
				newAppointmentButton.setEnabled(false);
			}

		};

		appointmentOverviewAsyncService.getPatientAppointment(_patient,
				callbackAppointment);

	}

	

	@UiHandler("newAppointmentButton")
	void onClicknewAppointmentButton(ClickEvent event) {

		
		Index.forward(new AppointmentRequestForm(_patient));
		
	}

	@UiHandler("deleteButton")
	void onClickDeleteButton(ClickEvent event) {
		// delete CalendarEvent

		AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("DELETE- Failed to connect to server. Please try again in a few minutes, or contact the system administrator.");
			}

			@Override
			public void onSuccess(Boolean b) {
				Window.alert("Event has been deleted");
				if (_list!=null){
					_list.clear();
				}
				
			}
		};

		appointmentOverviewAsyncService
				.deleteAppointment(_calEventId, callback);

		deleteButton.setEnabled(false);
		newAppointmentButton.setEnabled(true);
	}

	@UiHandler("appointmentChoiceButton")
	void onClickAppointmentChoiceButton(ClickEvent event) {
		CalendarEvent event1 = new CalendarEvent(25, "25.06.2015",
				"Dr.Tavolato", "First visit", "some reason");
		CalendarEvent event2 = new CalendarEvent(26, "25.06.2015", "Dr Ruben",
				"First visit", "some reason");
		CalendarEvent event3 = new CalendarEvent(27, "25.06.2015", "Dr Other",
				"First visit", "some reason");
		List<CalendarEvent> events = new ArrayList<>();
		events.add(event1);
		events.add(event2);
		events.add(event3);
		Index.forward(new AppointmentChoice(_patient, events));

	}
	
	@UiHandler("logoutButton")
	void onClickLogoutButton(ClickEvent event){
		Index.forward(new Login());
	}
}
