package at.itb13.oculus.domain.old;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 03.04.2015
 */
public class Receptionist implements java.io.Serializable {

	private Integer receptionistId;
	private User user;

	public Receptionist() {
	}

	public Receptionist(User user) {
		this.user = user;
	}

	public Integer getReceptionistId() {
		return this.receptionistId;
	}

	public void setReceptionistId(Integer receptionistId) {
		this.receptionistId = receptionistId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
