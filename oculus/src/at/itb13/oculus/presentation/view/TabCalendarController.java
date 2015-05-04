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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.application.calendar.CalendarController;
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

	private static final Logger _logger = LogManager.getLogger(TabCalendarController.class.getName());
	private static final String CALENDAR_EVENT_FXML = "CalendarEvent.fxml";


	@FXML
	private ScrollPane _scrollPane;
	@FXML
	private DatePicker _datePicker;	// TODO: Paar Sachen könnten wohl vom "alten" Datepicker vom AppointmentsTab übernommen werden
	@FXML
	private Button _addAppointmentButton;

	private OculusMain _main;

	private GridPane _gridPane;	// TODO: Aktueller Tag + aktuelle Uhrzeit irgendwie markieren
								// TODO: Automatisch runterscrollen zur aktuellen Uhrzeit

	private List<CalendarEventRO> _calEvents;

	@FXML
	private void initialize() {
		initScrollPane();
		initGridPane();
		loadCalendarEvents(LocalDate.now());
		displayAllCalendarEvents();
	}

	private void initScrollPane() {
		_scrollPane.setFitToWidth(true);	// TODO: wird des überhaupt gebraucht? --> evtl. löschen sonst
		_scrollPane.setFitToHeight(true);
	}

	private void initGridPane() {
		_gridPane = new GridPane();
		_gridPane.setGridLinesVisible(true); 	// TODO: "for debug purposes only" --> Durch CSS ersetzen
		

		// TODO: Row/Column Sizing ( https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/GridPane.html )
		// 1. Spalte schmal, die andren gleich breit; und zwar entsprechend der Anzahl ausgewählter Filter
		// Wichtig: muss später natürlich möglichst leicht wieder angepasst werden können, wenn
		// Nutzer andere Checkboxes anwählt
		
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
		LocalTime timeStart = LocalTime.MIN;	// TODO nicht hier hin start + end zeit schreiben, sonst irgwo herholen oder so
		LocalTime timeEnd = LocalTime.MAX.minusMinutes(15);		// also needed in "loadCalendarEvents"
												// vielleicht oben als private static final nochmal hin, damit später wenn nötig leicht austauschbar?
		
		long minutesToAdd = 15;	// TODO: In final static variable
		int row = 1;
		for(LocalTime curTime = LocalTime.MIN; curTime.isBefore(timeEnd); curTime = curTime.plusMinutes(minutesToAdd)) {	// TODO: While-loop wär wohl übersichtlicher // TODO: nicht LocalTime.MIN sondern siehe wie 5 Zeilen oben
			LocalTimeLabel timeLabel = new LocalTimeLabel(LocalTime.from(curTime));
			_gridPane.add(timeLabel, 0, row);
			++row;
		}
		
		
		_scrollPane.setContent(_gridPane);
		
		// TODO Pseudo-Code:
//		for(int row = 0; row < _gridPane.getNumberOfRows(); ++row) {
//			for(int col = 0; col < _gridPane.getNumberOfColumns(); ++col) {
//				HBox hBox = new HBox();
//				hBox.setNumberOfCols(so viel wie Filter selektiert sind)
//				_gridPane.getChildren(columnIndex, rowIndex, colSpan, rowSpan).add(hBox);
//			}
//		}
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
	private void loadCalendarEvents(LocalDate dayStart) {
		LocalTime timeStart = LocalTime.MIN;	// TODO: see initGridPane()
		LocalTime timeEnd = LocalTime.MAX;
		// TODO: set dayStart to a Monday, if it's not a Monday already
		LocalDate dayEnd = dayStart.plusWeeks(1);
		
		try {	// TODO set ids according to filters
			_calEvents = (List<CalendarEventRO>) ControllerFacade.getInstance().getCalendarController(   null   ,    2    ).getCalendarEventsInTimespan(LocalDateTime.of(dayStart, timeStart), LocalDateTime.of(dayEnd, timeEnd));
			// TODO: NewAppointmentControllerInterface verwenden statt den CalendarController!
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			// Fehlerbehandlung
			// unter anderem: zB hat der User gar keine checkboxes angewählt
			e.printStackTrace();
		}
	}

	private void displayAllCalendarEvents() {
		for(CalendarEventRO calEv : _calEvents) {
			// TODO: Werte wirklich berechnen (Runden ist nötig, da in minutesToAdd - Zeitblöcken (15 minuten, wenn nicht geändert) siehe: initGridPane()
			int columnIndex = 3;
			int rowIndex = 3;
			int colSpan = 3;
			int rowSpan = 3;
			displayCalendarEvent(calEv, columnIndex, rowIndex, colSpan, rowSpan);
		}
	}
	
	private void displayCalendarEvent(CalendarEventRO calendarEvent, int columnIndex, int rowIndex, int colSpan, int rowSpan) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(CALENDAR_EVENT_FXML));
		AnchorPane calEvPane = null;
		CalendarEventController calEvCol = null;
		try {
			 calEvPane = fxmlLoader.load();
			 calEvCol = fxmlLoader.getController();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Pseudo-Code
		// HBox hbox = _gridPane.getChildren(columnIndex, rowIndex, colSpan, rowSpan)				(siehe z.B. http://stackoverflow.com/questions/20825935/javafx-get-node-by-row-and-column )  (WIRD ÖFTERS BENÖTIGT -> IN EIGENE FUNKTION?!?
		// hbox.add(calendarEvent) in die richtige Spalte, je nach doctor/orthoptist.
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

	private void onDatePickerDateSelected() {
		// loadCalendarEvents(_datePicker.date())
		// displayAllCalendarEvents()
	}
	
	@FXML
	private void onButtonNewAppointmentClick() {
		// open new window
		// Neu erstellten Termin anzeigen lassen, sollte einer erstellt worden sein (man nicht auf Abbrechen geklickt hat)
	}
	
	private void initCheckBoxes() {
		// create 1 checkbox for every filter
		// + onClicked {
		//  loadCalendarEvents()
		//  displayAllCalendarEvents()
		// }
	}
	
	@FXML
	private void onButtonNextWeekClick() {
		// datepicker -> next week.... etc.
	}
	
	@FXML
	private void onButtonPreviousWeekClick() {
		// datepicker -> previous week.... etc.
	}
	
	/* TODO irgendwann vielleicht...
	 * private void reload() {
	 *  all 60 seconds {
	 *   onDatePickerDateSelected();
	 *  }
	 * }
	 */

}
