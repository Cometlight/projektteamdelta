package at.itb13.oculus.presentation.view;

import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.domain.readonlyinterfaces.CalendarEventRO;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import at.itb13.oculus.domain.readonlyinterfaces.QueueEntryRO;
import at.itb13.oculus.domain.readonlyinterfaces.QueueRO;
import at.itb13.oculus.presentation.OculusMain;
import at.itb13.oculus.presentation.util.QueueStringConverter;
import at.itb13.teamD.application.exceptions.InvalidInputException;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 15.04.2015
 */
public class TabQueueController {
	
	private static final Logger _logger = LogManager.getLogger(TabQueueController.class.getName());
	
	private static final int REFRESH_INTERVAL = 60000;	// in milliseconds
	
	@FXML
	private ListView<QueueEntryRO> _queueEntrysListView;
	@FXML
	private ComboBox<QueueRO> _queueBox;
	
	private ObservableList<QueueEntryRO> _queueEntryList = FXCollections.observableArrayList();
	
	@FXML
	private Label _dateTimeLabel;
	@FXML
	private Label _typeLabel;
	@FXML
	private Label _reasonLabel;
	@FXML
	private Button _endExaminationButton;
	@FXML
	private ComboBox<QueueRO> _nextQueueBox;
	@FXML
	private Button _insertButton;
	@FXML
	private Label _orLabel;
	@FXML
	private BorderPane _patientRecordBorderPane;
	//general Attributes
	private OculusMain _main;
	
	private CalendarEventRO _curCalendarEvent;
	
	private Timer _timer;
			
	//general Methods
	public void setMain(OculusMain main) {
		_main = main;		        
	}
			 
	@FXML
	private void initialize() {
		
		_queueEntrysListView.setItems(_queueEntryList);
		
		 _queueEntrysListView.setCellFactory(new Callback<ListView<QueueEntryRO>, ListCell<QueueEntryRO>>(){

			@Override
			public ListCell<QueueEntryRO> call(ListView<QueueEntryRO> param) {
				ListCell<QueueEntryRO> cell = new ListCell<QueueEntryRO>(){
					 
                    @Override
                    protected void updateItem(QueueEntryRO t, boolean bln) {
                        super.updateItem(t, bln);
                        if(t != null){
                        	setText(t.getPatient().getFirstName() + " "+ t.getPatient().getLastName()+"\nArrival Time: " +t.getArrivalTime().format(DateTimeFormatter.ofPattern("HH:mm")));
                        }else{
                        	setText("");
                        }
                    }
				};
				return cell;
			}
			 
		 });
		
		_queueEntrysListView
			.getSelectionModel().selectedItemProperty().addListener(
					(observable, oldValue, newValue) -> {
						// if newValue == null, methods just clear the widgets if something was displayed before
						_main.showPatientRecord(_patientRecordBorderPane, (newValue == null) ? null : newValue.getPatient());
						showAppointmentInfo(newValue);
					}
			);
		
		showAppointmentInfo(null);
	}
	
