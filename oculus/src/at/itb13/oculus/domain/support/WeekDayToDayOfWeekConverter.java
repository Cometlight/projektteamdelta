package at.itb13.oculus.domain.support;

import java.time.DayOfWeek;

import javax.persistence.AttributeConverter;

/**
 * TODO: Insert description here.
 *
 * @author Florin Metzler
 * @since 12.05.2015
 */
public class WeekDayToDayOfWeekConverter implements AttributeConverter<DayOfWeek, String> {

	/*
	 * @see javax.persistence.AttributeConverter#convertToDatabaseColumn(java.lang.Object)
	 */
	@Override
	public String convertToDatabaseColumn(DayOfWeek attr) {
		switch (attr) {
		case FRIDAY:
			return "FRI";
		case MONDAY:
			return "MON";
		case SATURDAY:
			return "SAT";
		case SUNDAY:
			return "SUN";
		case THURSDAY:
			return "THU";
		case TUESDAY:
			return "TUE";
		case WEDNESDAY:
			return "WED";
		default:
			throw new AssertionError("hi :)");		
		}
	}

	/*
	 * @see javax.persistence.AttributeConverter#convertToEntityAttribute(java.lang.Object)
	 */
	@Override
	public DayOfWeek convertToEntityAttribute(String dbValue) {
		switch (dbValue) {
		case "FRI":
			return DayOfWeek.FRIDAY;
		case "MON":
			return DayOfWeek.MONDAY;
		case "SAT":
			return DayOfWeek.SATURDAY;
		case "SUN":
			return DayOfWeek.SUNDAY;
		case "THU":
			return DayOfWeek.THURSDAY;
		case "TUE":
			return DayOfWeek.TUESDAY;
		case "WED":
			return DayOfWeek.WEDNESDAY;
		default:
			throw new AssertionError("hi :)");		
		}
	}

}
