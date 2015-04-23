package at.itb13.oculus.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 14.04.2015
 */
@Entity
@Table(name = "examinationprotocolservicecode", catalog = "oculus_d")
public class ExaminationProtocolServiceCode implements java.io.Serializable {
	private static final Logger _logger = LogManager.getLogger(ExaminationProtocolServiceCode.class.getName());
	private static final long serialVersionUID = 1L;
	
	private Integer _examinationProtocolServiceCodeId;
	private ExaminationProtocol _examinationProtocol;
	private InsuranceCarrier _insuranceCarrier;
	private ServiceCode _serviceCode;

	public ExaminationProtocolServiceCode() { }

	public ExaminationProtocolServiceCode(
			ExaminationProtocol examinationprotocol,
			InsuranceCarrier insurancecarrier, ServiceCode servicecode) {
		_examinationProtocol = examinationprotocol;
		_insuranceCarrier = insurancecarrier;
		_serviceCode = servicecode;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "examinationProtocolServiceCodeId", unique = true, nullable = false)
	public Integer getExaminationProtocolServiceCodeId() {
		return _examinationProtocolServiceCodeId;
	}

	public void setExaminationProtocolServiceCodeId(
			Integer examinationProtocolServiceCodeId) {
		_examinationProtocolServiceCodeId = examinationProtocolServiceCodeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "examinationProtocolId")
	public ExaminationProtocol getExaminationProtocol() {
		return _examinationProtocol;
	}

	public void setExaminationProtocol(ExaminationProtocol examinationprotocol) {
		_examinationProtocol = examinationprotocol;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "insuranceCarrierKey")
	public InsuranceCarrier getInsuranceCarrier() {
		return _insuranceCarrier;
	}

	public void setInsuranceCarrier(InsuranceCarrier insurancecarrier) {
		_insuranceCarrier = insurancecarrier;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "serviceCodeId")
	public ServiceCode getServiceCode() {
		return _serviceCode;
	}

	public void setServicecode(ServiceCode servicecode) {
		_serviceCode = servicecode;
	}

}
