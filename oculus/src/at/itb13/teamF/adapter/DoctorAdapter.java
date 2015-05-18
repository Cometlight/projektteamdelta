package at.itb13.teamF.adapter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;

import at.itb13.oculus.domain.Calendar;
import at.itb13.oculus.domain.Doctor;
import at.itb13.teamF.interfaces.IAdapter;
import at.oculus.teamf.domain.entity.exception.CantLoadPatientsException;
import at.oculus.teamf.domain.entity.interfaces.ICalendar;
import at.oculus.teamf.domain.entity.interfaces.IDoctor;
import at.oculus.teamf.domain.entity.interfaces.IOrthoptist;
import at.oculus.teamf.domain.entity.interfaces.IPatient;
import at.oculus.teamf.domain.entity.interfaces.IPatientQueue;
import at.oculus.teamf.persistence.exception.BadConnectionException;
import at.oculus.teamf.persistence.exception.NoBrokerMappedException;

/**
 * Adapter Doctor - TeamD --> Doctor TeamF
 * TODO getQueue setQueue getDomainObject addPatient
 * 
 * @author Karin Trommelschläger
 * @date 18.05.2015
 * 
 */
public class DoctorAdapter implements IAdapter, IDoctor{
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
		LocalDateTime localDateTime = _doctor.getUser().getCreateDate();
		Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());		
		return date;
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#setCreateDate(java.util.Date)
	 */
	@Override
	public void setCreateDate(Date createDate) {
		LocalDateTime localDateTime = createDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		_doctor.getUser().setCreateDate(localDateTime);
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#getIdleDate()
	 */
	@Override
	public Date getIdleDate() {
		LocalDateTime localDateTime = _doctor.getUser().getIdleDate();
		Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());		
		return date;
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#setIdleDate(java.util.Date)
	 */
	@Override
	public void setIdleDate(Date idleDate) {
		LocalDateTime localDateTime = idleDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		_doctor.getUser().setCreateDate(localDateTime);
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IOrthoptist#getId()
	 */
	@Override
	public int getId() {
		return _doctor.getDoctorId();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IOrthoptist#setId(int)
	 */
	@Override
	public void setId(int id) {
		_doctor.setDoctorId(id);
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IOrthoptist#getCalendar()
	 */
	@Override
	public ICalendar getCalendar() {
		Calendar calendar = _doctor.getCalendar();
		return new CalendarAdapter(calendar);
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IOrthoptist#setCalendar(at.oculus.teamf.domain.entity.interfaces.ICalendar)
	 */
	@Override
	public void setCalendar(ICalendar calendar) {
		CalendarAdapter calendarAdapter = (CalendarAdapter)calendar;
		Calendar cal = (Calendar)calendarAdapter.getDomainObject();
		_doctor.setCalendar(cal);
		
	}

	
	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IOrthoptist#setQueue(at.oculus.teamf.domain.entity.interfaces.IPatientQueue)
	 */
	@Override
	public void setQueue(IPatientQueue queue) {
		
		// TODO Auto-generated method stub
		
	}
	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IDoctor#getQueue()
	 */
	@Override
	public IPatientQueue getQueue() {
		// TODO Auto-generated method stub
		return null;
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
	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IDoctor#getDoctorSubstitude()
	 */
	@Override
	public IDoctor getDoctorSubstitude() {
		return new DoctorAdapter(_doctor.getDoctorSubstitute());
		
	}
	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IDoctor#setDoctorSubstitude(at.oculus.teamf.domain.entity.interfaces.IDoctor)
	 */
	@Override
	public void setDoctorSubstitude(IDoctor doctorSubstitude) {
		DoctorAdapter doctorAdapter = (DoctorAdapter)doctorSubstitude;
		Doctor doc = (Doctor)doctorAdapter.getDomainObject();
		_doctor.setDoctorSubstitute(doc);
		
	}
	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IDoctor#addPatient(at.oculus.teamf.domain.entity.interfaces.IPatient)
	 */
	@Override
	public void addPatient(IPatient patient) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IDoctor#getPatients()
	 */
	@Override
	public Collection<IPatient> getPatients() throws CantLoadPatientsException {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IDoctor#setPatients(java.util.Collection)
	 */
	@Override
	public void setPatients(Collection<IPatient> patients) {
		// TODO Auto-generated method stub
		
	}
	
}
