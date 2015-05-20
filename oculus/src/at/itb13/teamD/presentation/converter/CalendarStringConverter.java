package at.itb13.teamD.presentation.converter;

import java.util.HashMap;
import java.util.Map;

import javafx.util.StringConverter;
import at.itb13.teamD.domain.interfaces.ICalendar;

/**
 * @author Caroline Meusburger
 * @since 13.04.2015
 */
public class CalendarStringConverter extends StringConverter<ICalendar> {
	
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
