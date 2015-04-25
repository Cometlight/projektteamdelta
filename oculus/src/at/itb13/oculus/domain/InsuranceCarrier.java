package at.itb13.oculus.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 23.04.2015
 */
@Entity
@Table(name = "insurancecarrier", catalog = "oculus_d")
public class InsuranceCarrier implements java.io.Serializable {
	private static final Logger _logger = LogManager.getLogger(InsuranceCarrier.class.getName());
	private static final long serialVersionUID = 1L;
	
	private String _insuranceCarrierKey;
	private String _name;
	private Set<ExaminationProtocolServiceCode> _examinationProtocolServiceCodes = new HashSet<ExaminationProtocolServiceCode>(0);

	public InsuranceCarrier() { }

	public InsuranceCarrier(String insuranceCarrierKey, String name) {
		_insuranceCarrierKey = insuranceCarrierKey;
		_name = name;
	}

	public InsuranceCarrier(String insuranceCarrierKey, String name,
			Set<ExaminationProtocolServiceCode> examinationprotocolservicecodes) {
		_insuranceCarrierKey = insuranceCarrierKey;
		_name = name;
		_examinationProtocolServiceCodes = examinationprotocolservicecodes;
	}

	@Id
	@Column(name = "insuranceCarrierKey", unique = true, nullable = false, length = 4)
	public String getInsuranceCarrierKey() {
		return _insuranceCarrierKey;
	}

	public void setInsuranceCarrierKey(String insuranceCarrierKey) {
		_insuranceCarrierKey = insuranceCarrierKey;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "insurancecarrier")
	public Set<ExaminationProtocolServiceCode> getExaminationProtocolServiceCodes() {
		return _examinationProtocolServiceCodes;
	}

	public void setExaminationProtocolServiceCodes(
			Set<ExaminationProtocolServiceCode> examinationprotocolservicecodes) {
		_examinationProtocolServiceCodes = examinationprotocolservicecodes;
	}

}
