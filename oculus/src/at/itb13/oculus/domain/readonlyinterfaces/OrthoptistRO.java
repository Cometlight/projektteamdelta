package at.itb13.oculus.domain.readonlyinterfaces;

import at.itb13.oculus.domain.Calendar;
import at.itb13.oculus.domain.User;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 25.04.2015
 */
public interface OrthoptistRO {
	Integer getOrthoptistId();
	Calendar getCalendar();
	User getUser();
}
