package at.itb13.oculus.presentation.view.calendar;

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
}
