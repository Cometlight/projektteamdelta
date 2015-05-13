package at.itb13.oculus.presentation.view;

import at.itb13.oculus.presentation.OculusMain;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;


/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 15.04.2015
 */
public class RootLayoutController {
	@FXML
	private TabPane _tabPaneRoot;
	
	//general Attributs
	private OculusMain _main;
	
	//general Methods
	public void setMain(OculusMain main) {
		_main = main;		        
	}
	 
	 public void setTabPane(TabPane tabPane) {
		 _tabPaneRoot = tabPane;
	 }
	 
	 @FXML
	 private void initialize() {}
	 
	 /**
	  * is called when the tab "Patient" is selceted
	  */
	 @FXML
	 private void tabPatientControl(){
		_main.showPatientTab();
	 }
	 
	 /**
	  * is called when the tab "Appointments" is selceted
	  */
	 @FXML
	 private void tabAppointmentsControl(){	
		 _main.showAppointmentsTab();
	 }
	 /**
	  * is called when the tab "Waitinglist" is selected
	  */
	 @FXML
	 private void tabQueueControl(){					
		 _main.showQueueTab();
	 }
	 @FXML
	 private void tabCalendarControl(){
		 _main.showCalendarTab();
	 }
	 
	 public void setTab(int index) {
		 _tabPaneRoot.getSelectionModel().select(index);
	 }
}
