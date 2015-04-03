package at.itb13.oculus.domain.old;

import java.util.Date;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 03.04.2015
 */
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

	public Integer getExaminationResultId() {
		return this.examinationResultId;
	}

	public void setExaminationResultId(Integer examinationResultId) {
		this.examinationResultId = examinationResultId;
	}

	public Examinationprotocol getExaminationprotocol() {
		return this.examinationprotocol;
	}

	public void setExaminationprotocol(Examinationprotocol examinationprotocol) {
		this.examinationprotocol = examinationprotocol;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDevice() {
		return this.device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public byte[] getDeviceData() {
		return this.deviceData;
	}

	public void setDeviceData(byte[] deviceData) {
		this.deviceData = deviceData;
	}

}
