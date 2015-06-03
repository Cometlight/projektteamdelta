package at.itb13.oculus.presentation.gwt.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 01.06.2015
 */
public class CalendarEvent implements IsSerializable{
	private int _id;
	private String _date;
	private String _doctorOrthoptist;
	private String _type;
	private String _reason;
	
	public CalendarEvent(){}
	public CalendarEvent(int id, String date, String doctor, String type, String reason){
		_id = id;
		_date = date;
		_doctorOrthoptist = doctor;
		_type = type;
		_reason = reason;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return _id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		_id = id;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return _date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		_date = date;
	}
	/**
	 * @return the doctor
	 */
	public String getDoctorOrthoptist() {
		return _doctorOrthoptist;
	}
	/**
	 * @param doctorOrthoptist the doctor to set
	 */
	public void setDoctorOrthoptist(String doctorOrthoptist) {
		_doctorOrthoptist = doctorOrthoptist;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return _type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		_type = type;
	}
	/**
	 * @return the reason
	 */
	public String getReason() {
		return _reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		_reason = reason;
	}

}