	/**
	 * Automatically refresh the queues every {@link #REFRESH_INTERVAL} seconds
	 */
	public void startQueueReloader() {
		if(_timer == null) {
			_timer = new Timer("QueueReloader");
			_timer.schedule(new TimerTask() {
				@Override
				public void run() {
					_logger.trace("Refreshing Queues");
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							refreshQueueOnce();
						}
					});
				}
			}, 0, REFRESH_INTERVAL);
		}
	}
	
	public void refreshQueueOnce() {
		setItemsToQueueBox();
		// setQueueEntriesInList(); is automatically called by setItemsToQueueBox, as that method selects one of the queues and 
		// thus triggering the event that refreshes the EntriesList.
	}
	
	public void stopQueueReloader() {
		if(_timer != null) {
			_timer.cancel();
			_timer = null;
		}
	}

	/**
	 * Clears _queueBox and _nextQueueBox and inserts new queues from ControllerFacade.
	 */
	private void setItemsToQueueBox() {
		QueueRO curQueueQueueBox = _queueBox.getSelectionModel().getSelectedItem();
		QueueRO curQueueNextQueueBox = _nextQueueBox.getSelectionModel().getSelectedItem();
		
		// Clear HashMaps of StringConverter
		_queueBox.setConverter(new QueueStringConverter());
		_nextQueueBox.setConverter(new QueueStringConverter());
		
		// update _queueBox and _nextQueueBox
		List<QueueRO> queues = new LinkedList<>();
		for(at.itb13.oculus.application.queue.QueueController qC : ControllerFacade.getInstance().getAllQueueController()) {
			QueueRO q = qC.getQueue();
			queues.add(q);
			// Update references of old selected queues to the new ones, so it's possible to reselect them in the next step
			if( curQueueQueueBox != null && qC.representsSameQueueByID(curQueueQueueBox)) {
				curQueueQueueBox = q;
			}
			if( curQueueNextQueueBox != null && qC.representsSameQueueByID(curQueueNextQueueBox)) {
				curQueueNextQueueBox = q;
			}
		}
		
		_queueBox.getItems().setAll(queues);
		_nextQueueBox.getItems().setAll(queues);
		
		// if necessary, reselect items that were selected before the update
		if(curQueueQueueBox != null) {
			_queueBox.getSelectionModel().select(curQueueQueueBox);
		}
		if(curQueueNextQueueBox != null) {
			_nextQueueBox.getSelectionModel().select(curQueueNextQueueBox);
		}
	}
	
	@FXML
	private void handleQueueComboBox() {
		setQueueEntriesInList();
	}
	
	/**
	 * Clears _queueEntryList and fills the list with the current queue from ControllerFacade.
	 */
	private void setQueueEntriesInList() {
		QueueEntryRO entrySelected = _queueEntrysListView.getSelectionModel().getSelectedItem();
		QueueRO queueSelected = _queueBox.getSelectionModel().getSelectedItem();
		
		_queueEntryList.clear();	// delete old entries
		if(queueSelected != null) {
			at.itb13.oculus.application.queue.QueueController queueController = ControllerFacade.getInstance().getQueueController(queueSelected);
			queueController.reloadQueue();
			@SuppressWarnings("unchecked")
			List<QueueEntryRO> entries = (List<QueueEntryRO>) queueController.getQueueEntries();
			_queueEntryList.addAll(entries);	// add new entries
			_logger.info("Queue (_queueBox) has been updated");
		
			if(entrySelected != null && queueSelected.contains(entrySelected.getQueueEntryId())) {	// reselect (necessary if updating)
				for(QueueEntryRO entry : entries) {
					if(entry.getQueueEntryId().equals(entrySelected.getQueueEntryId())) {
						_queueEntrysListView.getSelectionModel().select(entry);	// need to select an entry that's contained in entries; not another one!
						break;
					}
				}
			}
		} else {
			_logger.info("No queue selected in _queueBox");
		}
	}
	
	private void showAppointmentInfo(QueueEntryRO entry){
		if (entry != null) {
			_curCalendarEvent = entry.getCalendarEvent();
			_nextQueueBox.setVisible(true);
			_nextQueueBox.getSelectionModel().clearSelection();
			_insertButton.setVisible(true);
			_endExaminationButton.setVisible(true);
			_orLabel.setText("or");
			
			if (entry.getCalendarEvent() != null) {
				_dateTimeLabel.setText(entry.getCalendarEvent().getEventStart().toString());
				_typeLabel.setText(entry.getCalendarEvent().getEventType().getEventTypeName());
				_reasonLabel.setText(entry.getCalendarEvent().getDescription());
			} else {
				_dateTimeLabel.setText("");
				_typeLabel.setText("");
				_reasonLabel.setText("");
			}
		} else {
			_curCalendarEvent = null;
			_nextQueueBox.setVisible(false);
			_insertButton.setVisible(false);
			_endExaminationButton.setVisible(false);
			_orLabel.setText("");
		}
		
	}

	@FXML
	private void handleRelocateQueueButton() {

		QueueRO queueNext = _nextQueueBox.getSelectionModel().getSelectedItem();
		QueueRO queueOld = _queueBox.getSelectionModel().getSelectedItem();
		PatientRO patient = _queueEntrysListView.getSelectionModel().getSelectedItem().getPatient();

		if (queueNext != null && queueOld != null && patient != null) {
			at.itb13.oculus.application.queue.QueueController controllerNext = ControllerFacade.getInstance().getQueueController(queueNext);
			at.itb13.oculus.application.queue.QueueController controllerOld = ControllerFacade.getInstance().getQueueController(queueOld);
			
			if (controllerNext != null && controllerOld != null) {
				if(controllerOld.removeQueueEntry(patient)) {
					try {
						if(controllerNext.pushQueueEntry(patient, _curCalendarEvent)) {
							refreshQueueOnce();
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setContentText("Patient was moved to the selected Queue.");
							alert.showAndWait();
							return;
						}
					} catch (InvalidInputException e) {
						_logger.error(e);	// Won't happen because the patient was just removed an instant ago
					}
				} else {
					_logger.error("Failed to remove patient (" + patient.getPatientId() + ") from queue.");
				}
			} else {
				_logger.error("Could not load QueueController for Queue with doctor '" + queueNext.getDoctor().getDoctorId()
						+ "' and with orthoptist '" + queueNext.getOrthoptist().getOrthoptistId() + "'!");
			}
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Failed to move patient to another queue!");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("No queue selected");
			alert.setContentText("Please choose a Queue before insert.");
			alert.setTitle("No queue selected");
			alert.showAndWait();
		}
	}
	
	/**
	 * Removes an Entry from the queue
	 */
	@FXML
	private void handleEndExamination(){
		at.itb13.oculus.application.queue.QueueController queueController = ControllerFacade.getInstance().getQueueController( _queueBox.getSelectionModel().getSelectedItem() );
		if(queueController.removeQueueEntry(_queueEntrysListView.getSelectionModel().getSelectedItem().getPatient())) {
			refreshQueueOnce();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Examination is closed. Patient is no longer in a Waitinglist.");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Failed to close examination. Patient is still in a Waitinglist.");
			alert.showAndWait();
		}
	}
}
