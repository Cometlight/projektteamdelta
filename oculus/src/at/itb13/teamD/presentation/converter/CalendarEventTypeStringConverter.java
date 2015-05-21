package at.itb13.teamD.presentation.converter;

import java.util.HashMap;
import java.util.Map;

import javafx.util.StringConverter;
import at.itb13.teamD.domain.interfaces.IEventType;

/**
 * @author Caroline Meusburger
 * @since 13.04.2015
 */
public class CalendarEventTypeStringConverter extends StringConverter<IEventType> {
	
	private Map<String, IEventType> _mapEventType = new HashMap<String, IEventType>();

	/*
	 * @see javafx.util.StringConverter#toString(java.lang.Object)
	 */
	@Override
	public String toString(IEventType eventtype) {
		String name = (eventtype.getEventTypeName());
		_mapEventType.put(name, eventtype);
	    return name;
	}

	/*
	 * @see javafx.util.StringConverter#fromString(java.lang.String)
	 */
	@Override
	public IEventType fromString(String name) {
		 return _mapEventType.get(name);
	}

}
