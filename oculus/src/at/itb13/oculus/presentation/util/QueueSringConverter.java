package at.itb13.oculus.presentation.util;

import java.util.HashMap;
import java.util.Map;

import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.readonlyinterfaces.DoctorRO;
import at.itb13.oculus.domain.readonlyinterfaces.QueueRO;
import javafx.util.StringConverter;



/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 13.04.2015
 */
public class QueueSringConverter extends StringConverter<QueueRO> {
	
	private Map<String, QueueRO> mapQueue = new HashMap<String, QueueRO>();

	/*
	 * @see javafx.util.StringConverter#toString(java.lang.Object)
	 */
	@Override
	public String toString(QueueRO queue) {
		String name = "";
		if(queue.getDoctor() != null){
			name = ("Dr. " +queue.getDoctor().getUser().getFirstName() + " " +queue.getDoctor().getUser().getLastName());
		}else if(queue.getOrthoptist() != null){
			name = ("Orthoptist "+queue.getOrthoptist().getUser().getFirstName() + " " +queue.getOrthoptist().getUser().getLastName());
		}
		else{
			name = "Orthoptists";
		}
		mapQueue.put(name, queue);
	    return name;
	}

	/*
	 * @see javafx.util.StringConverter#fromString(java.lang.String)
	 */
	@Override
	public QueueRO fromString(String name) {
		 return mapQueue.get(name);
	}

}
