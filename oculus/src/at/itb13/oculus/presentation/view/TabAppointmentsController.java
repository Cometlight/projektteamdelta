package at.itb13.oculus.presentation.view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.application.calendar.CalendarController;
import at.itb13.oculus.application.exceptions.InvalidInputException;
import at.itb13.oculus.application.queue.QueueController;
import at.itb13.oculus.domain.EventType;
import at.itb13.oculus.domain.readonlyinterfaces.CalendarEventRO;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import at.itb13.oculus.domain.readonlyinterfaces.QueueRO;
import at.itb13.oculus.presentation.OculusMain;
import at.itb13.oculus.presentation.util.QueueSringConverter;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 15.04.2015
 */
public class TabAppointmentsController {
	private static final Logger _logger = LogManager
			.getLogger(TabAppointmentsController.class.getName());

	@FXML
	private TableView<CalendarEventRO> _appointmentTable;
	@FXML
	private TableColumn<CalendarEventRO, String> _timeColumn;
	@FXML
	private TableColumn<CalendarEventRO, String> _patientColumn;
	@FXML
	private TableColumn<CalendarEventRO, String> _typeColumn;

	@FXML
	private DatePicker _datePicker;
	
	@FXML
	private Label _descriptionLabel;
	@FXML
	private Label _dateTimeLabel;
	@FXML
	private Label _patientNotInDatabaseLabel;
	@FXML
	private Label _patientLabel;
	@FXML
	private Label _eventTypeLabel;
	@FXML
	private Label _doctorLabel;
	@FXML
	private Button _addPatientButton;

	@FXML
	private ComboBox<QueueRO> _queueBox;
	@FXML
	private Button _insertQueueButton;
	@FXML
	private BorderPane _patientRecordBorderPane;

	private ObservableList<CalendarEventRO> _appointmentsList = FXCollections.observableArrayList();

	private OculusMain _main;

	// general Methods
	public void setMain(OculusMain main) {
		_main = main;
	}

