package at.itb13.oculus.domain;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 23.04.2015
 */
@Embeddable
public class UserPermissionID implements java.io.Serializable {
	private static final Logger _logger = LogManager.getLogger(UserPermissionID.class.getName());
	private static final long serialVersionUID = 1L;
	
	private int _permissionId;
	private int _userId;
	private int _userGroupId;

	public UserPermissionID() { }

	public UserPermissionID(int permissionId, int userId, int userGroupId) {
		_permissionId = permissionId;
		_userId = userId;
		_userGroupId = userGroupId;
	}

	@Column(name = "permissionId", nullable = false)
	public int getPermissionId() {
		return _permissionId;
	}

	public void setPermissionId(int permissionId) {
		_permissionId = permissionId;
	}

	@Column(name = "userId", nullable = false)
	public int getUserId() {
		return _userId;
	}

	public void setUserId(int userId) {
		_userId = userId;
	}

	@Column(name = "userGroupId", nullable = false)
	public int getUserGroupId() {
		return _userGroupId;
	}

	public void setUserGroupId(int userGroupId) {
		_userGroupId = userGroupId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserPermissionID))
			return false;
		UserPermissionID castOther = (UserPermissionID) other;

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
