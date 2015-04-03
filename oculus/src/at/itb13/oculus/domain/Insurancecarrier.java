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
public class Insurancecarrier implements java.io.Serializable {

	private String insuranceCarrierKey;
	private String name;
	private Set<Examinationprotocolservicecode> examinationprotocolservicecodes = new HashSet<Examinationprotocolservicecode>(
			0);

	public Insurancecarrier() {
	}

	public Insurancecarrier(String insuranceCarrierKey, String name) {
		this.insuranceCarrierKey = insuranceCarrierKey;
		this.name = name;
	}

	public Insurancecarrier(String insuranceCarrierKey, String name,
			Set<Examinationprotocolservicecode> examinationprotocolservicecodes) {
		this.insuranceCarrierKey = insuranceCarrierKey;
		this.name = name;
		this.examinationprotocolservicecodes = examinationprotocolservicecodes;
	}

	public String getInsuranceCarrierKey() {
		return this.insuranceCarrierKey;
	}

	public void setInsuranceCarrierKey(String insuranceCarrierKey) {
		this.insuranceCarrierKey = insuranceCarrierKey;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Examinationprotocolservicecode> getExaminationprotocolservicecodes() {
		return this.examinationprotocolservicecodes;
	}

	public void setExaminationprotocolservicecodes(
			Set<Examinationprotocolservicecode> examinationprotocolservicecodes) {
		this.examinationprotocolservicecodes = examinationprotocolservicecodes;
	}

}
