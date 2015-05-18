package at.itb13.teamF.adapter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import at.itb13.oculus.domain.Calendar;
import at.itb13.oculus.domain.Orthoptist;
import at.itb13.teamF.interfaces.IAdapter;
import at.oculus.teamf.domain.entity.interfaces.ICalendar;
import at.oculus.teamf.domain.entity.interfaces.IOrthoptist;
import at.oculus.teamf.domain.entity.interfaces.IPatientQueue;
import at.oculus.teamf.persistence.exception.BadConnectionException;
import at.oculus.teamf.persistence.exception.NoBrokerMappedException;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date May 18, 2015
 */
public class OrthoptistAdapter implements IAdapter, IOrthoptist {
	private Orthoptist _orthoptist;
	
	public OrthoptistAdapter() { }
	
	public OrthoptistAdapter(Orthoptist orthoptist) {
		_orthoptist = orthoptist;
	}
	
	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#getUserId()
	 */
	@Override
	public int getUserId() {
		return _orthoptist.getUser().getUserId();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#setUserId(int)
	 */
	@Override
	public void setUserId(int id) {
		_orthoptist.getUser().setUserId(id);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#getUserGroupId()
	 */
	@Override
	public Integer getUserGroupId() {
		return _orthoptist.getUser().getUsergroup().getUserGroupId();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#setUserGroupId(java.lang.Integer)
	 */
	@Override
	public void setUserGroupId(Integer userGroupId) {
		_orthoptist.getUser().getUsergroup().setUserGroupId(userGroupId);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#getUserName()
	 */
	@Override
	public String getUserName() {
		return _orthoptist.getUser().getUserName();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#setUserName(java.lang.String)
	 */
	@Override
	public void setUserName(String userName) {
		_orthoptist.getUser().setUserName(userName);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#getPassword()
	 */
	@Override
	public String getPassword() {
		return _orthoptist.getUser().getPassword();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#setPassword(java.lang.String)
	 */
	@Override
	public void setPassword(String password) {
		_orthoptist.getUser().setPassword(password);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#getTitle()
	 */
	@Override
	public String getTitle() {
		return _orthoptist.getUser().getTitle();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#setTitle(java.lang.String)
	 */
	@Override
	public void setTitle(String title) {
		_orthoptist.getUser().setTitle(title);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#getFirstName()
	 */
	@Override
	public String getFirstName() {
		return _orthoptist.getUser().getFirstName();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#setFirstName(java.lang.String)
	 */
	@Override
	public void setFirstName(String firstName) {
		_orthoptist.getUser().setFirstName(firstName);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#getLastName()
	 */
	@Override
	public String getLastName() {
		return _orthoptist.getUser().getLastName();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#setLastName(java.lang.String)
	 */
	@Override
	public void setLastName(String lastName) {
		_orthoptist.getUser().setLastName(lastName);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#getEmail()
	 */
	@Override
	public String getEmail() {
		return _orthoptist.getUser().getEmail();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#setEmail(java.lang.String)
	 */
	@Override
	public void setEmail(String email) {
		_orthoptist.getUser().setEmail(email);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#getCreateDate()
	 */
	@Override
	public Date getCreateDate() {
		LocalDateTime localDateTime = _orthoptist.getUser().getCreateDate();
		Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());		
		return date;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#setCreateDate(java.util.Date)
	 */
	@Override
	public void setCreateDate(Date createDate) {
		LocalDateTime localDateTime = createDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		_orthoptist.getUser().setCreateDate(localDateTime);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#getIdleDate()
	 */
	@Override
	public Date getIdleDate() {
		LocalDateTime localDateTime = _orthoptist.getUser().getIdleDate();
		Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());		
		return date;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#setIdleDate(java.util.Date)
	 */
	@Override
	public void setIdleDate(Date idleDate) {
		LocalDateTime localDateTime = idleDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		_orthoptist.getUser().setCreateDate(localDateTime);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IOrthoptist#getId()
	 */
	@Override
	public int getId() {
		return _orthoptist.getOrthoptistId();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IOrthoptist#setId(int)
	 */
	@Override
	public void setId(int id) {
		_orthoptist.setOrthoptistId(id);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IOrthoptist#getCalendar()
	 */
	@Override
	public ICalendar getCalendar() {
		Calendar calendar = _orthoptist.getCalendar();
		return new CalendarAdapter(calendar);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IOrthoptist#setCalendar(at.oculus.teamf.domain.entity.interfaces.ICalendar)
	 */
	@Override
	public void setCalendar(ICalendar calendar) {
		CalendarAdapter calendarAdapter = (CalendarAdapter)calendar;
		Calendar cal = (Calendar)calendarAdapter.getDomainObject();
		_orthoptist.setCalendar(cal);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IOrthoptist#getQueue()
	 */
	@Override
	public IPatientQueue getQueue() throws NoBrokerMappedException,
			BadConnectionException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IOrthoptist#setQueue(at.oculus.teamf.domain.entity.interfaces.IPatientQueue)
	 */
	@Override
	public void setQueue(IPatientQueue queue) {
		// TODO Auto-generated method stub

	}

	/*
	 * @see at.itb13.teamF.interfaces.IAdapter#getDomainObject()
	 */
	@Override
	public Object getDomainObject() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the orthoptist
	 */
	public Orthoptist getOrthoptist() {
		return _orthoptist;
	}

	/**
	 * @param orthoptist the orthoptist to set
	 */
	public void setOrthoptist(Orthoptist orthoptist) {
		_orthoptist = orthoptist;
	}
}
