package at.itb13.oculus.presentation.view;

import java.util.List;

import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.application.calendar.CalendarController;
import at.itb13.oculus.domain.interfaces.ICalendar;
import at.itb13.oculus.domain.interfaces.IEventType;
import at.itb13.oculus.presentation.util.CalendarEventTypeStringConverter;
import at.itb13.oculus.presentation.util.CalendarStringConverter;
import at.itb13.oculus.presentation.util.DoctorStringConverter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
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
//		_doctorBox.setConverter(new CalendarSringConverter());
//		List<CalendarController> calContrs = ControllerFacade.getInstance().getAllCalendarController(); //--> TODO: Change to Interface INewAppointmentController getAllCalendars
//		for(CalendarController c : calContrs){
			//_doctorBox.getItems().add(c.getCalendar());
//		}
	}
	
	private void setItemsToTypeBox(){
	//	_typeBox.setConverter(new CalendarEventTypeSringConverter());
	//	_typeBox.getItems().addAll(ControllerFacade.getInstance().getINewAppointmentController.getAllEventType());
	}
	
	private void initSpinner(){
		
		
		SpinnerValueFactory<Integer> sHours = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
		SpinnerValueFactory<Integer> sMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
		SpinnerValueFactory<Integer> eHours = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
		SpinnerValueFactory<Integer> eMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
		
		//start Hour
		_startTimeSpinnerHour.setValueFactory(sHours);
		SpinnerValueFactory.IntegerSpinnerValueFactory startHourFactory = (IntegerSpinnerValueFactory) _startTimeSpinnerHour.getValueFactory();  
        startHourFactory.setMax(23); 
        startHourFactory.setValue(0);
        startHourFactory.setWrapAround(true);
        _startTimeSpinnerHour.setEditable(true); 
        
        //End Hour
        _endTimeSpinnerHour.setValueFactory(eHours);
        SpinnerValueFactory.IntegerSpinnerValueFactory endHourFactory =(IntegerSpinnerValueFactory) _endTimeSpinnerHour.getValueFactory();
        endHourFactory.setMax(23); 
        endHourFactory.setValue(0);
        endHourFactory.setWrapAround(true);
        _endTimeSpinnerHour.setEditable(true);
		
		//start minutes
        _startTimeSpinnerMin.setValueFactory(sMin);
        SpinnerValueFactory.IntegerSpinnerValueFactory startMinFactory = (IntegerSpinnerValueFactory) _startTimeSpinnerMin.getValueFactory();
        startMinFactory.setMax(59);
        startMinFactory.setValue(0);
        startMinFactory.setWrapAround(true);
        _startTimeSpinnerMin.setEditable(true);
        
      //end minutes
        _endTimeSpinnerMin.setValueFactory(eMin);
        SpinnerValueFactory.IntegerSpinnerValueFactory endMinFactory = (IntegerSpinnerValueFactory) _endTimeSpinnerMin.getValueFactory();
        endMinFactory.setMax(59);
        endMinFactory.setValue(0);
        endMinFactory.setWrapAround(true);
        _endTimeSpinnerMin.setEditable(true);
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
