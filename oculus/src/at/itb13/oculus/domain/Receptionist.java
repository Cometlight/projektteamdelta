package at.itb13.oculus.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;

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
 * @date 23.04.2015
 */
@Entity
@Table(name = "receptionist", catalog = "oculus_d")
public class Receptionist implements java.io.Serializable {
	private static final Logger _logger = LogManager.getLogger(Receptionist.class.getName());
	private static final long serialVersionUID = 1L;
	
	private Integer _receptionistId;
	private User _user;

	public Receptionist() {
	}

	public Receptionist(User user) {
		_user = user;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "receptionistId", unique = true, nullable = false)
	public Integer getReceptionistId() {
		return _receptionistId;
	}

	public void setReceptionistId(Integer receptionistId) {
		_receptionistId = receptionistId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User getUser() {
		return _user;
	}

	public void setUser(User user) {
		_user = user;
	}

}
