package at.itb13.oculus.domain;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 03.04.2015
 */
public class ExaminationprotocolservicecodeId implements java.io.Serializable {

	private int examinationProtocolId;
	private int serviceCodeId;
	private String insuranceCarrierKey;

	public ExaminationprotocolservicecodeId() {
	}

	public ExaminationprotocolservicecodeId(int examinationProtocolId,
			int serviceCodeId, String insuranceCarrierKey) {
		this.examinationProtocolId = examinationProtocolId;
		this.serviceCodeId = serviceCodeId;
		this.insuranceCarrierKey = insuranceCarrierKey;
	}

	public int getExaminationProtocolId() {
		return this.examinationProtocolId;
	}

	public void setExaminationProtocolId(int examinationProtocolId) {
		this.examinationProtocolId = examinationProtocolId;
	}

	public int getServiceCodeId() {
		return this.serviceCodeId;
	}

	public void setServiceCodeId(int serviceCodeId) {
		this.serviceCodeId = serviceCodeId;
	}

	public String getInsuranceCarrierKey() {
		return this.insuranceCarrierKey;
	}

	public void setInsuranceCarrierKey(String insuranceCarrierKey) {
		this.insuranceCarrierKey = insuranceCarrierKey;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ExaminationprotocolservicecodeId))
			return false;
		ExaminationprotocolservicecodeId castOther = (ExaminationprotocolservicecodeId) other;

		return (this.getExaminationProtocolId() == castOther
				.getExaminationProtocolId())
				&& (this.getServiceCodeId() == castOther.getServiceCodeId())
				&& ((this.getInsuranceCarrierKey() == castOther
						.getInsuranceCarrierKey()) || (this
						.getInsuranceCarrierKey() != null
						&& castOther.getInsuranceCarrierKey() != null && this
						.getInsuranceCarrierKey().equals(
								castOther.getInsuranceCarrierKey())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getExaminationProtocolId();
		result = 37 * result + this.getServiceCodeId();
		result = 37
				* result
				+ (getInsuranceCarrierKey() == null ? 0 : this
						.getInsuranceCarrierKey().hashCode());
		return result;
	}

}