	@FXML
	private void initialize() {
		
		_appointmentTable.setItems(_appointmentsList);

//		_timeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CalendarEventRO, String>, ObservableValue<String>>() {
//			@Override
//			public ObservableValue<String> call(TableColumn.CellDataFeatures<CalendarEventRO, String> event) {
//				return new SimpleStringProperty(
//						event.getValue().getEventStart().getHour()
//						+ ":"
//						+ event.getValue().getEventStart().getMinute());
//			}
//		});
		
		_timeColumn.setCellValueFactory(new Callback<CellDataFeatures<CalendarEventRO, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CalendarEventRO, String> event) {
				return new SimpleStringProperty(
						event.getValue().getEventStart().getHour()	// TODO: Better formatting #hashtag
						+ ":"
						+ event.getValue().getEventStart().getMinute());
			}
		});
		
		_patientColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CalendarEventRO, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<CalendarEventRO, String> event) {
				if (event.getValue().getPatient() != null) {
					return new SimpleStringProperty(
							event.getValue().getPatient().getFirstName()
							+ " "
							+ event.getValue().getPatient().getLastName());
				} else {
					return new SimpleStringProperty(event.getValue().getPatientName());
				}
			}
		});

		_typeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CalendarEventRO, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<CalendarEventRO, String> event) {
				return new SimpleStringProperty(event.getValue().getEventtype().getEventTypeName());
			}
		});
		
		
		// Color the rows depending on the state of the CalendarEvents
		_appointmentTable.setRowFactory(new Callback<TableView<CalendarEventRO>, TableRow<CalendarEventRO>>() {
	        @Override
	        public TableRow<CalendarEventRO> call(TableView<CalendarEventRO> tableView) {
	            final TableRow<CalendarEventRO> row = new TableRow<CalendarEventRO>() {
	                @Override
	                protected void updateItem(CalendarEventRO calEv, boolean empty){
	                    super.updateItem(calEv, empty);
	                    // Rather use a css file and
	                    //  use  getStyleClass().add("classname");
	                    // and
	                    //  use  getStyleClass().removeAll(Collections.singleton("classname"));
	                    // instead of applying the colors directly here in the code.
	                    setStyle(null);
	                    if(calEv != null) {
	                    	if(calEv.isOpen()) {
	                    		if(calEv.getEventEnd().isBefore(LocalDateTime.now())) {	// patient missed the appointment
	                    			setStyle("-fx-background-color: red");
	                    		} else {	// it's ok; the patient still has time to come to the appointment sometime in the future
	                    			setStyle(null);
	                    			System.out.println("calev is after now");
	                    		}
		                    } else {
		                    	setStyle("-fx-background-color: lightgrey");
		                    	
		                    }
	                    }
	                }
	            };
	            return row;
	        }
	    });
		
		setItemsToQueueBox();
		showAppointmentInformation(null);
		
		_appointmentTable
				.getSelectionModel()
				.selectedItemProperty()
				.addListener(
						(observable, oldValue, newValue) -> {
							showAppointmentInformation(newValue);
							showPatientRecord((newValue == null) ? null : newValue.getPatient());
						});
		
		_datePicker.setValue(LocalDate.now());	// Show today's appointments by default
		changeDate(); 							// make sure, the appointments of today are loaded

	}

	/**
	 * fills the table with the events of the selected date
	 * @param startDate, endDate
	 */
	private void setCalendarEvents(LocalDateTime startDate, LocalDateTime endDate) {
		List<CalendarController> listCalCo = ControllerFacade.getInstance()
				.getAllCalendarController();

		// delete current appointments from the list
		_appointmentsList.clear();
		
		List<CalendarEventRO> events = new LinkedList<>();
		try {

			// With list instead:
			for (CalendarController calCo : listCalCo) {
				events.addAll(calCo.getCalendarEventsInTimespan(startDate,endDate));
			}

			for (CalendarEventRO e : events) {
				_appointmentsList.add(e);

			}

			_logger.info("Showing appointments between " + startDate + " and " + endDate);
		} catch (InvalidInputException e) {
			_logger.warn(e);
		}
	}

	public ObservableList<CalendarEventRO> getAppointments() {
		return _appointmentsList;
	}

	public void addAppointment(CalendarEventRO e) {
		_appointmentsList.add(e);
	}

	public void clearAppointments() {
		_appointmentsList.clear();

	}
	
	public void showPatientRecord(PatientRO patient) {
		_main.showPatientRecord(_patientRecordBorderPane, patient);
	}

	/**
	 * shows the appointment Informations of the selected CalendarEvent
	 * @param event
	 */
	public void showAppointmentInformation(CalendarEventRO event) {
		if (event != null) {
			_descriptionLabel.setText(event.getDescription());
			_dateTimeLabel.setText(event.getEventStart().toString());
			EventType type = event.getEventtype();
			_eventTypeLabel.setText(type.getEventTypeName());
			if(event.getCalendar().getDoctor() != null){
				_doctorLabel.setText(event.getCalendar().getDoctor().getUser().getFirstName() + " " +event.getCalendar().getDoctor().getUser().getLastName());
			}else if(event.getCalendar().getOrthoptist() != null){
				_doctorLabel.setText(event.getCalendar().getOrthoptist().getUser().getFirstName() +" " + event.getCalendar().getOrthoptist().getUser().getLastName());

			}else{
				_doctorLabel.setText("");
			}
			if (event.getPatient() == null) {
				_patientNotInDatabaseLabel
						.setText("New patient!\nPlease add the patient by clicking on \"Add Patient\".\nPatient Name:");
				_patientLabel.setText(event.getPatientName());
				_addPatientButton.setDisable(false);
				_addPatientButton.setVisible(true);
				_queueBox.setDisable(true);
				_insertQueueButton.setDisable(true);
			} else {
				_patientNotInDatabaseLabel.setText("");
				_patientLabel.setText("");
				_addPatientButton.setDisable(true);
				_addPatientButton.setVisible(false);
				Boolean inQueue = false;
				List<QueueController> queCon = ControllerFacade.getInstance().getAllQueueController();
				for(QueueController controller: queCon){
					if(controller.isPatientInQueue(event.getPatient())){
						inQueue = true;
						break;
					}
				}
				if(!inQueue){
					_queueBox.setDisable(false);
					_insertQueueButton.setDisable(false);
				}
			}
			
			
		} else {
			_descriptionLabel.setText("");
			_dateTimeLabel.setText("");
			_eventTypeLabel.setText("");
			_doctorLabel.setText("");
			_patientNotInDatabaseLabel.setText("");
			_patientLabel.setText("");
			_addPatientButton.setDisable(true);
			_addPatientButton.setVisible(false);
			_queueBox.setDisable(true);
			_insertQueueButton.setDisable(true);
		}
	}
	
	/**
	 * fills the table with the events of the current date
	 * @param calendarEventRo
	 */
	public void selectCalendarEventInTable(CalendarEventRO calendarEventRo) {
		_datePicker.setValue(calendarEventRo.getEventStart().toLocalDate());
		_appointmentTable.getSelectionModel().select(calendarEventRo);
	}

	/**
	 * is called when the button "add Patient" is pushed
	 */
	@FXML
	private void addPatientControl() {

		_main.showNewPatientDialog(null);
		
		PatientRO patient = ControllerFacade.getPatientSelected();

		if(patient != null) {
			CalendarEventRO calEv = _appointmentTable.getSelectionModel().getSelectedItem();
			
			// connect the calendarevent with the newly created patient
			CalendarController calco = ControllerFacade.getInstance().getCalendarController(calEv.getCalendar());
			CalendarEventRO calEvUpdated = calco.connectCalendarEventWithPatient(calEv, patient);
			if(calEvUpdated != null) {
				// update the view as now a patient actually exists
				showAppointmentInformation(calEvUpdated);
				_main.showPatientRecord(_patientRecordBorderPane, patient);
			} else {
				_logger.error("Failed to save the connection between the CalendarEvent (" + calEv.getCalendarEventId() 
						+ ") and the recently created Patient (" + patient.getPatientId() + ").");
				// TODO: warning that it didn't work?
			}
		}
	}

	/**
	 * fills the combo box with all queues
	 */
	private void setItemsToQueueBox() {

		_queueBox.setConverter(new QueueSringConverter());
		List<QueueController> queController = ControllerFacade.getInstance().getAllQueueController();
		for (QueueController controller : queController) {		
			_queueBox.getItems().add(controller.getQueue());
		}
	}

	/**
	 * is called when the button "insert" is pushed.
	 */
	@FXML
	private void handleInsertInQueueButton() {

		QueueRO queue = _queueBox.getSelectionModel().getSelectedItem();

		if (queue != null) {
			QueueController controller = ControllerFacade.getInstance().getQueueController(queue);
			if (controller != null) {
				if(_appointmentTable.getSelectionModel().getSelectedItem().getCalendar().getDoctor().getDoctorId().equals(queue.getDoctor().getDoctorId())){	// check if the calendarEvent's doctor is equal to the queue's doctor
					try {
						controller.pushQueueEntry(_appointmentTable.getSelectionModel().getSelectedItem().getPatient());
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setContentText("Patient is added to Queue");
						alert.showAndWait();
					} catch (InvalidInputException e) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setContentText("Patient was not added to the Queue, because the patient is already in a queue.");
						alert.showAndWait();
					}
					
				} else {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setHeaderText("Waitinglist is not equate to the Doctor");
					alert.setContentText("Selected Waitinglist is not equate to the Doctor of the Appointment. Are you sure you want to continue?");
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK){
						try {
							controller.pushQueueEntry(_appointmentTable.getSelectionModel().getSelectedItem().getPatient());
							alert = new Alert(AlertType.INFORMATION);
							alert.setContentText("Patient is added to Queue");
							alert.showAndWait();
						} catch (InvalidInputException e) {
							alert = new Alert(AlertType.WARNING);
							alert.setContentText("Patient was not added to the Queue, because the patient is already in a queue.");
							alert.showAndWait();
						}

					} else {
					    alert.close();
					}
				}
			} else {
				_logger.error("Could not load QueueController for Queue with doctor '" + queue.getDoctor().getDoctorId()
						+ "' and with orthoptist '" + queue.getOrthoptist().getOrthoptistId() + "'!");
			}

		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("No queue selected");
			alert.setContentText("Please choose a Queue before insert.");
			alert.setTitle("No queue selected");
			alert.showAndWait();
		}
	}

	/**
	 * is called when the date has changed
	 */
	@FXML
	private void changeDate() {
		setCalendarEvents(_datePicker.getValue().atTime(0, 0), _datePicker.getValue().atTime(23, 59));
	}
}
