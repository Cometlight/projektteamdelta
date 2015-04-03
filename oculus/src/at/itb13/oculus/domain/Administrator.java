package at.itb13.oculus.domain;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 03.04.2015
 */
public class Administrator implements java.io.Serializable {

	private Integer administratorId;
	private User user;

	public Administrator() {
	}

	public Administrator(User user) {
		this.user = user;
	}

	public Integer getAdministratorId() {
		return this.administratorId;
	}

	public void setAdministratorId(Integer administratorId) {
		this.administratorId = administratorId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
