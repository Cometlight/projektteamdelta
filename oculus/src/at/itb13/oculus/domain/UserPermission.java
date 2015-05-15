package at.itb13.oculus.domain;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "userpermission", catalog = "oculus_d")
public class UserPermission implements java.io.Serializable {
	@SuppressWarnings("unused")
	private static final Logger _logger = LogManager.getLogger(UserPermission.class.getName());
	private static final long serialVersionUID = 1L;
	
	private UserPermissionID _id;
	private Permission _permission;
	private User _user;
	private UserGroup _usergroup;

	public UserPermission() {
	}

	public UserPermission(UserPermissionID id, Permission permission,
			User user, UserGroup usergroup) {
		_id = id;
		_permission = permission;
		_user = user;
		_usergroup = usergroup;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "permissionId", column = @Column(name = "permissionId", nullable = false)),
			@AttributeOverride(name = "userId", column = @Column(name = "userId", nullable = false)),
			@AttributeOverride(name = "userGroupId", column = @Column(name = "userGroupId", nullable = false)) })
	public UserPermissionID getId() {
		return _id;
	}

	public void setId(UserPermissionID id) {
		_id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "permissionId", nullable = false, insertable = false, updatable = false)
	public Permission getPermission() {
		return _permission;
	}

	public void setPermission(Permission permission) {
		_permission = permission;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false, insertable = false, updatable = false)
	public User getUser() {
		return _user;
	}

	public void setUser(User user) {
		_user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userGroupId", nullable = false, insertable = false, updatable = false)
	public UserGroup getUsergroup() {
		return _usergroup;
	}

	public void setUsergroup(UserGroup usergroup) {
		_usergroup = usergroup;
	}

}
