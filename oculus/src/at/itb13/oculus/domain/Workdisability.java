package at.itb13.oculus.domain;

import java.util.Date;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 03.04.2015
 */
public class Workdisability implements java.io.Serializable {

	private Integer workDisabilityId;
	private Diagnosis diagnosis;
	private Date startDisability;
	private Date endDisability;
	private String reason;
	private Boolean bedRest;

	public Workdisability() {
	}

	public Workdisability(Diagnosis diagnosis, Date startDisability,
			Date endDisability, String reason, Boolean bedRest) {
		this.diagnosis = diagnosis;
		this.startDisability = startDisability;
		this.endDisability = endDisability;
		this.reason = reason;
		this.bedRest = bedRest;
	}

	public Integer getWorkDisabilityId() {
		return this.workDisabilityId;
	}

	public void setWorkDisabilityId(Integer workDisabilityId) {
		this.workDisabilityId = workDisabilityId;
	}

	public Diagnosis getDiagnosis() {
		return this.diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		this.diagnosis = diagnosis;
	}

	public Date getStartDisability() {
		return this.startDisability;
	}

	public void setStartDisability(Date startDisability) {
		this.startDisability = startDisability;
	}

	public Date getEndDisability() {
		return this.endDisability;
	}

	public void setEndDisability(Date endDisability) {
		this.endDisability = endDisability;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Boolean getBedRest() {
		return this.bedRest;
	}

	public void setBedRest(Boolean bedRest) {
		this.bedRest = bedRest;
	}

}