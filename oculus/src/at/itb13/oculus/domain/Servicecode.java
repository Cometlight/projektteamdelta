package at.itb13.oculus.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 03.04.2015
 */
public class Servicecode implements java.io.Serializable {

	private Integer serviceCodeId;
	private String serviceCode;
	private String description;
	private Set<Examinationprotocolservicecode> examinationprotocolservicecodes = new HashSet<Examinationprotocolservicecode>(
			0);

	public Servicecode() {
	}

	public Servicecode(String serviceCode, String description,
			Set<Examinationprotocolservicecode> examinationprotocolservicecodes) {
		this.serviceCode = serviceCode;
		this.description = description;
		this.examinationprotocolservicecodes = examinationprotocolservicecodes;
	}

	public Integer getServiceCodeId() {
		return this.serviceCodeId;
	}

	public void setServiceCodeId(Integer serviceCodeId) {
		this.serviceCodeId = serviceCodeId;
	}

	public String getServiceCode() {
		return this.serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Examinationprotocolservicecode> getExaminationprotocolservicecodes() {
		return this.examinationprotocolservicecodes;
	}

	public void setExaminationprotocolservicecodes(
			Set<Examinationprotocolservicecode> examinationprotocolservicecodes) {
		this.examinationprotocolservicecodes = examinationprotocolservicecodes;
	}

}
