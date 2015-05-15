package at.itb13.oculus.presentation.view.calendar;

import javafx.scene.control.CheckBox;
import at.itb13.teamD.domain.interfaces.ICalendar;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 11.05.2015
 */
public class CalendarCheckBox extends CheckBox {
	private ICalendar _calendar;
	
	
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

