package at.itb13.oculus.presentation.view;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.application.exceptions.InvalidInputException;
import at.itb13.oculus.domain.readonlyinterfaces.CalendarEventRO;
import at.itb13.oculus.presentation.OculusMain;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger, Daniel Scheffknecht
 * @date May 1, 2015
 */
public class TabCalendarController {
	private static final Logger _logger = LogManager.getLogger(NewAppointmentController.class.getName());

	@FXML
	private ScrollPane _scrollPane;
	@FXML
	private DatePicker _datePicker;	// TODO: Paar Sachen könnten wohl vom "alten" Datepicker vom AppointmentsTab übernommen werden

	private GridPane _gridPane;
	
	@FXML
	private Button _addAppointmentButton;

	private OculusMain _main;
	private List<CalendarEventRO> _calEvents;

	public void setMain(OculusMain main) {
		_main = main;
	}

	@FXML
	private void initialize() {
		// for(CalendarEventRO cal : _calEvents){
		// //create new CalendarEvent with dinamic size and set them to right
		// position
		// }
		initScrollPane();
		initGridPane();
		loadCalendarEvents();
		displayCalendarEvents();
	}

	private void initScrollPane() {
//		// Add listener to set ScrollPane FitToWidth/FitToHeight when viewport bounds changes
//		_scrollPane.viewportBoundsProperty().addListener(new ChangeListener() {
//			public void changed(ObservableValue<? extends Bounds> arg0, Bounds arg1, Bounds arg2) {
//			      Node content = _scrollPane.getContent();
//			      _scrollPane.setFitToWidth(content.prefWidth(-1)<arg2.getWidth());
//			      _scrollPane.setFitToHeight(content.prefHeight(-1)<arg2.getHeight());
//			    }
//
//			@Override
//			public void changed(ObservableValue observable, Object oldValue,
//					Object newValue) {
//				// TODO Auto-generated method stub DELETE????
//				
//			}
//		});
//		_scrollPane.setPrefSize(120, 120);
		_scrollPane.setFitToWidth(true);
		_scrollPane.setFitToHeight(true);
	}

	private void initGridPane() {
		_gridPane = new GridPane();
		_gridPane.setGridLinesVisible(true); 	// TODO: "for debug purposes only" --> Durch CSS ersetzen

		// Column header
		_gridPane.add(new Text("Time"), 0, 0);
		_gridPane.add(new WeekDayLabel(DayOfWeek.MONDAY), 1, 0);
		_gridPane.add(new WeekDayLabel(DayOfWeek.TUESDAY), 2, 0);
		_gridPane.add(new WeekDayLabel(DayOfWeek.WEDNESDAY), 3, 0);
		_gridPane.add(new WeekDayLabel(DayOfWeek.THURSDAY), 4, 0);
		_gridPane.add(new WeekDayLabel(DayOfWeek.FRIDAY), 5, 0);
		_gridPane.add(new WeekDayLabel(DayOfWeek.SATURDAY), 6, 0);
		_gridPane.add(new WeekDayLabel(DayOfWeek.SUNDAY), 7, 0);

		// Row header
		LocalTime timeStart = LocalTime.of(6, 0);	// TODO nicht hier hin start + end zeit schreiben, sonst irgwo herholen oder so
		LocalTime timeEnd = LocalTime.of(18, 0);	// also needed in "loadCalendarEvents"
		
		long minutesToAdd = 15;
		int row = 1;
		for(LocalTime curTime = LocalTime.of(6, 0); curTime.isBefore(timeEnd); curTime = curTime.plusMinutes(minutesToAdd)) {	// TODO: While-loop wär wohl übersichtlicher 
			LocalTimeLabel timeLabel = new LocalTimeLabel(LocalTime.from(curTime));
			_gridPane.add(timeLabel, 0, row);
			++row;
		}
		
		
		_scrollPane.setContent(_gridPane);
	}
	
	// TODO: Move these two classes to seperate files. --> Maybe new package "calendar"?
	private class WeekDayLabel extends Label {
		private DayOfWeek _dayOfWeek;	// Instead use something like here: https://docs.google.com/document/d/1Qr0auVYovnh4gi_G8TS2V43unr2h46GmwfrjTYBwwzo/edit#
										// and don't get it from the domain layer
		public WeekDayLabel(DayOfWeek dayOfWeek) {
			this(dayOfWeek, dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()));
		}
		
		public WeekDayLabel(DayOfWeek dayOfWeek, String text) {
			super(text);
			_dayOfWeek = dayOfWeek;
		}

		public DayOfWeek getDayOfWeek() {
			return _dayOfWeek;
		}

		public void setDayOfWeek(DayOfWeek dayOfWeek) {
			_dayOfWeek = dayOfWeek;
		}
	}
	
	private class LocalTimeLabel extends Label {
		private LocalTime _localTime;
		
		public LocalTimeLabel(LocalTime localTime) {
			this(localTime, localTime.toString());
		}
		
		public LocalTimeLabel(LocalTime localTime, String text) {
			super(text);
			_localTime = localTime;
			this.getStyleClass().add("LocalTimeLabel");
		}

		public LocalTime getLocalTime() {
			return _localTime;
		}

		public void setLocalTime(LocalTime localTime) {
			_localTime = localTime;
		}
	}
	
	@SuppressWarnings("unchecked")
	private void loadCalendarEvents() {
		LocalTime timeStart = LocalTime.of(6, 0);	// TODO: see initGridPane()
		LocalTime timeEnd = LocalTime.of(18, 0);
		LocalDate dayStart = LocalDate.now().minusWeeks(2);	// TODO: get date from datepicker
		LocalDate dayEnd = dayStart.plusWeeks(1);
		
		try {	// TODO set ids according to filters
			_calEvents = (List<CalendarEventRO>) ControllerFacade.getInstance().getCalendarController(   null   ,    2    ).getCalendarEventsInTimespan(LocalDateTime.of(dayStart, timeStart), LocalDateTime.of(dayEnd, timeEnd));
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void displayCalendarEvents() {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CalendarEvent.fxml"));
//		fxmlLoader.setRoot(_gridPane);
//		fxmlLoader.setController(new CalendarEventController());
		AnchorPane calEvPane = null;
		CalendarEventController calEvCol = null;
		try {
			 calEvPane = fxmlLoader.load();
			 calEvCol = fxmlLoader.getController();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		calEvCol.setMain(_main);	// TODO: needed?
		calEvCol.setCalEvent(_calEvents.get(0));
		_gridPane.add(calEvPane, 3, 3, 1, 3);
		
//		for(CalendarEventRO calEv : _calEvents) {
//			
//		}
//		
//		for(int col = 1; col < 7; ++col) {
//			for(int row = 1; row < 20; ++row) {	// TODO: change 20 to the real number... somehow
//				
//				
//			}
//		}
	}
	
	@FXML
	private Boolean handleNewAppointmentButton(){
		try {
		
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(OculusMain.class
					.getResource("view/NewAppointmentDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("New Appointment");
			dialogStage.setTitle("Edit Patient");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			//dialogStage.initOwner(_primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			NewAppointmentController controller = loader.getController();
			controller.setDialogStage(dialogStage);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			_logger.info("showNewAppointmentDialog successful");
			return controller.isOkClicked();
		} catch (IOException ex) {
			_logger.error("showNewAppointmentDialog failed", ex);
			return false;
		}
	}
}