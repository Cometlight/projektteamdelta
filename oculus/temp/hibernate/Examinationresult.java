// default package
// Generated 14.04.2015 20:32:57 by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Examinationresult generated by hbm2java
 */
@Entity
@Table(name = "examinationresult", catalog = "oculusdb")
public class Examinationresult implements java.io.Serializable {

	private Integer examinationResultId;
	private Examinationprotocol examinationprotocol;
	private User user;
	private String result;
	private Date createDate;
	private String device;
	private byte[] deviceData;

	public Examinationresult() {
	}

	public Examinationresult(Examinationprotocol examinationprotocol,
			User user, String result, Date createDate, String device,
			byte[] deviceData) {
		this.examinationprotocol = examinationprotocol;
		this.user = user;
		this.result = result;
		this.createDate = createDate;
		this.device = device;
		this.deviceData = deviceData;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "examinationResultId", unique = true, nullable = false)
	public Integer getExaminationResultId() {
		return this.examinationResultId;
	}

	public void setExaminationResultId(Integer examinationResultId) {
		this.examinationResultId = examinationResultId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "examinationProtocolId")
	public Examinationprotocol getExaminationprotocol() {
		return this.examinationprotocol;
	}

	public void setExaminationprotocol(Examinationprotocol examinationprotocol) {
		this.examinationprotocol = examinationprotocol;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "result", length = 65535)
	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createDate", length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "device")
	public String getDevice() {
		return this.device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	@Column(name = "deviceData")
	public byte[] getDeviceData() {
		return this.deviceData;
	}

	public void setDeviceData(byte[] deviceData) {
		this.deviceData = deviceData;
	}

}
