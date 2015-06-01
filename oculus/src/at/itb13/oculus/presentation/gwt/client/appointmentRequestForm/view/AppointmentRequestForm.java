
package at.itb13.oculus.presentation.gwt.client.appointmentRequestForm.view;

import java.time.LocalDateTime;
import java.util.Date;

import at.itb13.oculus.presentation.gwt.client.appointmentRequestForm.rpc.AppointmentCheckService;
import at.itb13.oculus.presentation.gwt.client.appointmentRequestForm.rpc.AppointmentCheckServiceAsync;

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
		datepicker1.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				datepicker1ErrorLabel.setText(event.getValue().toString());
			}
		});

		datepicker2.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				datepicker2ErrorLabel.setText(event.getValue().toString());
			}
		});
	}

	@UiField
	ListBox weekdayListBox;

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
	DatePicker datepicker2;

	@UiField
	Button addButton1;

	@UiField
	Label fromErrorLabel1;

	@UiField
	Label toErrorLabel1;

	@UiField
	Label weekdayErrorLabel1;

	@UiField
	Button addButton2;

	@UiField
	Label fromErrorLabel2;

	@UiField
	Label toErrorLabel2;

	@UiField
	Label weekdayErrorLabel2;

	@UiField
	Button addButton3;

	@UiField
	Label fromErrorLabel3;

	@UiField
	Label toErrorLabel3;

	@UiField
	Label weekdayErrorLabel3;
	
	@UiField
	Label datepicker1ErrorLabel;

	@UiField
	Label datepicker2ErrorLabel;

	@UiHandler("addButton1")
	void testButton(ClickEvent event) {
		datepicker1.setValue(new Date(), true);
		datepicker2.setValue(new Date(), true);

		int index = weekdayListBox.getSelectedIndex();
		String weekday = weekdayListBox.getItemText(index);
		String from = fromTextBox1.getText();
		String to = toTextBox1.getText();
		Date date1 = datepicker1.getHighlightedDate();
		Date date2 = datepicker2.getHighlightedDate();
		String socialInsuranceNumber = "3333333333";
		String appointmentType = "Child Treatment";
		String dateString1 = DateTimeFormat.getFormat("MM/dd/yyyy").format(
				date1);
		String dateString2 = DateTimeFormat.getFormat("MM/dd/yyyy").format(
				date2);

		AsyncCallback<LocalDateTime> callback = new AsyncCallback<LocalDateTime>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Failed to connect to server. Please try again in a few minutes, or contact the system administrator.");
			}

			@Override
			public void onSuccess(LocalDateTime result) {
				
			}
		};
				
		appointmentCheckService.getPossibleAppointment(weekday, from, to, date1, date2, socialInsuranceNumber, appointmentType, callback);
		
		datepicker1ErrorLabel.setText(dateString1);
		datepicker2ErrorLabel.setText(dateString2);
		weekdayErrorLabel1.setText("Tag: " + weekday);
		fromErrorLabel1.setText("von: " + from);
		toErrorLabel1.setText("bis: " + to);
	}

}
