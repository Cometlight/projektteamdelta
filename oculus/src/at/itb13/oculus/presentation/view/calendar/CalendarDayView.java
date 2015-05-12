package at.itb13.oculus.presentation.view.calendar;

import java.time.DayOfWeek;
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
	/*
	 * @see at.itb13.oculus.presentation.view.calendar.ICalendarViewState#initGridPaneHeader(javafx.scene.layout.GridPane)
	 */
	@Override
	public void initGridPaneHeader(GridPane header) {
		_gridPaneHeader = header;
		_gridPaneHeader.setGridLinesVisible(true); 	// TODO: "for debug purposes only" --> Durch CSS ersetzen
		
		_gridPaneHeader.add(new Text("Time"), 0, 0);
		_gridPaneHeader.add(new Text(DayOfWeek.MONDAY.name()), 1, 0);
	}
	@Override
	public void changeHeader(LocalDate date){
		_gridPaneHeader.add(new Text("Time"), 0, 0);
		_gridPaneHeader.add(new Text(date.getDayOfWeek().name() + " " + date.toString()), 1, 0);
	}

	/*
	 * @see at.itb13.oculus.presentation.view.calendar.ICalendarViewState#getNumberOfDays()
	 */
	@Override
	public int getNumberOfDays() {
		return NUMBER_OF_DAYS;
	}

}
