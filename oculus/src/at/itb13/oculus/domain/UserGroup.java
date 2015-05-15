package at.itb13.oculus.domain;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Table(name = "usergroup", catalog = "oculus_d", uniqueConstraints = @UniqueConstraint(columnNames = "userGroupName"))
public class UserGroup implements java.io.Serializable {
	@SuppressWarnings("unused")
	private static final Logger _logger = LogManager.getLogger(UserGroup.class.getName());
	private static final long serialVersionUID = 1L;
	
	private Integer _userGroupId;
	private String _userGroupName;
	private String _description;
	private Set<UserPermission> _userPermissions = new HashSet<UserPermission>(0);
	private Set<User> _users = new HashSet<User>(0);

	public UserGroup() {
	}

	public UserGroup(String userGroupName) {
		_userGroupName = userGroupName;
	}

	public UserGroup(String userGroupName, String description,
			Set<UserPermission> userpermissions, Set<User> users) {
		_userGroupName = userGroupName;
		_description = description;
		_userPermissions = userpermissions;
		_users = users;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "userGroupId", unique = true, nullable = false)
	public Integer getUserGroupId() {
		return _userGroupId;
	}

	public void setUserGroupId(Integer userGroupId) {
		_userGroupId = userGroupId;
	}

	@Column(name = "userGroupName", unique = true, nullable = false, length = 50)
	public String getUserGroupName() {
		return _userGroupName;
	}

	public void setUserGroupName(String userGroupName) {
		_userGroupName = userGroupName;
	}

	@Column(name = "description", length = 65535)
	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usergroup")
	public Set<UserPermission> getUserPermissions() {
		return _userPermissions;
	}

	public void setUserPermissions(Set<UserPermission> userpermissions) {
		_userPermissions = userpermissions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usergroup")
	public Set<User> getUsers() {
		return _users;
	}

	public void setUsers(Set<User> users) {
		_users = users;
	}

}
