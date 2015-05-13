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
	private GridPane _gridPaneHeader;
	private LocalDateLabel[] _gridHeaderDateLabel;

	/*
	 * @see at.itb13.oculus.presentation.view.calendar.ICalendarViewState#initGridPaneHeader()
	 */
	@Override
	public void initGridPaneHeader(GridPane header) {
		_gridPaneHeader = header;
		_gridPaneHeader.setGridLinesVisible(true); 	// TODO: "for debug purposes only" --> Durch CSS ersetzen
		_gridHeaderDateLabel = new LocalDateLabel[NUMBER_OF_DAYS];
		for(int i = 0; i < _gridHeaderDateLabel.length; i++){
			_gridHeaderDateLabel[i] = new LocalDateLabel();
		}
		_gridPaneHeader.add(new Text("Time"), 0, 0);
		for(int i = 0; i < _gridHeaderDateLabel.length; i++){
			_gridPaneHeader.add(_gridHeaderDateLabel[i], i+1, 0);
		}
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
		LocalDate startDate = getStartDate(date);
		
		for(int i = 0; i < _gridHeaderDateLabel.length; i++){
			
			_gridPaneHeader.getChildren().remove(_gridHeaderDateLabel[i]);
			_gridHeaderDateLabel[i] = new LocalDateLabel(startDate, startDate.getDayOfWeek().name() + " " + startDate);
//			_gridHeaderDateLabel[i] = new Text(startDate.getDayOfWeek().name() + " "+ startDate);
			startDate = startDate.plusDays(1);
			_gridPaneHeader.add(_gridHeaderDateLabel[i], i+1, 0);
		}
		
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
	
	public LocalDate getStartDate(LocalDate date){
		LocalDate monday = date;
		//search for Monday in this Week
		while(monday.getDayOfWeek() != DayOfWeek.MONDAY){
			monday = monday.minusDays(1);
		}
		return monday;
	}

	

}
