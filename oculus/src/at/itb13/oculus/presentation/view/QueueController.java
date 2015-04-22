package at.itb13.oculus.presentation.view;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.domain.QueueEntry;
import at.itb13.oculus.domain.readonlyinterfaces.CalendarEventRO;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import at.itb13.oculus.domain.readonlyinterfaces.QueueEntryRO;
import at.itb13.oculus.domain.readonlyinterfaces.QueueRO;
import at.itb13.oculus.presentation.OculusMain;
import at.itb13.oculus.presentation.util.QueueSringConverter;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 15.04.2015
 */
public class QueueController {
	
	@FXML
	private ListView<QueueEntryRO> _queueEntrysListView;
	@FXML
	private ComboBox<QueueRO> _queueBox;
	private QueueRO _queue;
	
	private ObservableList<QueueEntryRO> _queueEntryList = FXCollections.observableArrayList();
	
	@FXML
	private Label _dateTimeLabel;
	@FXML
	private Label _typeLabel;
	@FXML
	private Label _reasonLabel;
	@FXML
	private BorderPane _patientRecordBorderPane;
	//general Attributes
	private OculusMain _main;
			
	//general Methods
	public void setMain(OculusMain main) {
		_main = main;		        
	}
			 
	@FXML
	private void initialize() {
		
		setItemsToQueueBox();
		
		_queueEntrysListView.setItems(_queueEntryList);
		
		 _queueEntrysListView.setCellFactory(new Callback<ListView<QueueEntryRO>, ListCell<QueueEntryRO>>(){

			@Override
			public ListCell<QueueEntryRO> call(ListView<QueueEntryRO> param) {
				ListCell<QueueEntryRO> cell = new ListCell<QueueEntryRO>(){
					 
                    @Override
                    protected void updateItem(QueueEntryRO t, boolean bln) {
                        super.updateItem(t, bln);
                        if(t != null){
                        	setText(t.getPatient().getFirstName() + " "+ t.getPatient().getLastName());
                        }else{
                        	setText("");
                        }
                    }
				};
				return cell;
			}
			 
		 });
		showAppointmentInfo(null);	 
		_queueEntrysListView
		.getSelectionModel()
		.selectedItemProperty()
		.addListener(
				(observable, oldValue, newValue) -> _main
						.showPatientRecord(_patientRecordBorderPane,
								newValue.getPatient()));
		_queueEntrysListView
		.getSelectionModel()
		.selectedItemProperty()
		.addListener(
				(observable, oldValue, newValue) -> showAppointmentInfo(newValue));
		
		
	}

	private void clearQueue() {
		_queueEntryList.clear();
		
	}

	private void setItemsToQueueBox() {

		_queueBox.setConverter(new QueueSringConverter());
		List<at.itb13.oculus.application.queue.QueueController> queController = ControllerFacade.getInstance().getAllQueueController();
		for (at.itb13.oculus.application.queue.QueueController controller : queController) {		
			_queueBox.getItems().add(controller.getQueue());
		}
	}
	
	@FXML
	private void handleQueueComboBox() {
		_queue = _queueBox.getSelectionModel().getSelectedItem();
		setQueueEntriesInList();
	}
	
	private void setQueueEntriesInList(){
		
		clearQueue();
		at.itb13.oculus.application.queue.QueueController controller = ControllerFacade.getInstance().getQueueController(_queue);
		List<QueueEntryRO> entries = (List<QueueEntryRO>) controller.getQueueEntries();
		
		for(QueueEntryRO entry : entries){
			_queueEntryList.add(entry);
		}
	}
	
	private void showAppointmentInfo(QueueEntryRO entry){
		if((entry!= null)&&(entry.getCalendarEvent() != null)){
			_dateTimeLabel.setText(entry.getCalendarEvent().getEventStart().toString());
			_typeLabel.setText(entry.getCalendarEvent().getEventtype().getEventTypeName());
			_reasonLabel.setText(entry.getCalendarEvent().getDescription());
			
		}else{
			_dateTimeLabel.setText("");
			_typeLabel.setText("");
			_reasonLabel.setText("");
		}
	}
	
	
}
