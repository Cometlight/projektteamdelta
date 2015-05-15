package at.itb13.oculus.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.domain.readonlyinterfaces.OrthoptistRO;
import at.itb13.teamD.domain.interfaces.IOrthoptist;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 23.04.2015
 */
@Entity
@Table(name = "orthoptist", catalog = "oculus_d")
public class Orthoptist implements java.io.Serializable, OrthoptistRO, IOrthoptist {
	@SuppressWarnings("unused")
	private static final Logger _logger = LogManager.getLogger(Orthoptist.class.getName());
	private static final long serialVersionUID = 1L;
	
	private Integer _orthoptistId;
	private Calendar _calendar;
	private User _user;

	public Orthoptist(){
	}

	public Orthoptist(Calendar calendar) {
		_calendar = calendar;
	}

	public Orthoptist(Calendar calendar, User user) {
		_calendar = calendar;
		_user = user;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "orthoptistId", unique = true, nullable = false)
	public Integer getOrthoptistId() {
		return _orthoptistId;
	}

	public void setOrthoptistId(Integer orthoptistId) {
		_orthoptistId = orthoptistId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "calendarId", nullable = false)
	public Calendar getCalendar() {
		return _calendar;
	}

	public void setCalendar(Calendar calendar) {
		_calendar = calendar;
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
