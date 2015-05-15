package at.itb13.oculus.domain;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Table(name = "servicecode", catalog = "oculus_d", uniqueConstraints = @UniqueConstraint(columnNames = "serviceCode"))
public class ServiceCode implements java.io.Serializable {
	@SuppressWarnings("unused")
	private static final Logger _logger = LogManager.getLogger(ServiceCode.class.getName());
	private static final long serialVersionUID = 1L;
	
	private Integer _serviceCodeId;
	private String _serviceCode;
	private String _description;
	private Set<ExaminationProtocolServiceCode> _examinationProtocolServiceCodes = new HashSet<ExaminationProtocolServiceCode>(0);

	public ServiceCode() { }

	public ServiceCode(String serviceCode, String description,
			Set<ExaminationProtocolServiceCode> examinationprotocolservicecodes) {
		_serviceCode = serviceCode;
		_description = description;
		_examinationProtocolServiceCodes = examinationprotocolservicecodes;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "serviceCodeId", unique = true, nullable = false)
	public Integer getServiceCodeId() {
		return _serviceCodeId;
	}

	public void setServiceCodeId(Integer serviceCodeId) {
		_serviceCodeId = serviceCodeId;
	}

	@Column(name = "serviceCode", unique = true, length = 10)
	public String getServiceCode() {
		return _serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		_serviceCode = serviceCode;
	}

	@Column(name = "description", length = 65535)
	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "servicecode")
	public Set<ExaminationProtocolServiceCode> getExaminationprotocolservicecodes() {
		return _examinationProtocolServiceCodes;
	}

	public void setExaminationprotocolservicecodes(
			Set<ExaminationProtocolServiceCode> examinationprotocolservicecodes) {
		_examinationProtocolServiceCodes = examinationprotocolservicecodes;
	}

}
