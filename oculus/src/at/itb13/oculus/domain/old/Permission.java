package at.itb13.oculus.domain.old;


import java.util.HashSet;
import java.util.Set;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 03.04.2015
 */
public class Permission implements java.io.Serializable {

	private Integer permissionId;
	private String permissionName;
	private String description;
	private Set<Userpermission> userpermissions = new HashSet<Userpermission>(0);

	public Permission() {
	}

	public Permission(String permissionName, String description,
			Set<Userpermission> userpermissions) {
		this.permissionName = permissionName;
		this.description = description;
		this.userpermissions = userpermissions;
	}

	public Integer getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionName() {
		return this.permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
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

}
