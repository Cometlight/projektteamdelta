package at.itb13.oculus.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Convert;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.technicalServices.util.LocalDateTimePersistenceConverter;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 23.04.2015
 */
@Entity
@Table(name = "examinationresult", catalog = "oculus_d")
public class ExaminationResult implements java.io.Serializable {
	@SuppressWarnings("unused")
	private static final Logger _logger = LogManager.getLogger(ExaminationResult.class.getName());
	private static final long serialVersionUID = 1L;
	
	private Integer _examinationResultId;
	private ExaminationProtocol _examinationprotocol;
	private User _user;
	private String _result;
	private LocalDateTime _createDate;
	private String _device;
	private byte[] _deviceData;

	public ExaminationResult() {
	}

	public ExaminationResult(ExaminationProtocol examinationprotocol,
			User user, String result, LocalDateTime createDate, String device,
			byte[] deviceData) {
		_examinationprotocol = examinationprotocol;
		_user = user;
		_result = result;
		_createDate = createDate;
		_device = device;
		_deviceData = deviceData;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "examinationResultId", unique = true, nullable = false)
	public Integer getExaminationResultId() {
		return _examinationResultId;
	}

	public void setExaminationResultId(Integer examinationResultId) {
		_examinationResultId = examinationResultId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "examinationProtocolId")
	public ExaminationProtocol getExaminationprotocol() {
		return _examinationprotocol;
	}

	public void setExaminationprotocol(ExaminationProtocol examinationprotocol) {
		_examinationprotocol = examinationprotocol;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User getUser() {
		return _user;
	}

	public void setUser(User user) {
		_user = user;
	}

	@Column(name = "result", length = 65535)
	public String getResult() {
		return _result;
	}

	public void setResult(String result) {
		_result = result;
	}

	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name = "createDate", length = 19)
	public LocalDateTime getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		_createDate = createDate;
	}

	@Column(name = "device")
	public String getDevice() {
		return _device;
	}

	public void setDevice(String device) {
		_device = device;
	}

	@Column(name = "deviceData")
	public byte[] getDeviceData() {
		return _deviceData;
	}

	public void setDeviceData(byte[] deviceData) {
		_deviceData = deviceData;
	}

}
