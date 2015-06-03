
package at.itb13.oculus.presentation.gwt.client.appointmentRequestForm.view;

import java.util.Date;
import java.util.List;

import at.itb13.oculus.presentation.gwt.client.Index;
import at.itb13.oculus.presentation.gwt.client.appointmentChoice.view.AppointmentChoice;
import at.itb13.oculus.presentation.gwt.client.appointmentRequestForm.rpc.AppointmentCheckService;
import at.itb13.oculus.presentation.gwt.client.appointmentRequestForm.rpc.AppointmentCheckServiceAsync;
import at.itb13.oculus.presentation.gwt.client.login.view.Login;
import at.itb13.oculus.presentation.gwt.shared.CalendarEvent;
import at.itb13.oculus.presentation.gwt.shared.Patient;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.tractionsoftware.gwt.user.client.ui.UTCTimeBox;

public class AppointmentRequestForm extends Composite {
	private static AppointmentRequestFormUiBinder uiBinder = GWT
			.create(AppointmentRequestFormUiBinder.class);
	
	private final AppointmentCheckServiceAsync appointmentCheckService = GWT
		.create(AppointmentCheckService.class);
	
	interface AppointmentRequestFormUiBinder extends
			UiBinder<Widget, AppointmentRequestForm> {
	}

	public AppointmentRequestForm(Patient patient) {
		initWidget(uiBinder.createAndBindUi(this));
		_patient = patient;
		//Init the from and to Datelabels
		fromDateLabel.setText("From: ");
		toDateLabel.setText("To: ");
		
		
		nameLabel.setText(patient.getName());
		sinLabel.setText(patient.getSin());
		doctorLabel.setText(patient.getDoctor());
		
		//Init all timeboxes:
		
		//From-To Timebox 1
		fromTimeBox1.addValueChangeHandler(new ValueChangeHandler<Long>() {
            public void onValueChange(ValueChangeEvent<Long> event) {
                fromTimeBox1.setValue(event.getValue());
                //My little test
//                fromLabel1.setText(fromTimeBox1.getText());
            }
        });
		
		toTimeBox1.addValueChangeHandler(new ValueChangeHandler<Long>() {
            public void onValueChange(ValueChangeEvent<Long> event) {
            	toTimeBox1.setValue(event.getValue());
            }
        });
		
		//From-To Timebox 2
		
		fromTimeBox2.addValueChangeHandler(new ValueChangeHandler<Long>() {
            public void onValueChange(ValueChangeEvent<Long> event) {
                fromTimeBox2.setValue(event.getValue());
            }
        });
		
		toTimeBox2.addValueChangeHandler(new ValueChangeHandler<Long>() {
            public void onValueChange(ValueChangeEvent<Long> event) {
            	toTimeBox2.setValue(event.getValue());
            }
        });
		
		//From-To Timebox 3
		
		fromTimeBox3.addValueChangeHandler(new ValueChangeHandler<Long>() {
            public void onValueChange(ValueChangeEvent<Long> event) {
                fromTimeBox3.setValue(event.getValue());
            }
        });
		
		toTimeBox3.addValueChangeHandler(new ValueChangeHandler<Long>() {
            public void onValueChange(ValueChangeEvent<Long> event) {
            	toTimeBox3.setValue(event.getValue());
            }
        });
		
		//Init datepicker
		
		datepicker1.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				if(!_isFirstDatePicked){
					toDateLabel.setText("To: ");
					_fromDate = datepicker1.getHighlightedDate();
					String dateString1 = DateTimeFormat.getFormat("MM/dd/yyyy").format(_fromDate);
					fromDateLabel.setText("From: " + dateString1);
					_isFirstDatePicked = true;
				}
				else{
					_toDate = datepicker1.getHighlightedDate();
					String dateString2 = DateTimeFormat.getFormat("MM/dd/yyyy").format(_toDate);
					toDateLabel.setText("To: " + dateString2);
					_isFirstDatePicked = false;
				}
			}
		});
		
		AsyncCallback<List<String>> callback = new AsyncCallback<List<String>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Failed to connect to server. Please try again in a few minutes, or contact the system administrator.");
			}

			@Override
			public void onSuccess(List<String> result) {
				//add all eventtypes to ListBox
//				result.forEach(s -> eventTypeListBox.addItem(s));
				for(String s : result) {
					eventTypeListBox.addItem(s);
				}
			}
			
		};
		appointmentCheckService.getEventTypes(callback);
		
		weekdayListBox2.setVisible(false);
		weekdayListBox3.setVisible(false);
		addButton2.setVisible(false);
		fromTimeBox2.setVisible(false);
		fromTimeBox3.setVisible(false);
		toTimeBox2.setVisible(false);
		toTimeBox3.setVisible(false);
		fromLabel2.setVisible(false);
		toLabel2.setVisible(false);
		fromLabel3.setVisible(false);
		toLabel3.setVisible(false);
		removeButton1.setVisible(false);
		removeButton2.setVisible(false);
	}
	
	private Patient _patient;
	private boolean _isFirstDatePicked;
	private Date _fromDate;
	private Date _toDate;
	private boolean _isAdded1;
	private boolean _isAdded2;
	private List<CalendarEvent> _results;
	private String _reason;

	@UiField
	ListBox weekdayListBox1;
	
	@UiField
	ListBox weekdayListBox2;
	
	@UiField
	ListBox weekdayListBox3;

	@UiField
	UTCTimeBox fromTimeBox1;

	@UiField
	UTCTimeBox toTimeBox1;
	
	@UiField
	UTCTimeBox fromTimeBox2;

	@UiField
	UTCTimeBox toTimeBox2;
	
	@UiField
	UTCTimeBox fromTimeBox3;

	@UiField
	UTCTimeBox toTimeBox3;

	@UiField
	DatePicker datepicker1;

	@UiField
	Button addButton1;

	@UiField
	Label fromLabel1;
	
	@UiField
	Label fromLabel2;
	
	@UiField
	Label fromLabel3;
	
	@UiField
	Label toLabel1;
	
	@UiField
	Label toLabel2;
	
	@UiField
	Label toLabel3;
	
	@UiField
	Label fromErrorLabel1;

	@UiField
	Label toErrorLabel1;

	@UiField
	Label weekdayErrorLabel1;

	@UiField
	Button addButton2;
	
	@UiField
	Button removeButton1;

	@UiField
	Label fromErrorLabel2;

	@UiField
	Label toErrorLabel2;

	@UiField
	Label weekdayErrorLabel2;

	@UiField
	Label fromErrorLabel3;

	@UiField
	Label toErrorLabel3;
	
	@UiField
	Button removeButton2;

	@UiField
	Label weekdayErrorLabel3;
	
	@UiField
	Label datepicker1ErrorLabel;
	
	@UiField
	Label fromDateLabel;
	
	@UiField
	Label toDateLabel;
	
	@UiField
	ListBox eventTypeListBox;
	
	@UiField
	Button submitButton;
	
	@UiField
	Button logOutButton;
	
	@UiField
	Button resetButton;
	
	@UiField
	Label nameLabel;
	
	@UiField
	Label sinLabel;
	
	@UiField
	Label doctorLabel;
	
	@UiField
	TextBox reasonForAppointmentTextBox;
	
	@UiHandler("addButton1")
	void onClickAddButton1(ClickEvent event){
		addButton2.setVisible(true);
		fromTimeBox2.setVisible(true);
		toTimeBox2.setVisible(true);
		fromLabel2.setVisible(true);
		toLabel2.setVisible(true);
		addButton1.setVisible(false);
		removeButton1.setVisible(true);
		weekdayListBox2.setVisible(true);
		_isAdded1 = true;
	}
	
	@UiHandler("removeButton1")
	void onClickRemoveButton1(ClickEvent event){
		addButton2.setVisible(false);
		fromTimeBox2.setVisible(false);
		toTimeBox2.setVisible(false);
		fromLabel2.setVisible(false);
		toLabel2.setVisible(false);
		addButton1.setVisible(true);
		removeButton1.setVisible(false);
		weekdayListBox2.setVisible(false);
		_isAdded1 = false;
	}
	
	@UiHandler("addButton2")
	void onClickAddButton2(ClickEvent event){
		fromTimeBox3.setVisible(true);
		toTimeBox3.setVisible(true);
		fromLabel3.setVisible(true);
		toLabel3.setVisible(true);
		addButton2.setVisible(false);
		removeButton1.setVisible(false);
		removeButton2.setVisible(true);
		weekdayListBox3.setVisible(true);
		_isAdded2 = true;
	}
	
	@UiHandler("removeButton2")
	void onClickRemoveButton2(ClickEvent event){
		fromTimeBox3.setVisible(false);
		toTimeBox3.setVisible(false);
		fromLabel3.setVisible(false);
		toLabel3.setVisible(false);
		addButton2.setVisible(true);
		removeButton1.setVisible(true);
		removeButton2.setVisible(false);
		weekdayListBox3.setVisible(false);
		_isAdded2 = false;
	}
	
	@UiHandler("submitButton")
	void onClickSubmitButton(ClickEvent event) {
		datepicker1.setValue(new Date(), true);

		int index1 = weekdayListBox1.getSelectedIndex();
		String weekday1 = weekdayListBox1.getItemText(index1);
		
		int index2 = weekdayListBox2.getSelectedIndex();
		String weekday2 = weekdayListBox2.getItemText(index2);
		
		int index3 = weekdayListBox3.getSelectedIndex();
		String weekday3 = weekdayListBox3.getItemText(index3);
		
		String from1 = fromTimeBox1.getText();
		String to1 = toTimeBox1.getText();
			
		String from2 = fromTimeBox2.getText();
		String to2 = toTimeBox2.getText();
		
		String from3 = fromTimeBox3.getText();
		String to3 = toTimeBox3.getText();
	
		int index4 = eventTypeListBox.getSelectedIndex();
		String appointmentType = eventTypeListBox.getItemText(index4);
		
		_reason = reasonForAppointmentTextBox.getText();

		AsyncCallback<CalendarEvent> callback = new AsyncCallback<CalendarEvent>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Wrong Input, please try again.");
			}

			@Override
			public void onSuccess(CalendarEvent result) {
				result.setReason(_reason);
				_results.add(result);
//					Index.forward(new AppointmentChoice(_patient, results));
			}
		};
		
		boolean isSameDay = false;
		
		//TODO: handle same day
		appointmentCheckService.getPossibleAppointment(weekday1, from1, to1, _fromDate, _toDate, isSameDay, 
														appointmentType, callback);
		if(_isAdded1){
			appointmentCheckService.getPossibleAppointment(weekday2, from2, to2, _fromDate, _toDate, isSameDay, 
															appointmentType, callback);
			if(_isAdded2){
				appointmentCheckService.getPossibleAppointment(weekday3, from3, to3, _fromDate, _toDate, isSameDay, 
																appointmentType, callback);
			}
		}
		Index.forward(new AppointmentChoice(_patient, _results));
	}
	
	@UiHandler("resetButton")
	void onClickResetButton(ClickEvent event){
		resetUI();
	}
	
	private void resetUI(){
		weekdayListBox2.setVisible(false);
		weekdayListBox3.setVisible(false);
		addButton2.setVisible(false);
		fromTimeBox2.setVisible(false);
		fromTimeBox3.setVisible(false);
		toTimeBox2.setVisible(false);
		toTimeBox3.setVisible(false);
		fromLabel2.setVisible(false);
		toLabel2.setVisible(false);
		fromLabel3.setVisible(false);
		toLabel3.setVisible(false);
		removeButton1.setVisible(false);
		removeButton2.setVisible(false);
		addButton1.setVisible(true);
		_isFirstDatePicked = false;
		_fromDate = null;
		_toDate = null;
		_isAdded1 = false;
		_isAdded2 = false;
		_reason = "";
		
		reasonForAppointmentTextBox.setText(" ");
		
		weekdayListBox1.setItemSelected(0, true);
		weekdayListBox2.setItemSelected(0, true);
		weekdayListBox3.setItemSelected(0, true);
		
		fromTimeBox1.setText(" ");
		toTimeBox1.setText(" ");
		fromTimeBox2.setText(" ");
		toTimeBox2.setText(" ");
		fromTimeBox3.setText(" ");
		toTimeBox3.setText(" ");
		
		fromDateLabel.setText("From: ");
		toDateLabel.setText("To: ");
		
		eventTypeListBox.setItemSelected(0, true);
	}
	
	@UiHandler("logOutButton")
	void onClickLogOutButton(ClickEvent event){
		Index.forward(new Login());
	}
}
