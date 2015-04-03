package at.itb13.oculus.domain;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 03.04.2015
 */
public class UserpermissionId implements java.io.Serializable {

	private int permissionId;
	private int userId;
	private int userGroupId;

	public UserpermissionId() {
	}

	public UserpermissionId(int permissionId, int userId, int userGroupId) {
		this.permissionId = permissionId;
		this.userId = userId;
		this.userGroupId = userGroupId;
	}

	public int getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserGroupId() {
		return this.userGroupId;
	}

	public void setUserGroupId(int userGroupId) {
		this.userGroupId = userGroupId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserpermissionId))
			return false;
		UserpermissionId castOther = (UserpermissionId) other;

		return (this.getPermissionId() == castOther.getPermissionId())
				&& (this.getUserId() == castOther.getUserId())
				&& (this.getUserGroupId() == castOther.getUserGroupId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getPermissionId();
		result = 37 * result + this.getUserId();
		result = 37 * result + this.getUserGroupId();
		return result;
	}

}
