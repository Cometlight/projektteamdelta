package at.itb13.oculus.application.doctor;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.domain.ExaminationProtocol;
import at.itb13.oculus.domain.readonlyinterfaces.ExaminationProtocolRO;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 22.04.2015
 */
public class WelcomePatient {
	private static final Logger _logger = LogManager.getLogger(WelcomePatient.class.getName());
	
	/**
	 * TODO
	 * 
	 * @param patientRO
	 * @return
	 */
	public List<? extends ExaminationProtocolRO> getAllExaminationProtocolsSorted(PatientRO patientRO) {
		Set<ExaminationProtocol> listExPro = patientRO.getExaminationprotocols();
		return ExaminationProtocol.sortExaminationProtocolsByStartDate(listExPro);
	}
}
