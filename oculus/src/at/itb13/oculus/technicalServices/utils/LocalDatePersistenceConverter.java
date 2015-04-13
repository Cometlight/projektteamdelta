package at.itb13.oculus.technicalServices.utils;

import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 13.04.2015
 */
@Converter
public class LocalDatePersistenceConverter implements AttributeConverter<LocalDate, java.sql.Date> {

	@Override
	public java.sql.Date convertToDatabaseColumn(LocalDate entityValue) {
		if (entityValue != null) {
			return java.sql.Date.valueOf(entityValue);
		}
		return null;
	}

	@Override
	public LocalDate convertToEntityAttribute(java.sql.Date databaseValue) {
		if (databaseValue != null) {
			return databaseValue.toLocalDate();
		}
		return null;
	}
}
