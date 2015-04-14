package at.itb13.oculus.presentation.util;

import java.util.HashMap;
import java.util.Map;

import at.itb13.oculus.domain.Doctor;
import javafx.util.StringConverter;



/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 13.04.2015
 */
public class DoctorSringConverter extends StringConverter<Doctor> {
	
	private Map<String, Doctor> mapDoctors = new HashMap<String, Doctor>();

	/*
	 * @see javafx.util.StringConverter#toString(java.lang.Object)
	 */
	@Override
	public String toString(Doctor doctor) {
		String name = (doctor.getUser().getFirstName()+" "+doctor.getUser().getLastName());
		mapDoctors.put(name, doctor);
	    return name;
	}

	/*
	 * @see javafx.util.StringConverter#fromString(java.lang.String)
	 */
	@Override
	public Doctor fromString(String name) {
		 return mapDoctors.get(name);
	}

}
