
package at.itb13.oculus.presentation.gwt.client.appointmentRequestForm.view;

import java.util.Date;
import java.util.List;

import at.itb13.oculus.presentation.gwt.client.Index;
import at.itb13.oculus.presentation.gwt.client.appointmentChoice.view.AppointmentChoice;
import at.itb13.oculus.presentation.gwt.client.appointmentOverview.view.AppointmentOverview;
import at.itb13.oculus.presentation.gwt.client.appointmentRequestForm.rpc.AppointmentCheckService;
import at.itb13.oculus.presentation.gwt.client.appointmentRequestForm.rpc.AppointmentCheckServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
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

public class AppointmentRequestForm extends Composite {
	private static AppointmentRequestFormUiBinder uiBinder = GWT
			.create(AppointmentRequestFormUiBinder.class);
	
	private final AppointmentCheckServiceAsync appointmentCheckService = GWT
		.create(AppointmentCheckService.class);
	
	interface AppointmentRequestFormUiBinder extends
			UiBinder<Widget, AppointmentRequestForm> {
	}

	public AppointmentRequestForm() {
		initWidget(uiBinder.createAndBindUi(this));
		
		//Init the from and to Datelabels
		fromDateLabel.setText("From: ");
		toDateLabel.setText("To: ");
		
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
		fromTextBox2.setVisible(false);
		fromTextBox3.setVisible(false);
		toTextBox2.setVisible(false);
		toTextBox3.setVisible(false);
		fromLabel2.setVisible(false);
		toLabel2.setVisible(false);
		fromLabel3.setVisible(false);
		toLabel3.setVisible(false);
		removeButton1.setVisible(false);
		removeButton2.setVisible(false);
	}
	
	private boolean _validInput;
	private boolean _isFirstDatePicked;
	private Date _fromDate;
	private Date _toDate;
	private boolean isAdded1;
	private boolean isAdded2;
	private List<String> results;

	@UiField
	ListBox weekdayListBox1;
	
	@UiField
	ListBox weekdayListBox2;
	
	@UiField
	ListBox weekdayListBox3;

	@UiField
	TextBox fromTextBox1;

	@UiField
	TextBox toTextBox1;
	
	@UiField
	TextBox fromTextBox2;

	@UiField
	TextBox toTextBox2;
	
	@UiField
	TextBox fromTextBox3;

	@UiField
	TextBox toTextBox3;

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
	TextBox reasonForAppointmentTextBox;
	
	@UiHandler("addButton1")
	void addButton1(ClickEvent event){
		addButton2.setVisible(true);
		fromTextBox2.setVisible(true);
		toTextBox2.setVisible(true);
		fromLabel2.setVisible(true);
		toLabel2.setVisible(true);
		addButton1.setVisible(false);
		removeButton1.setVisible(true);
		weekdayListBox2.setVisible(true);
		isAdded1 = true;
	}
	
	@UiHandler("removeButton1")
	void removeButton1(ClickEvent event){
		addButton2.setVisible(false);
		fromTextBox2.setVisible(false);
		toTextBox2.setVisible(false);
		fromLabel2.setVisible(false);
		toLabel2.setVisible(false);
		addButton1.setVisible(true);
		removeButton1.setVisible(false);
		weekdayListBox2.setVisible(false);
		isAdded1 = false;
	}
	
	@UiHandler("addButton2")
	void addButton2(ClickEvent event){
		fromTextBox3.setVisible(true);
		toTextBox3.setVisible(true);
		fromLabel3.setVisible(true);
		toLabel3.setVisible(true);
		addButton2.setVisible(false);
		removeButton1.setVisible(false);
		removeButton2.setVisible(true);
		weekdayListBox3.setVisible(true);
		isAdded2 = true;
	}
	
	@UiHandler("removeButton2")
	void removeButton2(ClickEvent event){
		fromTextBox3.setVisible(false);
		toTextBox3.setVisible(false);
		fromLabel3.setVisible(false);
		toLabel3.setVisible(false);
		addButton2.setVisible(true);
		removeButton1.setVisible(true);
		removeButton2.setVisible(false);
		weekdayListBox3.setVisible(false);
		isAdded2 = false;
	}

	@UiHandler({"fromTextBox1"})
	void onFromBox1Change(ValueChangeEvent<String> event) {
		if (event.getValue().length() != 0) {
			if(!isTimeValid(event.getValue())){
				fromErrorLabel1.setText("This is not a valid time");
				fromErrorLabel1.setVisible(true);
				_validInput = false;
			} else {
				fromErrorLabel1.setVisible(false);
				_validInput = true;
			}
		} else {
			fromErrorLabel1.setVisible(false);
			_validInput = true;
		}
	}
	
