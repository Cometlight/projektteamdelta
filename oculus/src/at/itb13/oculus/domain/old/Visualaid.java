package at.itb13.oculus.domain.old;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 03.04.2015
 */
public class Visualaid implements java.io.Serializable {

	private Integer visualAidId;
	private Diagnosis diagnosis;

	public Visualaid() {
	}

	public Visualaid(Diagnosis diagnosis) {
		this.diagnosis = diagnosis;
	}

	public Integer getVisualAidId() {
		return this.visualAidId;
	}

	public void setVisualAidId(Integer visualAidId) {
		this.visualAidId = visualAidId;
	}

	public Diagnosis getDiagnosis() {
		return this.diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		this.diagnosis = diagnosis;
	}

}
