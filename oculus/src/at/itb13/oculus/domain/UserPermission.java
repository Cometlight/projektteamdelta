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
 * Userpermission generated by hbm2java
 */
@Entity
@Table(name = "userpermission", catalog = "oculusdb")
public class UserPermission implements java.io.Serializable {

	private static final Logger _logger = LogManager.getLogger(UserPermission.class
			.getName());
	private static final long serialVersionUID = 1L;
	private UserPermissionID id;
	private Permission permission;
	private User user;
	private UserGroup usergroup;

	public UserPermission() {
	}

	public UserPermission(UserPermissionID id, Permission permission,
			User user, UserGroup usergroup) {
		this.id = id;
		this.permission = permission;
		this.user = user;
		this.usergroup = usergroup;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "permissionId", column = @Column(name = "permissionId", nullable = false)),
			@AttributeOverride(name = "userId", column = @Column(name = "userId", nullable = false)),
			@AttributeOverride(name = "userGroupId", column = @Column(name = "userGroupId", nullable = false)) })
	public UserPermissionID getId() {
		return this.id;
	}

	public void setId(UserPermissionID id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "permissionId", nullable = false, insertable = false, updatable = false)
	public Permission getPermission() {
		return this.permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false, insertable = false, updatable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userGroupId", nullable = false, insertable = false, updatable = false)
	public UserGroup getUsergroup() {
		return this.usergroup;
	}

	public void setUsergroup(UserGroup usergroup) {
		this.usergroup = usergroup;
	}

}
