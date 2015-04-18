package at.itb13.oculus.presentation.view;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.prefs.Preferences;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.application.calendar.CalendarController;
import at.itb13.oculus.application.doctor.DoctorRequest;
import at.itb13.oculus.application.exceptions.InvalidInputException;
import at.itb13.oculus.application.queue.QueueController;
import at.itb13.oculus.domain.CalendarEvent;
import at.itb13.oculus.domain.EventType;
import at.itb13.oculus.domain.readonlyinterfaces.CalendarEventRO;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import at.itb13.oculus.domain.readonlyinterfaces.QueueRO;
import at.itb13.oculus.presentation.OculusMain;
import at.itb13.oculus.presentation.util.DoctorSringConverter;
import at.itb13.oculus.presentation.util.QueueSringConverter;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 15.04.2015
 */
public class AppointmentsController {
	private static final Logger _logger = LogManager
			.getLogger(AppointmentsController.class.getName());

	@FXML
	private TableView<CalendarEventRO> _appointmentTable;
	@FXML
	private TableColumn<CalendarEventRO, String> _timeColumn;
	@FXML
	private TableColumn<CalendarEventRO, String> _patientColumn;
	@FXML
	private TableColumn<CalendarEventRO, String> _otherColumn;

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

		setTodaysCalendarEvents();
		_appointmentTable.setItems(_appointmentsList);

		_timeColumn
				.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CalendarEventRO, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(
							TableColumn.CellDataFeatures<CalendarEventRO, String> event) {

						return new SimpleStringProperty(event.getValue()
								.getEventStart().getHour()
								+ ":"
								+ event.getValue().getEventStart().getMinute());
					}

				});
		_patientColumn
				.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CalendarEventRO, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(
							TableColumn.CellDataFeatures<CalendarEventRO, String> event) {
						if (event.getValue().getPatient() != null) {
							return new SimpleStringProperty(event.getValue()
									.getPatient().getFirstName()
									+ " "
									+ event.getValue().getPatient()
											.getLastName());
						} else {
							return new SimpleStringProperty(event.getValue()
									.getPatientName());
						}
					}

				});

		_otherColumn
				.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CalendarEventRO, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(
							TableColumn.CellDataFeatures<CalendarEventRO, String> event) {
						return new SimpleStringProperty(event.getValue()
								.getEventtype().getEventTypeName());
					}

				});
		setItemsToQueueBox();
		showAppointmentInformation(null);
		_appointmentTable
				.getSelectionModel()
				.selectedItemProperty()
				.addListener(
						(observable, oldValue, newValue) -> showAppointmentInformation(newValue));
		_appointmentTable
				.getSelectionModel()
				.selectedItemProperty()
				.addListener(
						(observable, oldValue, newValue) -> _main
								.showPatientRecord(_patientRecordBorderPane,
										newValue));

	}

	/*
	 * 
	 */
	private void setTodaysCalendarEvents() {
		List<CalendarController> listCalCo = ControllerFacade.getInstance()
				.getAllCalendarController();
		LocalDate startofend = LocalDate.now();
		LocalDateTime end = LocalDateTime.of(startofend, LocalTime.MAX);

		List<CalendarEventRO> events = new LinkedList<>();
		try {

			// With list instead:
			for (CalendarController calCo : listCalCo) {
				events.addAll(calCo.getCalendarEventsInTimespan(LocalDateTime.now(),
						LocalDateTime.MAX));
			}

			for (CalendarEventRO e : events) {
				_appointmentsList.add(e);

			}

		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	private void showAppointmentInformation(CalendarEventRO event) {
		if (event != null) {
			_descriptionLabel.setText(event.getDescription());
			_dateTimeLabel.setText(event.getEventStart().toString());
			if (event.getPatient() == null) {
				_patientNotInDatabaseLabel
						.setText("Patient is not in Database.\nPatient Name");
				_addPatientButton.setDisable(false);
				_addPatientButton.setVisible(true);
				_queueBox.setDisable(true);
				_insertQueueButton.setDisable(true);
			} else {
				_patientNotInDatabaseLabel.setText("");
				_addPatientButton.setDisable(true);
				_addPatientButton.setVisible(false);
				_queueBox.setDisable(false);
				_insertQueueButton.setDisable(false);
			}
			_patientLabel.setText(event.getPatientName());
			EventType type = event.getEventtype();

			_eventTypeLabel.setText(type.getEventTypeName());
		} else {
			_descriptionLabel.setText("");
			_dateTimeLabel.setText("");
			_eventTypeLabel.setText("");
			_patientNotInDatabaseLabel.setText("");
			_patientLabel.setText("");
			_addPatientButton.setDisable(true);
			_addPatientButton.setVisible(false);
			_queueBox.setDisable(true);
			_insertQueueButton.setDisable(true);
		}
	}

	@FXML
	private void addPatientControl() {

		_main.showNewPatientDialog();

		CalendarController calco = ControllerFacade.getInstance()
				.getCalendarController(
						_appointmentTable.getSelectionModel().getSelectedItem()
								.getCalendar());
		calco.connectCalendarEventWithPatient(_appointmentTable.getSelectionModel().getSelectedItem(), _main.getCreatedPatient());
	}

	private void setItemsToQueueBox() {

		_queueBox.setConverter(new QueueSringConverter());
		List<QueueController> queController = ControllerFacade.getInstance().getAllQueueController();
		for (QueueController controller : queController) {		
			_queueBox.getItems().add(controller.getQueue());
		}
	}

	@FXML
	private void handleInsertInQueueButton() {

		QueueRO queue = _queueBox.getSelectionModel().getSelectedItem();
		QueueController controller = null;

		if (queue != null) {
			if (queue.getDoctor() != null) {
				controller = ControllerFacade.getInstance().getQueueController(queue.getDoctor().getDoctorId(), null);
			} else if (queue.getOrthoptist() != null) {
				controller = ControllerFacade.getInstance().getQueueController(null, queue.getOrthoptist().getOrthoptistId());
			}
			if (controller != null) {
				controller.pushQueueEntry(_appointmentTable.getSelectionModel().getSelectedItem().getPatient());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("Patient is added to Queue");
				alert.showAndWait();
			}

		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("No queue selected");
			alert.setContentText("Please choose a Queue bevor insert.");
			alert.setTitle("No queue selected");
			alert.showAndWait();
		}
	}

}
