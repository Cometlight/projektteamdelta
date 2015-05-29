package at.itb13.oculus.presentation.gwt.client.appointmentRequestForm.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
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

	interface AppointmentRequestFormUiBinder extends
			UiBinder<Widget, AppointmentRequestForm> {
	}

	public AppointmentRequestForm() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	private boolean validInput;
	
	@UiField
	ListBox weekdayListBox;
	
	@UiField
	TextBox FromTextBox;
	
	@UiField
	TextBox ToTextBox;
	
	@UiField
	DatePicker datepicker1;
	
	@UiField
	DatePicker datepicker2;
	
	@UiField
	Button addButton;
	
	@UiField
	Label fromErrorLabel;
	
	@UiField
	Label toErrorLabel;
	
	@UiField
	Label weekdayErrorLabel;
	
	@UiHandler("addButton")
	void testButton(ClickEvent event) {
		int index = weekdayListBox.getSelectedIndex();
		String weekday = weekdayListBox.getItemText(index);
		String from = FromTextBox.getText();
		String to = ToTextBox.getText();

		weekdayErrorLabel.setText("Tag: " + weekday);
		fromErrorLabel.setText("von: " + from);
		toErrorLabel.setText("bis: " + to);
	}
}
