package at.itb13.oculus.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "administrator", catalog = "oculus_d")
public class Administrator implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger _logger = LogManager.getLogger(Administrator.class.getName());
	
	private Integer _administratorId;
	private User _user;

	public Administrator() {
	}

	public Administrator(User user) {
		_user = user;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "administratorId", unique = true, nullable = false)
	public Integer getAdministratorId() {
		return _administratorId;
	}

	public void setAdministratorId(Integer administratorId) {
		_administratorId = administratorId;
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
