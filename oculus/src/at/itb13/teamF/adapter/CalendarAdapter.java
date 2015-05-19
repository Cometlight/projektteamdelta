package at.itb13.teamF.adapter;

import at.itb13.oculus.domain.Calendar;
import at.itb13.teamF.interfaces.IAdapter;
import at.oculus.teamf.domain.entity.interfaces.ICalendar;

/**
 * Adapter for the ICalendar interface from TeamF.
 *
 * @author Florin Metzler
 * @since 18.05.2015
 */
public class CalendarAdapter implements ICalendar, IAdapter {
private Calendar _calendar;
	
	public CalendarAdapter() { }
	
	public CalendarAdapter(Calendar calendar){
		_calendar = calendar;
	}
		
	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IDomain#setId(int)
	 */
	@Override
	public void setId(int id) {
		_calendar.setCalendarId(id);
	}

	/*
	 * @see at.itb13.teamF.interfaces.IAdapter#getDomainObject()
	 */
	@Override
	public Object getDomainObject() {
		return _calendar;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.ICalendar#getId()
	 */
	@Override
	public int getId() {
		return _calendar.getCalendarId();
	}

}
