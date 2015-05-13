package at.itb13.oculus.presentation.util;

import java.util.HashMap;
import java.util.Map;

import at.itb13.oculus.domain.readonlyinterfaces.DoctorRO;
import javafx.util.StringConverter;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 13.04.2015
 */
public class DoctorStringConverter extends StringConverter<DoctorRO> {
	
	private Map<String, DoctorRO> _mapDoctors = new HashMap<String, DoctorRO>();

	/*
	 * @see javafx.util.StringConverter#toString(java.lang.Object)
	 */
	@Override
	public String toString(DoctorRO doctor) {
		String name = (doctor.getUser().getFirstName()+" "+doctor.getUser().getLastName());
		_mapDoctors.put(name, doctor);
	    return name;
	}

	/*
	 * @see javafx.util.StringConverter#fromString(java.lang.String)
	 */
	@Override
	public DoctorRO fromString(String name) {
		 return _mapDoctors.get(name);
	}

}
