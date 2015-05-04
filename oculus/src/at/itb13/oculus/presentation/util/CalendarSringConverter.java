package at.itb13.oculus.presentation.util;

import java.util.HashMap;
import java.util.Map;

import at.itb13.oculus.domain.interfaces.ICalendar;
import at.itb13.oculus.domain.readonlyinterfaces.CalendarRO;
import at.itb13.oculus.domain.readonlyinterfaces.DoctorRO;
import javafx.util.StringConverter;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 13.04.2015
 */
public class CalendarSringConverter extends StringConverter<ICalendar> {
	
	private Map<String, ICalendar> _mapCalendar = new HashMap<String, ICalendar>();

	/*
	 * @see javafx.util.StringConverter#toString(java.lang.Object)
	 */
	@Override
	public String toString(ICalendar calendar) {
		String name = (calendar.getTitle());
		_mapCalendar.put(name, calendar);
	    return name;
	}

	/*
	 * @see javafx.util.StringConverter#fromString(java.lang.String)
	 */
	@Override
	public ICalendar fromString(String name) {
		 return _mapCalendar.get(name);
	}

}
