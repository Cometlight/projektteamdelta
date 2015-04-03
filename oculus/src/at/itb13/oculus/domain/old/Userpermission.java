package at.itb13.oculus.domain.old;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 03.04.2015
 */
public class Userpermission implements java.io.Serializable {

	private UserpermissionId id;
	private Permission permission;
	private User user;
	private Usergroup usergroup;

	public Userpermission() {
	}

	public Userpermission(UserpermissionId id, Permission permission,
			User user, Usergroup usergroup) {
		this.id = id;
		this.permission = permission;
		this.user = user;
		this.usergroup = usergroup;
	}

	public UserpermissionId getId() {
		return this.id;
	}

	public void setId(UserpermissionId id) {
		this.id = id;
	}

	public Permission getPermission() {
		return this.permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Usergroup getUsergroup() {
		return this.usergroup;
	}

	public void setUsergroup(Usergroup usergroup) {
		this.usergroup = usergroup;
	}

}
