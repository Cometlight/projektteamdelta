package at.itb13.oculus.presentation.gwt.client.appointmentChoice.view;

import java.util.List;

import at.itb13.oculus.presentation.gwt.client.Index;
import at.itb13.oculus.presentation.gwt.client.appointmentChoice.rpc.AppointmentChoiceService;
import at.itb13.oculus.presentation.gwt.client.appointmentChoice.rpc.AppointmentChoiceServiceAsync;
import at.itb13.oculus.presentation.gwt.client.appointmentOverview.view.AppointmentOverview;
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
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 01.06.2015
 */
public class AppointmentChoice extends Composite{

	private static AppointmentChoiceUiBinder uiBinder = GWT
			.create(AppointmentChoiceUiBinder.class);
	private final AppointmentChoiceServiceAsync appointmentChoiceAsyncService = GWT
			.create(AppointmentChoiceService.class);
	@UiTemplate("AppointmentChoice.ui.xml")
	interface AppointmentChoiceUiBinder extends UiBinder<Widget, AppointmentChoice> {
	}
	
	private Patient _patient;
	@UiField
	Label nameLabel;
	@UiField
	Label sinLabel;
	@UiField
	Label doctorLabel;
	@UiField
	CellTable<CalendarEvent> appointmentTable;
	@UiField
	CellTable<CalendarEvent> chosenTable;
	CalendarEvent _calendarEvent;
	@UiField
	HTMLPanel htmlPanel;
	@UiField
	VerticalPanel tablePanel;
	@UiField
	VerticalPanel chosenPanel;
	@UiField
	Button okButton;
	@UiField
	Button backButton;
	@UiField
	Button logoutButton;
	
	private List<CalendarEvent> _events;
	
	
	
	public AppointmentChoice(Patient patient, List<CalendarEvent> events) {
	
	//old:
//	public AppointmentChoice(Patient patient) {	
		initWidget(uiBinder.createAndBindUi(this));
		_patient = patient;
		_events = events;
		//set patient informations at header
		nameLabel.setText(_patient.getName());
		sinLabel.setText(_patient.getSin());
		doctorLabel.setText(_patient.getDoctor());

		initAppointmentTable();
	
	}

	private void initAppointmentTable(){
		
	appointmentTable = new CellTable<>();
	
	//create the table with the 3 appointment proposals
	    TextColumn<CalendarEvent> dateColumn = new TextColumn<CalendarEvent>() {
	      @Override
	      public String getValue(CalendarEvent event) {
	        return event.getDate();
	      }
	    };
	    
	    TextColumn<CalendarEvent> doctorColumn = new TextColumn<CalendarEvent>() {
	      @Override
	      public String getValue(CalendarEvent event) {
	        return event.getDoctorOrthoptist();
	      }
	    };
	    TextColumn<CalendarEvent> typeColumn = new TextColumn<CalendarEvent>() {
		      @Override
		      public String getValue(CalendarEvent event) {
		        return event.getType();
		      }
	    };
	    TextColumn<CalendarEvent> reasonColumn = new TextColumn<CalendarEvent>() {
		      @Override
		      public String getValue(CalendarEvent event) {
		        return event.getReason();
		      }
		 };
	    
	 // Add the columns.
	   
	    appointmentTable.addColumn(dateColumn, "Date");
	    //TODO: TIME
	    appointmentTable.addColumn(doctorColumn, "Doctor / Orthoptist");
	    appointmentTable.addColumn(typeColumn, "Appointment Type");
	    appointmentTable.addColumn(reasonColumn, "Reason");

	    
	 // Create a data provider.
	    ListDataProvider<CalendarEvent> dataProvider = new ListDataProvider<CalendarEvent>();

	    // Connect the table to the data provider.
	    dataProvider.addDataDisplay(appointmentTable);
	    
	    // Add the data to the data provider, which automatically pushes it to the
	    // widget.
	    List<CalendarEvent> list = dataProvider.getList();
	    for (CalendarEvent ev : _events) {
	      list.add(ev);
	    }
	    
		//create the table with the selected appointment
		chosenTable = new CellTable<>();
		
	    TextColumn<CalendarEvent> cDateColumn = new TextColumn<CalendarEvent>() {
	      @Override
	      public String getValue(CalendarEvent event) {
	        return event.getDate();
	      }
	    };
	    
	    TextColumn<CalendarEvent> cDoctorColumn = new TextColumn<CalendarEvent>() {
		      @Override
		      public String getValue(CalendarEvent event) {
		        return event.getDoctorOrthoptist();
		      }
		    };
		    TextColumn<CalendarEvent> cTypeColumn = new TextColumn<CalendarEvent>() {
			      @Override
			      public String getValue(CalendarEvent event) {
			        return event.getType();
			      }
		    };
		    TextColumn<CalendarEvent> cReasonColumn = new TextColumn<CalendarEvent>() {
			      @Override
			      public String getValue(CalendarEvent event) {
			        return event.getReason();
			      }
			 };
		    
		 // Add the columns.
		   
		    chosenTable.addColumn(cDateColumn, "Date");
		    chosenTable.addColumn(cDoctorColumn, "Doctor / Orthoptis");
		    chosenTable.addColumn(cTypeColumn, "Appointment Type");
		    chosenTable.addColumn(cReasonColumn, "Reason");

		    
		 // Create a data provider.
		    ListDataProvider<CalendarEvent> chosenDataProvider = new ListDataProvider<CalendarEvent>();

		    // Connect the table to the data provider.
		    chosenDataProvider.addDataDisplay(chosenTable);
		    
		    // Add the data to the data provider, which automatically pushes it to the
		    // widget.
		   final List<CalendarEvent> chosenList = chosenDataProvider.getList();


	    //appointment table handler
	    final SingleSelectionModel<CalendarEvent> selectionModel = new SingleSelectionModel<CalendarEvent>();
	    appointmentTable.setSelectionModel(selectionModel);
	    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler(){

			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				CalendarEvent selected = selectionModel.getSelectedObject();
				if(selected != null){
					chosenList.clear();
					chosenList.add(selected);
					_calendarEvent = selected;
					selectionModel.setSelected(selected, false);
				}
				
			}
	    });

	    appointmentTable.sinkEvents(Event.ONCLICK);
	
	    tablePanel.add(appointmentTable);
	    chosenPanel.add(chosenTable);
	}
	
	
	@UiHandler("okButton")
	void onClickOkButton(ClickEvent event) {
		
		AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("AppointmentChoice - Failed to connect to server. Please try again in a few minutes, or contact the system administrator.");
			}

			@Override
			public void onSuccess(Boolean b) {
				Window.alert("Appointment has been added");
				Index.forward(new AppointmentOverview(_patient));
			}
		};

		appointmentChoiceAsyncService.addAppointment(_patient, _calendarEvent, callback);
				
	
	}
	
	@UiHandler("backButton")
	void onClickBackButton(ClickEvent event){
		Index.forward(new AppointmentRequestForm(_patient));
	}
	
	@UiHandler("logoutButton")
	void onClickLogoutButton(ClickEvent event){
		Index.forward(new Login());
	}


}
