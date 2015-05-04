package at.itb13.oculus.presentation.view;

import java.util.List;

import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.application.calendar.CalendarController;
import at.itb13.oculus.domain.interfaces.ICalendar;
import at.itb13.oculus.domain.interfaces.IEventType;
import at.itb13.oculus.presentation.util.CalendarEventTypeSringConverter;
import at.itb13.oculus.presentation.util.CalendarSringConverter;
import at.itb13.oculus.presentation.util.DoctorSringConverter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 04.05.2015
 */
public class NewAppointmentController {
	
	@FXML
	private TextField _patientNameField;
	@FXML
	private Button _searchPatientButton;
	@FXML
	private TextField _patientField;
	@FXML
	private Button _deletePatientButton;
	@FXML
	private DatePicker _datePicker;
	@FXML
	private Spinner<Integer> _startTimeSpinnerHour;
	@FXML
	private Spinner<Integer> _startTimeSpinnerMin;
	@FXML
	private Spinner<Integer> _endTimeSpinnerHour;
	@FXML
	private Spinner<Integer> _endTimeSpinnerMin;
	@FXML
	private ComboBox<IEventType> _typeBox;
	@FXML
	private ComboBox<ICalendar> _doctorBox;
	@FXML
	private TextArea _resonText;
	
	private Stage _dialogStage;
	
	private boolean okClicked = false;
	
	@FXML
	private void initialize(){
		//setItemsToDoctorBox();
		initSpinner();
	}
	
	/**
	 * fills the combobox doctor with calendars
	 */
	private void setItemsToDoctorBox() {
		_doctorBox.setConverter(new CalendarSringConverter());
		List<CalendarController> calContrs = ControllerFacade.getInstance().getAllCalendarController(); //--> TODO: Change to Interface INewAppointmentController getAllCalendars
		for(CalendarController c : calContrs){
			//_doctorBox.getItems().add(c.getCalendar());
		}
	}
	
	private void setItemsToTypeBox(){
		_typeBox.setConverter(new CalendarEventTypeSringConverter());
	//	_typeBox.getItems().addAll(ControllerFacade.getInstance().getINewAppointmentController.getAllEventType());
	}
	
	private void initSpinner(){
		SpinnerValueFactory<Integer> hours = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
		_startTimeSpinnerHour.setValueFactory(hours);
		_endTimeSpinnerHour.setValueFactory(hours);
		
		SpinnerValueFactory<Integer> mins = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
		_startTimeSpinnerMin.setValueFactory(mins);
		_endTimeSpinnerMin.setValueFactory(mins);
	}

	/**
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		_dialogStage = dialogStage;
		
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}

}
