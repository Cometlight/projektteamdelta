package at.itb13.oculus.presentation.view.calendar;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.application.calendar.CalendarController;
import at.itb13.oculus.application.exceptions.InvalidInputException;
import at.itb13.oculus.domain.interfaces.ICalendar;
import at.itb13.oculus.domain.readonlyinterfaces.CalendarEventRO;
import at.itb13.oculus.presentation.OculusMain;
import at.itb13.oculus.presentation.view.NewAppointmentController;

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
	private static final int TIME_INTERVAL_MINUTES = 15;
	private static final double TIME_COLUMN_WIDTH = 50d;
	private static final int GRIDPANE_NUMBER_OF_COLUMNS = 8;	// Reason: javafx.GridPane does not have an appropriate method

	@FXML
	private VBox _mainAreaVBox;
	@FXML
	private VBox _calendarCheckBoxesVBox;
	@FXML
	private DatePicker _datePicker;	// TODO: Paar Sachen k�nnten wohl vom "alten" Datepicker vom AppointmentsTab �bernommen werden
	@FXML
	private Button _addAppointmentButton;

	private GridPane _gridPaneHeader;
	private ScrollPane _scrollPane;
	private GridPane _gridPaneContent;	// TODO: Aktueller Tag + aktuelle Uhrzeit irgendwie markieren
										// TODO: Automatisch runterscrollen zur aktuellen Uhrzeit
	private List<CalendarCheckBox> _calendarCheckBoxes;

	private List<CalendarEventRO> _calEvents;

	@FXML
	private void initialize() {
		_logger.info("Initializing TabCalendarController ...");
		
		initCheckBoxes();	// Needs to be initialized first of all
		initDatePicker();
		initMainArea();
		
		loadCalendarEvents(LocalDate.now());
		displayAllCalendarEvents();
		
		_logger.info("TabCalendarController has been initialized.");
	}
	
	private void initCheckBoxes() {
		List<ICalendar> calendars = ControllerFacade.getInstance().getNewAppointmentController().getAllCalendars();
		_calendarCheckBoxes = new ArrayList<>(calendars.size());
		for(ICalendar cal : calendars) {
			CalendarCheckBox calCheckBox = new CalendarCheckBox(cal);
			
			calCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					_logger.info("Selected calendars have changed. Updating content of calendar...");
					initGridPaneContent();
					loadCalendarEvents(LocalDate.now());	// TODO: nicht .now, sondern das, was eben wirklich angezeigt werden soll.
					displayAllCalendarEvents();
					_logger.info("Content of calendar has been updated.");
				}
			});
			
			_calendarCheckBoxes.add(calCheckBox);
		}
		_calendarCheckBoxesVBox.getChildren().setAll(_calendarCheckBoxes);
		
		
		// TODO
		// + onClicked {
		//  loadCalendarEvents()
		//  displayAllCalendarEvents()
		// }
	}
	
	private void initDatePicker() {
		_datePicker.setConverter(new StringConverter<LocalDate>() {
			 DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		     @Override 
		     public String toString(LocalDate date) {
		         if (date != null) {
		             return dateFormatter.format(date);
		         } else {
		             return "";
		         }
		     }

		     @Override 
		     public LocalDate fromString(String string) {
		         if (string != null && !string.isEmpty()) {
		             return LocalDate.parse(string, dateFormatter);
		         } else {
		             return null;
		         }
		     }
		});
		
		_datePicker.setValue(LocalDate.now());	// Show today's appointments by default
	}
	
	private void initMainArea() {
		_gridPaneHeader = new GridPane();
		_gridPaneContent = new GridPane();
		_scrollPane = new ScrollPane();
		_scrollPane.setContent(_gridPaneContent);
		
		_mainAreaVBox.getChildren().setAll(_gridPaneHeader, _scrollPane);
		
		initGridPaneHeader();
		initScrollPane();
		initGridPaneContent();
		resizeGridPanes();
	}

	private void initScrollPane() {
		_scrollPane.setFitToWidth(true);	// TODO: wird des �berhaupt gebraucht? --> evtl. l�schen sonst
		_scrollPane.setFitToHeight(true);
	}
	
	private void initGridPaneHeader() {
		_gridPaneHeader.setGridLinesVisible(true); 	// TODO: "for debug purposes only" --> Durch CSS ersetzen
		
		_gridPaneHeader.add(new Text("Time"), 0, 0);
		_gridPaneHeader.add(new WeekDayLabel(DayOfWeek.MONDAY), 1, 0);
		_gridPaneHeader.add(new WeekDayLabel(DayOfWeek.TUESDAY), 2, 0);
		_gridPaneHeader.add(new WeekDayLabel(DayOfWeek.WEDNESDAY), 3, 0);
		_gridPaneHeader.add(new WeekDayLabel(DayOfWeek.THURSDAY), 4, 0);
		_gridPaneHeader.add(new WeekDayLabel(DayOfWeek.FRIDAY), 5, 0);
		_gridPaneHeader.add(new WeekDayLabel(DayOfWeek.SATURDAY), 6, 0);
		_gridPaneHeader.add(new WeekDayLabel(DayOfWeek.SUNDAY), 7, 0);
	}

	private void initGridPaneContent() {
		_gridPaneContent.getChildren().clear();
		_gridPaneContent.setGridLinesVisible(true); 	// TODO: "for debug purposes only" --> Durch CSS ersetzen
		
		// 1st column: Display the time
		LocalTime timeStart = LocalTime.MIN;	// TODO nicht hier hin start + end zeit schreiben, sonst irgwo herholen oder so
		LocalTime timeEnd = LocalTime.MAX.minusMinutes(TIME_INTERVAL_MINUTES);		// also needed in "loadCalendarEvents"
												// vielleicht oben als private static final nochmal hin, damit sp�ter wenn n�tig leicht austauschbar?
		
		long minutesToAdd = TIME_INTERVAL_MINUTES;
		int row = 1;
		for(LocalTime curTime = LocalTime.MIN; curTime.isBefore(timeEnd); curTime = curTime.plusMinutes(TIME_INTERVAL_MINUTES)) {	// TODO: While-loop w�r wohl �bersichtlicher // TODO: nicht LocalTime.MIN sondern siehe wie 5 Zeilen oben
			LocalTimeLabel timeLabel = new LocalTimeLabel(LocalTime.from(curTime));
			_gridPaneContent.add(timeLabel, 0, row);
			++row;
		}
		
		// Insert 1 GridPane into every cell. Each GridPane has so many Columns as CheckBoxes are ticked.
		int rowCount = getRowCount(_gridPaneContent);
		int calendarsToDisplay = getNumberOfSelectedCheckBoxes();
		ColumnConstraints columnConstraints = new ColumnConstraints();
		columnConstraints.setHgrow(Priority.ALWAYS);
		for(int r = 0; r < rowCount; ++r) {
			for(int c = 1; c < GRIDPANE_NUMBER_OF_COLUMNS; ++c) {	// "1" because we can ignore the first column
				GridPane gridPane = new GridPane();
				for(int i = 0; i < calendarsToDisplay; ++i) {
					gridPane.add(new Text("empty"), i, 0);
					gridPane.getColumnConstraints().add(columnConstraints);
					gridPane.setGridLinesVisible(true);	// TODO: "for debug purposes only" --> Durch CSS ersetzen
				}
				_gridPaneContent.add(gridPane, c, r);
			}
		}
	}
	
	private void resizeGridPanes() {	// TODO: Spalten von Header und Content sind nicht sch�n gleich breit. (Eventuell ist der Grund der Scrollbalken des Scrollpanes)
										// siehe auch https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/GridPane.html
		ColumnConstraints firstColCC = new ColumnConstraints(TIME_COLUMN_WIDTH, TIME_COLUMN_WIDTH, TIME_COLUMN_WIDTH);
		_gridPaneHeader.getColumnConstraints().add(firstColCC);
		_gridPaneContent.getColumnConstraints().add(firstColCC);
		
		ColumnConstraints cC = new ColumnConstraints();
		cC.setHgrow(Priority.ALWAYS);
		for(int i = 0; i < GRIDPANE_NUMBER_OF_COLUMNS - 1; ++i) {	// -1 because we already have set the constraint for the first column just above
			_gridPaneHeader.getColumnConstraints().add(cC);
			_gridPaneContent.getColumnConstraints().add(cC);
		}
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
			// unter anderem: zB hat der User gar keine checkboxes angew�hlt
			e.printStackTrace();
		}
	}

	private void displayAllCalendarEvents() {
		for(CalendarEventRO calEv : _calEvents) {
			// TODO: Werte wirklich berechnen (Runden ist n�tig, da in minutesToAdd - Zeitbl�cken (15 minuten, wenn nicht ge�ndert) siehe: initGridPane()
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
		// HBox hbox = _gridPane.getChildren(columnIndex, rowIndex, colSpan, rowSpan)				(siehe z.B. http://stackoverflow.com/questions/20825935/javafx-get-node-by-row-and-column )  (WIRD �FTERS BEN�TIGT -> IN EIGENE FUNKTION?!?
		// hbox.add(calendarEvent) in die richtige Spalte, je nach doctor/orthoptist.
	}
	
	@FXML
	private Boolean handleNewAppointmentButton(){
		/**
		 * evtl. TODO:
		 * - Neu erstellten Termin anzeigen lassen, sollte einer erstellt worden sein (man nicht auf Abbrechen geklickt hat)
		 * - Cancel Button funktioniert noch nicht
		 */
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
	
	// TODO: In seperate Datei auslagern
	private class CalendarCheckBox extends CheckBox {
		private ICalendar _calendar;
		
		private CalendarCheckBox() { }
		
		public CalendarCheckBox(ICalendar calendar, String label) {
			super(label);
			_calendar = calendar;
		}
		
		public CalendarCheckBox(ICalendar calendar) {
			this(calendar, calendar.getTitle());
		}

		public ICalendar getCalendar() {
			return _calendar;
		}

		public void setCalendar(ICalendar calendar) {
			_calendar = calendar;
		}
	}
	
	// TODO: Diese Methode der �bersichtlichkeit halber wo anders hinschieben?
	private int getNumberOfSelectedCheckBoxes() {
		int i = 0;
		for(CalendarCheckBox calCheckBox : _calendarCheckBoxes) {
			if(calCheckBox.isSelected()) {
				++i;
			}
		}
		return i;
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

	// TODO: besser machen? wo anders hin tun? zwischenspeichern stattdessen?
	// siehe http://stackoverflow.com/a/20766735
	private static int getRowCount(GridPane pane) {
		int numRows = pane.getRowConstraints().size();
        for (int i = 0; i < pane.getChildren().size(); i++) {
            Node child = pane.getChildren().get(i);
            if (child.isManaged()) {
                int rowIndex = GridPane.getRowIndex(child);
                int rowEnd = GridPane.getRowIndex(child);
                numRows = Math.max(numRows, (rowEnd != GridPane.REMAINING? rowEnd : rowIndex) + 1);
            }
        }
        return numRows;
	}
}
