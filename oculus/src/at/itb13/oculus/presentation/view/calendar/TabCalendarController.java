package at.itb13.oculus.presentation.view.calendar;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.application.calendar.CalendarController;
import at.itb13.oculus.application.exceptions.InvalidInputException;
import at.itb13.oculus.domain.readonlyinterfaces.CalendarEventRO;
import at.itb13.oculus.presentation.OculusMain;
import at.itb13.teamD.domain.interfaces.ICalendar;
import at.itb13.teamD.domain.interfaces.ICalendarEvent;
import at.itb13.teamD.domain.interfaces.IPatient;

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
	private static final double TIME_COLUMN_WIDTH = 5d;	// percentage
	private static final double HEADER_MARGIN_RIGHT = 10d;
	private static final double CONTENT_ROW_HEIGHT = 20d;

	private ICalendarViewState _state;
	@FXML
	private VBox _mainAreaVBox;
	@FXML
	private VBox _calendarCheckBoxesVBox;
	@FXML
	private DatePicker _datePicker;
	@FXML
	private Button _addAppointmentButton;
	@FXML
	private TextField _weekNumberTextField;
	@FXML
	private Button _dayViewButton;
	@FXML
	private Button _weekViewButton;
	
	

	private GridPane _gridPaneHeader;
	private ScrollPane _scrollPane;
	private GridPane _gridPaneContent;	// TODO: Aktueller Tag + aktuelle Uhrzeit irgendwie markieren
										// TODO: Automatisch runterscrollen zur aktuellen Uhrzeit
	private List<CalendarCheckBox> _calendarCheckBoxes;

	private List<ICalendarEvent> _calEvents;
	
	private Map<Integer, Color> _calendarColorMap;	// Key = CalendarID
	
	private ColorGenerator _colorGenerator;

	@FXML
	private void initialize() {
		_logger.info("Initializing TabCalendarController ...");
		
		_state = new CalendarWeekView();
		
		_colorGenerator = new ColorGenerator();
		_calendarColorMap = new HashMap<>();
		
		initCheckBoxes();	// Needs to be initialized first of all
		initDatePicker();
		_weekNumberTextField.setText(getWeekNumber(_datePicker.getValue()).toString());
		
		initMainArea();
		
		loadCalendarEvents(LocalDate.now().minusWeeks(1), _state.getNumberOfDays());
		displayAllCalendarEvents();
		
		scrollToCurrentTime();	// TODO: Wo anders hinschieben?
		markCurrentTime();	// TODO: alle ~15 Minuten oder so autom. aufrufen
		
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
					onDatePickerDateSelected();	// Do the same thing as if a date was selected in the DatePicker.
					displayAllCalendarEvents();
					_logger.info("Content of calendar has been updated.");
				}
			});
			
			Color color = _colorGenerator.getNextColor();
			_calendarColorMap.put(cal.getCalendarId(), color);
			calCheckBox.setStyle("-fx-background-color: " + ColorGenerator.colorToString(color));
			
			_calendarCheckBoxes.add(calCheckBox);
		}
		_calendarCheckBoxesVBox.getChildren().setAll(_calendarCheckBoxes);
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
		VBox.setMargin(_gridPaneHeader, new Insets(0d, HEADER_MARGIN_RIGHT, 0d, 0d));
		
		_state.initGridPaneHeader(_gridPaneHeader);
		_state.changeHeader(_datePicker.getValue());
		initScrollPane();
		initGridPaneContent();
		resizeGridPanes();
		
		_gridPaneContent.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				onGridPaneContentMouseClick(event);
			}
		});
	}
	
	private void onGridPaneContentMouseClick(MouseEvent event) {
		LocalTime time;
		LocalDate date;
		
		System.out.println(event.getX() + " - " + event.getY());	// TODO: delete
		
		int rowIndex = (int) (event.getY() / CONTENT_ROW_HEIGHT);
		System.out.println(rowIndex);
		LocalTimeLabel localTimeLabel = (LocalTimeLabel) getNodeByRowColumnIndex(rowIndex, 0, _gridPaneContent);
		time = localTimeLabel.getLocalTime();

		double colWidthTime = _gridPaneContent.getWidth() * TIME_COLUMN_WIDTH / 100d;
		double colWidthDays = (_gridPaneContent.getWidth() - colWidthTime) / _state.getNumberOfDays();
		double xMinusColWidthTime = event.getX() - colWidthTime;
		
		if(xMinusColWidthTime <= 0) {
			return;	// do nothing, if not clicked in the column of a day
		}
		
		int colIndex = ( (int) (xMinusColWidthTime / colWidthDays) ) + 1;	// +1 because the first column was already subtracted
		
		LocalDateLabel localDateLabel = (LocalDateLabel) getNodeByColumnIndex(colIndex, _gridPaneHeader);
		date = localDateLabel.getLocalDate();
		
		System.out.println("Time: " + time + ", Date: " + date);	// TODO: Methode aufrufen und date + time übergeben
	}

	private void initScrollPane() {
		_scrollPane.setFitToWidth(true);
		_scrollPane.setFitToHeight(true);
	}
	
	
	private void initGridPaneContent() {
		_gridPaneContent.getChildren().clear();
		_gridPaneContent.setGridLinesVisible(true); 	// TODO: "for debug purposes only" --> Durch CSS ersetzen
		
		// 1st column: Display the time
		LocalTime timeStart = LocalTime.MIN;
		LocalTime timeEnd = LocalTime.MAX.minusMinutes(TIME_INTERVAL_MINUTES);
		RowConstraints rowConstraint = new RowConstraints(CONTENT_ROW_HEIGHT);
		
		int row = 0;
		for(LocalTime curTime = timeStart; curTime.isBefore(timeEnd); curTime = curTime.plusMinutes(TIME_INTERVAL_MINUTES)) {
			LocalTimeLabel timeLabel = new LocalTimeLabel(LocalTime.from(curTime));
			_gridPaneContent.add(timeLabel, 0, row);
			GridPane.setColumnIndex(timeLabel, 0);
			GridPane.setRowIndex(timeLabel, row);
			_gridPaneContent.getRowConstraints().add(rowConstraint);
			++row;
		}
	}
	
	private void clearCalEventsFromGridPaneContent() {
		if(_gridPaneContent != null && _gridPaneContent.getChildren() != null) {
			Iterator<Node> it = _gridPaneContent.getChildren().iterator();
			while(it.hasNext()) {
				Node node = it.next();
				Integer columnIndex = GridPane.getColumnIndex(node);
				if(columnIndex != null && columnIndex >= 1) {
					it.remove();
				}
			}
		}
	}
	
	private void resizeGridPanes() {
		ColumnConstraints firstColCC = new ColumnConstraints();
		firstColCC.setPercentWidth(TIME_COLUMN_WIDTH);
		_gridPaneHeader.getColumnConstraints().add(firstColCC);
		_gridPaneContent.getColumnConstraints().add(firstColCC);
		
		ColumnConstraints cC = new ColumnConstraints();
		cC.setPercentWidth( (100d - TIME_COLUMN_WIDTH) / (double)(_state.getNumberOfDays()));
		for(int i = 0; i < _state.getNumberOfDays(); ++i) {
			_gridPaneHeader.getColumnConstraints().add(cC);
			_gridPaneContent.getColumnConstraints().add(cC);
		}
	}
	
	private void loadCalendarEvents(LocalDate dayStart, int numberOfDays) {
		LocalTime timeStart = LocalTime.MIN;
		LocalTime timeEnd = LocalTime.MAX;
		LocalDate dayEnd = dayStart.plusDays(numberOfDays - 1);	// Not using -1 would result in the display of the appointments of for example two Mondays
		
		_logger.info("Displaying appointments from " + dayStart + " (" + timeStart + ") to " + dayEnd + " (" + timeEnd + ")");
		
		try {
			_calEvents = new LinkedList<>();
			for(CalendarCheckBox calCheckBox : _calendarCheckBoxes) {
				if(calCheckBox.isSelected()) {
					List<ICalendarEvent> list = ControllerFacade.getInstance().getNewAppointmentController().getCalendarEventsInTimespan(
							calCheckBox.getCalendar(), 
							LocalDateTime.of(dayStart, timeStart), 
							LocalDateTime.of(dayEnd, timeEnd));
					_calEvents.addAll(list);
				}
			}
			_logger.info(_calEvents.size() + " appointments have been found.");
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void displayAllCalendarEvents() {
		clearCalEventsFromGridPaneContent();
		for(ICalendarEvent calEv : _calEvents) {
			int columnIndex = calEv.getEventStart().getDayOfWeek().getValue();
			int rowIndex = calEv.getEventStart().getHour() * 4 + calEv.getEventStart().getMinute() / TIME_INTERVAL_MINUTES;
			int colSpan = 1;
			int rowSpan = ( (calEv.getEventEnd().getHour() * 60 + calEv.getEventEnd().getMinute()) - (calEv.getEventStart().getHour() * 60 + calEv.getEventStart().getMinute()) ) / TIME_INTERVAL_MINUTES;
			displayCalendarEvent(calEv, columnIndex, rowIndex, colSpan, rowSpan);
		}
	}
	
	private void displayCalendarEvent(ICalendarEvent calendarEvent, int columnIndex, int rowIndex, int colSpan, int rowSpan) {
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
		
		double height = rowSpan * CONTENT_ROW_HEIGHT;
		calEvPane.setMinHeight(height);
		calEvPane.setPrefHeight(height);
		calEvPane.setMaxHeight(height);
		
		GridPane gP = (GridPane) getNodeByRowColumnIndex(rowIndex, columnIndex, _gridPaneContent);
		if(gP == null) {
			// Add a new GridPane filled with CalendarEventFillerNodes, which will be replaced by actual CalendarEvents
			gP = new GridPane();
			ColumnConstraints columnConstraint = new ColumnConstraints();
			columnConstraint.setPercentWidth(100d/getNumberOfSelectedCheckBoxes());
			int fillerNodeColumnNumber = 0;
			for(CalendarCheckBox calCheckBox : _calendarCheckBoxes) {
				if(calCheckBox.isSelected()) {
					CalendarEventFillerNode fillerNode = new CalendarEventFillerNode(calCheckBox.getCalendar());
					gP.add(fillerNode, fillerNodeColumnNumber++, 0);
					GridPane.setHgrow(fillerNode, Priority.ALWAYS);
					gP.getColumnConstraints().add(columnConstraint);
				}
			}
			_gridPaneContent.add(gP, columnIndex, rowIndex, colSpan, rowSpan);
		}
		
		if(getRowCount(gP) < rowSpan) {
			_gridPaneContent.getChildren().remove(gP);
			_gridPaneContent.add(gP, columnIndex, rowIndex, colSpan, rowSpan);
		}
		
		// Replace the CalendarEventFillerNode which represents the same calendar as calendarEvent's calendar by calEvPane.
		ListIterator<Node> it = gP.getChildren().listIterator();
		while(it.hasNext()) {
			Node node = it.next();
			if(node instanceof CalendarEventFillerNode 
					&& ((CalendarEventFillerNode)node).getCalendar().getCalendarId().equals(calendarEvent.getCalendar().getCalendarId())) {
				int indexColumn = GridPane.getColumnIndex(node);
				int indexRow = GridPane.getRowIndex(node);
				it.remove();
				gP.add(calEvPane, indexColumn, indexRow);
				break;
			}
		}
		
		// Set color of appointment according to its calendar
		calEvPane.setStyle("-fx-background-color: " + ColorGenerator.colorToString(_calendarColorMap.get(calendarEvent.getCalendar().getCalendarId())) + "; "
				+ "-fx-border-color: black; "
				+ "-fx-border-width: 1;");
		
		System.out.println(rowIndex + ", " + columnIndex + " | " + rowSpan + ", " + colSpan);	// TODO: zur Größe des CalendarEvent.fxml's: http://stackoverflow.com/questions/16242398/why-wont-the-children-in-my-javafx-hbox-grow-scenebuilder u.a.
		
		calEvCol.setCalEvent(calendarEvent);
	}

	public Node getNodeByRowColumnIndex(final int row, final int column, GridPane parentGridPane) {
        Node result = null;
        ObservableList<Node> childrens = parentGridPane.getChildren();
        for(Node node : childrens) {
            if( GridPane.getRowIndex(node) != null 
            		&& GridPane.getRowIndex(node) == row 
            		&& GridPane.getColumnIndex(node) != null 
            		&& GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return result;
    }
	
	public Node getNodeByColumnIndex(final int column, GridPane parentGridPane) {
        Node result = null;
        ObservableList<Node> childrens = parentGridPane.getChildren();
        for(Node node : childrens) {
            if(GridPane.getColumnIndex(node) != null 
            		&& GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return result;
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
					.getResource("view/calendar/NewAppointmentDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("New Appointment");
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

	@FXML
	private void onDatePickerDateSelected() {
		LocalDate date = _datePicker.getValue();
		
		_weekNumberTextField.setText(getWeekNumber(date).toString());
		
		date = _state.onDatePickerSelected(date);
		_state.changeHeader(date);
		
		loadCalendarEvents(_state.getStartDate(date), _state.getNumberOfDays());
		displayAllCalendarEvents();
	}
	
	@FXML
	private void todayButtonControl(){
		_datePicker.setValue(LocalDate.now());
		onDatePickerDateSelected();
		scrollToCurrentTime();
		markCurrentTime();
	}
	
	private Integer getWeekNumber(LocalDate date) {
		WeekFields weekFields = WeekFields.of(Locale.getDefault());
		return date.get(weekFields.weekOfWeekBasedYear());
	}
	
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
	private void onButtonNext() {
		_datePicker.setValue(_state.nextButtonControl(_datePicker.getValue()));
	}
	
	@FXML
	private void onButtonPrevious() {
		_datePicker.setValue(_state.previosButtonControl(_datePicker.getValue()));
	}
	
	@FXML
	private void onTextFieldWeekNumberAction() {
		String text = _weekNumberTextField.getText();
		
		if(text.matches("^\\d{1,2}$")) {
			Integer weekNumber = Integer.valueOf(text);
			if(weekNumber > 0 && weekNumber <= 52) {
				
				LocalDate date = LocalDate.now().withDayOfYear(1);	// 1st January, current year
				date = date.plusWeeks(weekNumber-1);
				
				_datePicker.setValue(date);
						
			} else {
				// TODO: not a valid week number
			}
		} else {
			// TODO: not a valid number
		}
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
	
	private void scrollToCurrentTime() {
		int rowCount = getRowCount(_gridPaneContent);
		rowCount = 100 / rowCount; // 100/96 => about 1.05
		
		double hourValue = LocalTime.now().getHour(); //if for example 5AM => hourValue = 5
		double vertPos= ((hourValue * 4 * 1.5) * rowCount ) / 100; // multiplier 4 because hours are divided by four. 
		_scrollPane.setVvalue(vertPos); //sets the value in percent! 
	}
	
	private void markCurrentTime() {
		for(Node node : _gridPaneContent.getChildren()) {
			Integer colIndex = GridPane.getColumnIndex(node);
			if(colIndex != null && colIndex.equals(0) && ((LocalTimeLabel)node).getLocalTime().getHour() == LocalTime.now().getHour()) {
				node.setStyle("-fx-background-color: pink");
			}
		}
	}
	
	@FXML
	private void DayViewButtonControl(){
		_state = new CalendarDayView();
		_dayViewButton.setDisable(true);
		_weekViewButton.setDisable(false);
		initMainArea();
	//	loadCalendarEvents(, _state.getNumberOfDays());
		displayAllCalendarEvents();
		_state.changeHeader(_datePicker.getValue());
		scrollToCurrentTime();
		markCurrentTime();
	}
	
	@FXML
	private void WeekViewButtonControl(){
		_state = new CalendarWeekView();
		_dayViewButton.setDisable(false);
		_weekViewButton.setDisable(true);
		initMainArea();
		displayAllCalendarEvents();
		_state.changeHeader(_datePicker.getValue());		//TODO: MONDAY
		scrollToCurrentTime();
		markCurrentTime();
	}
}
