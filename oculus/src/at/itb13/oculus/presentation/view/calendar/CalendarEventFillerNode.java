package at.itb13.oculus.presentation.view.calendar;

import javafx.scene.control.Label;
import at.itb13.oculus.domain.interfaces.ICalendar;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 11.05.2015
 */
public class CalendarEventFillerNode extends Label {
	private ICalendar _calendar;
	
	public CalendarEventFillerNode(ICalendar calendar) {
		_calendar = calendar;
	}

	public ICalendar getCalendar() {
		return _calendar;
	}
	
	public void setCalendar(ICalendar calendar) {
		_calendar = calendar;
	}
}