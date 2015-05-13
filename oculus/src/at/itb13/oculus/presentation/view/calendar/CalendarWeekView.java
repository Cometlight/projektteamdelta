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
public class CalendarWeekView implements ICalendarViewState{
	
	private static final int NUMBER_OF_DAYS = 7;

	/*
	 * @see at.itb13.oculus.presentation.view.calendar.ICalendarViewState#initGridPaneHeader()
	 */
	@Override
	public void initGridPaneHeader(GridPane header) {
		header.setGridLinesVisible(true); 	// TODO: "for debug purposes only" --> Durch CSS ersetzen
		
		header.add(new Text("Time"), 0, 0);
		header.add(new Text(DayOfWeek.MONDAY.name()), 1, 0);
		header.add(new Text(DayOfWeek.TUESDAY.name()), 2, 0);
		header.add(new Text(DayOfWeek.WEDNESDAY.name()), 3, 0);
		header.add(new Text(DayOfWeek.THURSDAY.name()), 4, 0);
		header.add(new Text(DayOfWeek.FRIDAY.name()), 5, 0);
		header.add(new Text(DayOfWeek.SATURDAY.name()), 6, 0);
		header.add(new Text(DayOfWeek.SUNDAY.name()), 7, 0);
		
	}


	/*
	 * @see at.itb13.oculus.presentation.view.calendar.ICalendarViewState#getNumberOfColumns()
	 */
	@Override
	public int getNumberOfDays() {
		return NUMBER_OF_DAYS;
	}


	/*
	 * @see at.itb13.oculus.presentation.view.calendar.ICalendarViewState#changeHeader(java.time.LocalDate)
	 */
	@Override
	public void changeHeader(LocalDate date) {
		// TODO Auto-generated method stub
		
	}


	/*
	 * @see at.itb13.oculus.presentation.view.calendar.ICalendarViewState#onDatePickerSelected(java.time.LocalDate)
	 */
	@Override
	public LocalDate onDatePickerSelected(LocalDate date) {
		// A monday should be provided to loadCalendareEvents() to display a full week
		while(!date.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
				date = date.minusDays(1);
		}
		return date;
	}

	

}
