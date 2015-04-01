// default package
// Generated 01.04.2015 15:28:33 by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "user", catalog = "oculusdb")
public class User implements java.io.Serializable {

	private Integer userId;
	private Usergroup usergroup;
	private String userName;
	private String password;
	private String realName;
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

	public User(String userName, String password, String realName,
			Date createDate) {
		this.userName = userName;
		this.password = password;
		this.realName = realName;
		this.createDate = createDate;
	}

	public User(Usergroup usergroup, String userName, String password,
			String realName, Date createDate, Date idleDate,
			Set<Doctor> doctors, Set<Orthoptist> orthoptists,
			Set<Userpermission> userpermissions,
			Set<Administrator> administrators,
			Set<Examinationresult> examinationresults,
			Set<Receptionist> receptionists,
			Set<Examinationprotocol> examinationprotocols) {
		this.usergroup = usergroup;
		this.userName = userName;
		this.password = password;
		this.realName = realName;
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

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "userId", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userGroupId")
	public Usergroup getUsergroup() {
		return this.usergroup;
	}

	public void setUsergroup(Usergroup usergroup) {
		this.usergroup = usergroup;
	}

	@Column(name = "userName", nullable = false, length = 30)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "password", nullable = false, length = 30)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "realName", nullable = false, length = 100)
	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createDate", nullable = false, length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "idleDate", length = 19)
	public Date getIdleDate() {
		return this.idleDate;
	}

	public void setIdleDate(Date idleDate) {
		this.idleDate = idleDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Doctor> getDoctors() {
		return this.doctors;
	}

	public void setDoctors(Set<Doctor> doctors) {
		this.doctors = doctors;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Orthoptist> getOrthoptists() {
		return this.orthoptists;
	}

	public void setOrthoptists(Set<Orthoptist> orthoptists) {
		this.orthoptists = orthoptists;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Userpermission> getUserpermissions() {
		return this.userpermissions;
	}

	public void setUserpermissions(Set<Userpermission> userpermissions) {
		this.userpermissions = userpermissions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Administrator> getAdministrators() {
		return this.administrators;
	}

	public void setAdministrators(Set<Administrator> administrators) {
		this.administrators = administrators;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Examinationresult> getExaminationresults() {
		return this.examinationresults;
	}

	public void setExaminationresults(Set<Examinationresult> examinationresults) {
		this.examinationresults = examinationresults;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Receptionist> getReceptionists() {
		return this.receptionists;
	}

	public void setReceptionists(Set<Receptionist> receptionists) {
		this.receptionists = receptionists;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Examinationprotocol> getExaminationprotocols() {
		return this.examinationprotocols;
	}

	public void setExaminationprotocols(
			Set<Examinationprotocol> examinationprotocols) {
		this.examinationprotocols = examinationprotocols;
	}

}
