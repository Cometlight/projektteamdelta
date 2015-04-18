package at.itb13.oculus.domain.readonlyinterfaces;

import java.time.LocalDateTime;
import java.util.Set;

import at.itb13.oculus.domain.Diagnosis;
import at.itb13.oculus.domain.ExaminationProtocolServiceCode;
import at.itb13.oculus.domain.ExaminationResult;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.ReferralLetter;
import at.itb13.oculus.domain.User;

/**
 * TODO: Insert description here.
 * 
 * @author Karin Trommelschläger
 * @date 17.04.2015
 * 
 */
public interface ExaminationProtocolRO {
	Integer getExaminationProtocolId();
	Diagnosis getDiagnosis();
	Patient getPatient();
	User getUser();
	LocalDateTime getStartProtocol();
	LocalDateTime getEndProtocol();
	String getDescription();
	Set<ExaminationResult> getExaminationResults();
	Set<ExaminationProtocolServiceCode> getExaminationProtocolServiceCodes();
	Set<ReferralLetter> getReferralLetters();
}
