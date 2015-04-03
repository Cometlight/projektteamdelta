package at.itb13.oculus.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 03.04.2015
 */
public class User implements java.io.Serializable {

	private Integer userId;
	private Usergroup usergroup;
	private String userName;
	private String password;
	private String title;
	private String firstName;
	private String lastName;
	private String email;
	private Date createDate;
	private Date idleDate;
	private Set<Doctor> doctors = new HashSet<Doctor>(0);
	private Set<Orthoptist> orthoptists = new HashSet<Orthoptist>(0);
	private Set<Userpermission> userpermissions = new HashSet<Userpermission>(0);
	private Set<Administrator> administrators = new HashSet<Administrator>(0);
	private Set<Examinationresult> examinationresults = new HashSet<Examinationresult>(
			0);
	private Set<Receptionist> receptionists = new HashSet<Receptionist>(0);
	private Set<Examinationprotocol> examinationprotocols = new HashSet<Examinationprotocol>(
			0);

	public User() {
	}

	public User(String userName, String password, String firstName,
			String lastName, Date createDate) {
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.createDate = createDate;
	}

	public User(Usergroup usergroup, String userName, String password,
			String title, String firstName, String lastName, String email,
			Date createDate, Date idleDate, Set<Doctor> doctors,
			Set<Orthoptist> orthoptists, Set<Userpermission> userpermissions,
			Set<Administrator> administrators,
			Set<Examinationresult> examinationresults,
			Set<Receptionist> receptionists,
			Set<Examinationprotocol> examinationprotocols) {
		this.usergroup = usergroup;
		this.userName = userName;
		this.password = password;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.createDate = createDate;
		this.idleDate = idleDate;
		this.doctors = doctors;
		this.orthoptists = orthoptists;
		this.userpermissions = userpermissions;
		this.administrators = administrators;
		this.examinationresults = examinationresults;
		this.receptionists = receptionists;
		this.examinationprotocols = examinationprotocols;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Usergroup getUsergroup() {
		return this.usergroup;
	}

	public void setUsergroup(Usergroup usergroup) {
		this.usergroup = usergroup;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getIdleDate() {
		return this.idleDate;
	}

	public void setIdleDate(Date idleDate) {
		this.idleDate = idleDate;
	}

	public Set<Doctor> getDoctors() {
		return this.doctors;
	}

	public void setDoctors(Set<Doctor> doctors) {
		this.doctors = doctors;
	}

	public Set<Orthoptist> getOrthoptists() {
		return this.orthoptists;
	}

	public void setOrthoptists(Set<Orthoptist> orthoptists) {
		this.orthoptists = orthoptists;
	}

	public Set<Userpermission> getUserpermissions() {
		return this.userpermissions;
	}

	public void setUserpermissions(Set<Userpermission> userpermissions) {
		this.userpermissions = userpermissions;
	}

	public Set<Administrator> getAdministrators() {
		return this.administrators;
	}

	public void setAdministrators(Set<Administrator> administrators) {
		this.administrators = administrators;
	}

	public Set<Examinationresult> getExaminationresults() {
		return this.examinationresults;
	}

	public void setExaminationresults(Set<Examinationresult> examinationresults) {
		this.examinationresults = examinationresults;
	}

	public Set<Receptionist> getReceptionists() {
		return this.receptionists;
	}

	public void setReceptionists(Set<Receptionist> receptionists) {
		this.receptionists = receptionists;
	}

	public Set<Examinationprotocol> getExaminationprotocols() {
		return this.examinationprotocols;
	}

	public void setExaminationprotocols(
			Set<Examinationprotocol> examinationprotocols) {
		this.examinationprotocols = examinationprotocols;
	}

}
