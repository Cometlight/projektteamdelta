package at.itb13.oculus.presentation.gwt.client.appointmentRequestForm.view;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
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
	
	@UiField
	Label datepicker1ErrorLabel;
	
	@UiField
	Label datepicker2ErrorLabel;
	
	@UiHandler("addButton")
	void testButton(ClickEvent event) {
		datepicker1.setValue(new Date(), true);
		datepicker2.setValue(new Date(), true);
		
		int index = weekdayListBox.getSelectedIndex();
		String weekday = weekdayListBox.getItemText(index);
		String from = FromTextBox.getText();
		String to = ToTextBox.getText();
		Date date1 = datepicker1.getHighlightedDate();
		Date date2 = datepicker2.getHighlightedDate();
		 
		String dateString1 = DateTimeFormat.getFormat("MM/dd/yyyy").format(date1);
		String dateString2 = DateTimeFormat.getFormat("MM/dd/yyyy").format(date2);

		
		datepicker1ErrorLabel.setText(dateString1);	
		datepicker2ErrorLabel.setText(dateString2);	
		weekdayErrorLabel.setText("Tag: " + weekday);
		fromErrorLabel.setText("von: " + from);
		toErrorLabel.setText("bis: " + to);
	}
}
