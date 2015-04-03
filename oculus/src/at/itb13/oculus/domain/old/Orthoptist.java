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
public class Orthoptist implements java.io.Serializable {

	private Integer orthoptistId;
	private Calendar calendar;
	private User user;
	private Set<Queue> queues = new HashSet<Queue>(0);

	public Orthoptist() {
	}

	public Orthoptist(Calendar calendar) {
		this.calendar = calendar;
	}

	public Orthoptist(Calendar calendar, User user, Set<Queue> queues) {
		this.calendar = calendar;
		this.user = user;
		this.queues = queues;
	}

	public Integer getOrthoptistId() {
		return this.orthoptistId;
	}

	public void setOrthoptistId(Integer orthoptistId) {
		this.orthoptistId = orthoptistId;
	}

	public Calendar getCalendar() {
		return this.calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Queue> getQueues() {
		return this.queues;
	}

	public void setQueues(Set<Queue> queues) {
		this.queues = queues;
	}

}