	@UiHandler({"toTextBox1"})
	void onToBox1Change(ValueChangeEvent<String> event) {
		if (event.getValue().length() != 0) {
			if(!isTimeValid(event.getValue())){
				toErrorLabel1.setText("This is not a valid time");
				toErrorLabel1.setVisible(true);
				_validInput = false;
			} else {
				toErrorLabel1.setVisible(false);
				_validInput = true;
			}
		} else {
			toErrorLabel1.setVisible(false);
			_validInput = true;
		}
	}
	
	@UiHandler({"fromTextBox2"})
	void onFromBox2Change(ValueChangeEvent<String> event) {
		if (event.getValue().length() != 0) {
			if(!isTimeValid(event.getValue())){
				fromErrorLabel2.setText("This is not a valid time");
				fromErrorLabel2.setVisible(true);
				_validInput = false;
			} else {
				fromErrorLabel2.setVisible(false);
				_validInput = true;
			}
		}else {
			fromErrorLabel2.setVisible(false);
			_validInput = true;
		}
	}
	
	@UiHandler({"toTextBox2"})
	void onToBox2Change(ValueChangeEvent<String> event) {
		if (event.getValue().length() != 0) {
			if(!isTimeValid(event.getValue())){
				toErrorLabel2.setText("This is not a valid time");
				toErrorLabel2.setVisible(true);
				_validInput = false;
			} else {
				toErrorLabel2.setVisible(false);
				_validInput = true;
			}
		}else {
			toErrorLabel2.setVisible(false);
			_validInput = true;
		}
	}
	
	@UiHandler({"fromTextBox3"})
	void onFromBox3Change(ValueChangeEvent<String> event) {
		if (event.getValue().length() != 0) {
			if(!isTimeValid(event.getValue())){
				fromErrorLabel3.setText("This is not a valid time");
				fromErrorLabel3.setVisible(true);
				_validInput = false;
			} else {
				fromErrorLabel3.setVisible(false);
				_validInput = true;
			}
		} else {
			fromErrorLabel3.setVisible(false);
			_validInput = true;
		}
	}
	
	@UiHandler({"toTextBox3"})
	void onToBox3Change(ValueChangeEvent<String> event) {
		if (event.getValue().length() != 0) {
			if(!isTimeValid(event.getValue())){
				toErrorLabel3.setText("This is not a valid time");
				toErrorLabel3.setVisible(true);
				_validInput = false;
			} else {
				toErrorLabel3.setVisible(false);
				_validInput = true;
			}
		} else {
			toErrorLabel3.setVisible(false);
			_validInput = true;
		}
	}
	
	private boolean isTimeValid(String time) {
		final String timePattern = "^(1?[0-9]|2[0-3]):[0-5][0-9]$";

		RegExp regExp = RegExp.compile(timePattern);
		MatchResult matcher = regExp.exec(time);

		return matcher != null;
	}
	
	@UiHandler("submitButton")
	void submitButton(ClickEvent event) {
		datepicker1.setValue(new Date(), true);

		if(true){
				
			int index1 = weekdayListBox1.getSelectedIndex();
			String weekday1 = weekdayListBox1.getItemText(index1);
			
			int index2 = weekdayListBox2.getSelectedIndex();
			String weekday2 = weekdayListBox2.getItemText(index2);
			
			int index3 = weekdayListBox3.getSelectedIndex();
			String weekday3 = weekdayListBox3.getItemText(index3);
			
			String from1 = fromTextBox1.getText();
			String to1 = toTextBox1.getText();
				
			String from2 = fromTextBox2.getText();
			String to2 = toTextBox2.getText();
			
			String from3 = fromTextBox3.getText();
			String to3 = toTextBox3.getText();
		
			int index4 = eventTypeListBox.getSelectedIndex();
			String appointmentType = eventTypeListBox.getItemText(index4);
			
			/**
			 * TODO use the member variables!
			 */
			
			Date date1 = _fromDate;
			Date date2 = _toDate;
	
			AsyncCallback<String> callback = new AsyncCallback<String>() {
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Wrong Input, please try again.");
				}
	
				@Override
				public void onSuccess(String result) {
					Window.alert(result);
					results.add(result);
				}
			};
			
			boolean isSameDay = false;
			
			//TODO: handle same day
			appointmentCheckService.getPossibleAppointment(weekday1, from1, to1, date1, date2, isSameDay, 
															appointmentType, callback);
			if(isAdded1){
				appointmentCheckService.getPossibleAppointment(weekday2, from2, to2, date1, date2, isSameDay, 
																appointmentType, callback);
				if(isAdded2){
					appointmentCheckService.getPossibleAppointment(weekday3, from3, to3, date1, date2, isSameDay, 
																	appointmentType, callback);
				}
			}
		}
		//Index.forward(new AppointmentChoice(results));
	}
}
