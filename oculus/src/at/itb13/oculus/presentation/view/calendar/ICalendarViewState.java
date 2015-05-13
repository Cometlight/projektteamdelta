package at.itb13.oculus.presentation.view.calendar;

import java.time.LocalDate;

import javafx.scene.layout.GridPane;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 11.05.2015
 */
public interface ICalendarViewState {

	void initGridPaneHeader(GridPane header);
	int getNumberOfDays();
	void changeHeader(LocalDate date);
	LocalDate onDatePickerSelected(LocalDate date);
	LocalDate getStartDate(LocalDate date);
	LocalDate nextButtonControl(LocalDate date);
	LocalDate previosButtonControl(LocalDate date);
}
