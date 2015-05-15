package at.itb13.oculus.presentation.view.calendar;

import java.time.LocalDate;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 11.05.2015
 */
public class CalendarDayView implements ICalendarViewState {
	private static final int NUMBER_OF_DAYS = 1;
	private GridPane _gridPaneHeader;
	private LocalDateLabel _gridHeaderDateLabel;
	
	/*
	 * @see at.itb13.oculus.presentation.view.calendar.ICalendarViewState#initGridPaneHeader(javafx.scene.layout.GridPane)
	 */
	@Override
	public void initGridPaneHeader(GridPane header) {
		_gridPaneHeader = header;
		_gridPaneHeader.setGridLinesVisible(true); 	// TODO: "for debug purposes only" --> Durch CSS ersetzen
		
		_gridPaneHeader.add(new Text("Time"), 0, 0);
		_gridHeaderDateLabel = new LocalDateLabel();
		_gridPaneHeader.add(_gridHeaderDateLabel, 1, 0);
	}
	
	@Override
	public void changeHeader(LocalDate date){
	
		_gridPaneHeader.add(new Text("Time"), 0, 0);
		_gridPaneHeader.getChildren().remove(_gridHeaderDateLabel);
		_gridHeaderDateLabel = new LocalDateLabel(date, date.getDayOfWeek().name() + " " + date.toString());
		_gridPaneHeader.add(_gridHeaderDateLabel, 1, 0);
	}

	/*
	 * @see at.itb13.oculus.presentation.view.calendar.ICalendarViewState#getNumberOfDays()
	 */
	@Override
	public int getNumberOfDays() {
		return NUMBER_OF_DAYS;
	}
	
	/*
	 * @see at.itb13.oculus.presentation.view.calendar.ICalendarViewState#onDatePickerSelected(java.time.LocalDate)
	 */
	@Override
	public LocalDate onDatePickerSelected(LocalDate date) {
		return date;
	}
	
	/*
	 * @see at.itb13.oculus.presentation.view.calendar.ICalendarViewState#getStartDate(java.time.LocalDate)
	 */
	@Override
	public LocalDate getStartDate(LocalDate date) {
		return date;
	}
	/*
	 * @see at.itb13.oculus.presentation.view.calendar.ICalendarViewState#nextButtonControl(java.time.LocalDate)
	 */
	@Override
	public LocalDate nextButtonControl(LocalDate date) {
		return date.plusDays(1);
	}
	/*
	 * @see at.itb13.oculus.presentation.view.calendar.ICalendarViewState#previosButtonControl(java.time.LocalDate)
	 */
	@Override
	public LocalDate previousButtonControl(LocalDate date) {
		return date.minusDays(1);
	}

	/*
	 * @see at.itb13.oculus.presentation.view.calendar.ICalendarViewState#getColumnForDate(java.time.LocalDate)
	 */
	@Override
	public int getColumnForDate(LocalDate date) {
		return 1;
	}

}
