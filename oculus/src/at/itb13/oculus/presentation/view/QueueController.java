package at.itb13.oculus.presentation.view;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
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
	private ListView<QueueEntryRO> _queueEntrys;
	@FXML
	private ComboBox<QueueRO> _queueBox;
	private QueueRO _queue;
	
	private ObservableList<QueueEntryRO> _patientList = FXCollections.observableArrayList();
	//general Attributes
	private OculusMain _main;
			
	//general Methods
	public void setMain(OculusMain main) {
		_main = main;		        
	}
			 
	@FXML
	private void initialize() {
		setItemsToQueueBox();
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
	}
	
	private void setCalendarEventInList(){
		
		at.itb13.oculus.application.queue.QueueController controller = ControllerFacade.getInstance().getQueueController(_queue);
		List<? extends QueueEntryRO> entries = controller.getQueueEntries();
				
			
	}
}
