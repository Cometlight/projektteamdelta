package at.itb13.oculus.domain;


import java.util.HashSet;
import java.util.Set;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 03.04.2015
 */
public class Usergroup implements java.io.Serializable {

	private Integer userGroupId;
	private String userGroupName;
	private String description;
	private Set<Userpermission> userpermissions = new HashSet<Userpermission>(0);
	private Set<User> users = new HashSet<User>(0);

	public Usergroup() {
	}

	public Usergroup(String userGroupName) {
		this.userGroupName = userGroupName;
	}

	public Usergroup(String userGroupName, String description,
			Set<Userpermission> userpermissions, Set<User> users) {
		this.userGroupName = userGroupName;
		this.description = description;
		this.userpermissions = userpermissions;
		this.users = users;
	}

	public Integer getUserGroupId() {
		return this.userGroupId;
	}

	public void setUserGroupId(Integer userGroupId) {
		this.userGroupId = userGroupId;
	}

	public String getUserGroupName() {
		return this.userGroupName;
	}

	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Userpermission> getUserpermissions() {
		return this.userpermissions;
	}

	public void setUserpermissions(Set<Userpermission> userpermissions) {
		this.userpermissions = userpermissions;
	}

	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
