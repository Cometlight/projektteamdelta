package at.itb13.oculus.technicalServices.converter;

import java.sql.Time;
import java.time.LocalTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * TODO: Insert description here.
 *
 * @author Florin Metzler
 * @since 30.04.2015
 */
@Converter
public class LocalTimeConverter implements AttributeConverter<LocalTime, Time> {

	@Override
	public Time convertToDatabaseColumn(LocalTime attribute) {
		return attribute != null ? Time.valueOf(attribute) : null;
	}

	@Override
	public LocalTime convertToEntityAttribute(Time dbData) {
		return dbData != null ? dbData.toLocalTime() : null;
	}
	
}
