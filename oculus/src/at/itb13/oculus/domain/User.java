package at.itb13.oculus.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Convert;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.technicalServices.converter.LocalDateTimePersistenceConverter;
import at.itb13.teamD.domain.interfaces.IUser;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 23.04.2015
 */
@Entity
@Table(name = "user", catalog = "oculus_d")
public class User implements java.io.Serializable, IUser{
	@SuppressWarnings("unused")
	private static final Logger _logger = LogManager.getLogger(User.class.getName());
	private static final long serialVersionUID = 1L;
	
	private Integer _userId;
	private UserGroup _usergroup;
	private String _userName;
	private String _password;
	private String _title;
	private String _firstName;
	private String _lastName;
	private String _email;
	private LocalDateTime _createDate;
	private LocalDateTime _idleDate;
	
	private Doctor _doctor;
	private Orthoptist _orthoptist;
	private Receptionist _receptionist;
	private Administrator _administrator;
	
	private Set<UserPermission> _userPermissions = new HashSet<UserPermission>(0);
	private Set<ExaminationResult> _examinationResults = new HashSet<ExaminationResult>(0);
	private Set<ExaminationProtocol> _examinationProtocols = new HashSet<ExaminationProtocol>(0);

	public User() { }

	public User(String userName, String password, String firstName,
			String lastName, LocalDateTime createDate) {
		_userName = userName;
		_password = password;
		_firstName = firstName;
		_lastName = lastName;
		_createDate = createDate;
	}

	public User(UserGroup usergroup, String userName, String password,
			String title, String firstName, String lastName, String email,
			LocalDateTime createDate, LocalDateTime idleDate, Set<Doctor> doctors,
			Set<Orthoptist> orthoptists, Set<UserPermission> userpermissions,
			Set<Administrator> administrators,
			Set<ExaminationResult> examinationresults,
			Set<Receptionist> receptionists,
			Set<ExaminationProtocol> examinationprotocols) {
		_usergroup = usergroup;
		_userName = userName;
		_password = password;
		_title = title;
		_firstName = firstName;
		_lastName = lastName;
		_email = email;
		_createDate = createDate;
		_idleDate = idleDate;
		_userPermissions = userpermissions;
		_examinationResults = examinationresults;
		_examinationProtocols = examinationprotocols;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "userId", unique = true, nullable = false)
	public Integer getUserId() {
		return _userId;
	}

	public void setUserId(Integer userId) {
		_userId = userId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userGroupId")
	public UserGroup getUsergroup() {
		return _usergroup;
	}

	public void setUsergroup(UserGroup usergroup) {
		_usergroup = usergroup;
	}

	@Column(name = "userName", nullable = false, length = 30)
	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return _password;
	}

	public void setPassword(String password) {
		_password = password;
	}

	@Column(name = "title", length = 30)
	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	@Column(name = "firstName", nullable = false, length = 50)
	public String getFirstName() {
		return _firstName;
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;
	}

	@Column(name = "lastName", nullable = false, length = 50)
	public String getLastName() {
		return _lastName;
	}

	public void setLastName(String lastName) {
		_lastName = lastName;
	}

	@Column(name = "email")
	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
	}

	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name = "createDate", nullable = false, length = 19)
	public LocalDateTime getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		_createDate = createDate;
	}

	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name = "idleDate", length = 19)
	public LocalDateTime getIdleDate() {
		return _idleDate;
	}

	public void setIdleDate(LocalDateTime idleDate) {
		_idleDate = idleDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserPermission> getUserpermissions() {
		return _userPermissions;
	}

	public void setUserpermissions(Set<UserPermission> userpermissions) {
		_userPermissions = userpermissions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<ExaminationResult> getExaminationresults() {
		return _examinationResults;
	}

	public void setExaminationresults(Set<ExaminationResult> examinationresults) {
		_examinationResults = examinationresults;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<ExaminationProtocol> getExaminationprotocols() {
		return _examinationProtocols;
	}

	public void setExaminationprotocols(
			Set<ExaminationProtocol> examinationprotocols) {
		_examinationProtocols = examinationprotocols;
	}

	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public Doctor getDoctor() {
		return _doctor;
	}

	public void setDoctor(Doctor doctor) {
		_doctor = doctor;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public Orthoptist getOrthoptist() {
		return _orthoptist;
	}

	public void setOrthoptist(Orthoptist orthoptist) {
		_orthoptist = orthoptist;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public Receptionist getReceptionist() {
		return _receptionist;
	}

	public void setReceptionist(Receptionist receptionist) {
		_receptionist = receptionist;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public Administrator getAdministrator() {
		return _administrator;
	}

	public void setAdministrator(Administrator administrator) {
		_administrator = administrator;
	}
}
