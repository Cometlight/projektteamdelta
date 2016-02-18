package at.itb13.oculus.presentation.gwt.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 01.06.2015
 */
public class Patient implements IsSerializable{
	private String _name;
	private String _sin;
	private String _doctor;
	
	public Patient(){}
	public Patient(String name, String sin, String doc){
		_name = name;
		_sin = sin;
		_doctor = doc;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return _name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		_name = name;
	}
	/**
	 * @return the sin
	 */
	public String getSin() {
		return _sin;
	}
	/**
	 * @param sin the sin to set
	 */
	public void setSin(String sin) {
		_sin = sin;
	}
	/**
	 * @return the doctor
	 */
	public String getDoctor() {
		return _doctor;
	}
	/**
	 * @param doctor the doctor to set
	 */
	public void setDoctor(String doctor) {
		_doctor = doctor;
	}
	
	
}
