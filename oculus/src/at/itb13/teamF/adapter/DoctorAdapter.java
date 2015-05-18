package at.itb13.teamF.adapter;

import java.util.Date;

import at.itb13.oculus.domain.Doctor;
import at.itb13.teamF.interfaces.IAdapter;
import at.oculus.teamf.domain.entity.interfaces.ICalendar;
import at.oculus.teamf.domain.entity.interfaces.IOrthoptist;
import at.oculus.teamf.domain.entity.interfaces.IPatientQueue;
import at.oculus.teamf.persistence.exception.BadConnectionException;
import at.oculus.teamf.persistence.exception.NoBrokerMappedException;

/**
 * Adapter Doctor - TeamD --> Doctor TeamF
 * 
 * @author Karin Trommelschl�ger
 * @date 18.05.2015
 * 
 */
public class DoctorAdapter implements IAdapter, IOrthoptist{
	Doctor _doctor;
	
	
	public DoctorAdapter() {
		
	}
	public DoctorAdapter(Doctor doctor){
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#getUserId()
	 */
	@Override
	public int getUserId() {
		return _doctor.getUser().getUserId();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#setUserId(int)
	 */
	@Override
	public void setUserId(int id) {
		_doctor.getUser().setUserId(id);
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#getUserGroupId()
	 */
	@Override
	public Integer getUserGroupId() {
		return _doctor.getUser().getUsergroup().getUserGroupId();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#setUserGroupId(java.lang.Integer)
	 */
	@Override
	public void setUserGroupId(Integer userGroupId) {
		_doctor.getUser().getUsergroup().setUserGroupId(userGroupId);
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#getUserName()
	 */
	@Override
	public String getUserName() {
		return _doctor.getUser().getUserName();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#setUserName(java.lang.String)
	 */
	@Override
	public void setUserName(String userName) {
		_doctor.getUser().setUserName(userName);
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#getPassword()
	 */
	@Override
	public String getPassword() {
		return _doctor.getUser().getPassword();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#setPassword(java.lang.String)
	 */
	@Override
	public void setPassword(String password) {
		_doctor.getUser().setPassword(password);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#getTitle()
	 */
	@Override
	public String getTitle() {
		return _doctor.getUser().getTitle();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#setTitle(java.lang.String)
	 */
	@Override
	public void setTitle(String title) {
		_doctor.getUser().setTitle(title);
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#getFirstName()
	 */
	@Override
	public String getFirstName() {
		return _doctor.getUser().getFirstName();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#setFirstName(java.lang.String)
	 */
	@Override
	public void setFirstName(String firstName) {
		_doctor.getUser().setFirstName(firstName);
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#getLastName()
	 */
	@Override
	public String getLastName() {
		return _doctor.getUser().getLastName();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#setLastName(java.lang.String)
	 */
	@Override
	public void setLastName(String lastName) {
		_doctor.getUser().setLastName(lastName);
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#getEmail()
	 */
	@Override
	public String getEmail() {
		return _doctor.getUser().getEmail();
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#setEmail(java.lang.String)
	 */
	@Override
	public void setEmail(String email) {
		_doctor.getUser().setEmail(email);
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#getCreateDate()
	 */
	@Override
	public Date getCreateDate() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#setCreateDate(java.util.Date)
	 */
	@Override
	public void setCreateDate(Date createDate) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#getIdleDate()
	 */
	@Override
	public Date getIdleDate() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#setIdleDate(java.util.Date)
	 */
	@Override
	public void setIdleDate(Date idleDate) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IOrthoptist#getId()
	 */
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IOrthoptist#setId(int)
	 */
	@Override
	public void setId(int id) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IOrthoptist#getCalendar()
	 */
	@Override
	public ICalendar getCalendar() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IOrthoptist#setCalendar(at.oculus.teamf.domain.entity.interfaces.ICalendar)
	 */
	@Override
	public void setCalendar(ICalendar calendar) {
		// TODO Auto-generated method stub
		
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
	 * @return the _doctor
	 */
	public Doctor get_doctor() {
		return _doctor;
	}
	/**
	 * @param _doctor the _doctor to set
	 */
	public void set_doctor(Doctor _doctor) {
		this._doctor = _doctor;
	}
}
