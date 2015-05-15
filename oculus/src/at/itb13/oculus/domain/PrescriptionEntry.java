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

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 23.04.2015
 */
@Entity
@Table(name = "prescriptionentry", catalog = "oculus_d")
public class PrescriptionEntry implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer _prescriptionEntryId;
	private Medicine _medicine;
	private Prescription _prescription;

	public PrescriptionEntry() {
	}

	public PrescriptionEntry(Medicine medicine, Prescription prescription) {
		_medicine = medicine;
		_prescription = prescription;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "prescriptionEntryId", unique = true, nullable = false)
	public Integer getPrescriptionEntryId() {
		return _prescriptionEntryId;
	}

	public void setPrescriptionEntryId(Integer prescriptionEntryId) {
		_prescriptionEntryId = prescriptionEntryId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "medicineId")
	public Medicine getMedicine() {
		return _medicine;
	}

	public void setMedicine(Medicine medicine) {
		_medicine = medicine;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prescriptionId")
	public Prescription getPrescription() {
		return _prescription;
	}

	public void setPrescription(Prescription prescription) {
		_prescription = prescription;
	}

}
