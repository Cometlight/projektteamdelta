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
	
	private Integer examinationProtocolServiceCodeId;
	private ExaminationProtocol examinationprotocol;
	private InsuranceCarrier insurancecarrier;
	private ServiceCode servicecode;

	public ExaminationProtocolServiceCode() {
	}

	public ExaminationProtocolServiceCode(
			ExaminationProtocol examinationprotocol,
			InsuranceCarrier insurancecarrier, ServiceCode servicecode) {
		this.examinationprotocol = examinationprotocol;
		this.insurancecarrier = insurancecarrier;
		this.servicecode = servicecode;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "examinationProtocolServiceCodeId", unique = true, nullable = false)
	public Integer getExaminationProtocolServiceCodeId() {
		return this.examinationProtocolServiceCodeId;
	}

	public void setExaminationProtocolServiceCodeId(
			Integer examinationProtocolServiceCodeId) {
		this.examinationProtocolServiceCodeId = examinationProtocolServiceCodeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "examinationProtocolId")
	public ExaminationProtocol getExaminationprotocol() {
		return this.examinationprotocol;
	}

	public void setExaminationprotocol(ExaminationProtocol examinationprotocol) {
		this.examinationprotocol = examinationprotocol;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "insuranceCarrierKey")
	public InsuranceCarrier getInsurancecarrier() {
		return this.insurancecarrier;
	}

	public void setInsurancecarrier(InsuranceCarrier insurancecarrier) {
		this.insurancecarrier = insurancecarrier;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "serviceCodeId")
	public ServiceCode getServicecode() {
		return this.servicecode;
	}

	public void setServicecode(ServiceCode servicecode) {
		this.servicecode = servicecode;
	}

}
