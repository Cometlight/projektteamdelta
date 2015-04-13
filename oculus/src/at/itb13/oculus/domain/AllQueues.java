package at.itb13.oculus.domain;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.technicalServices.dao.QueueDao;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 11.04.2015
 */
public class AllQueues {
	
	private static final Logger _logger = LogManager.getLogger(AllQueues.class
			.getName());
	private static AllQueues _allQueues;
	private List<Queue> _queues;
	
	static {
		_allQueues = new AllQueues();
		_allQueues._queues = new LinkedList<>();
	}
	
	private AllQueues() { }
	
	public Queue getQueueByDoctorId(Integer doctorId) {
		for(Queue q : _queues) {
			if(q.getDoctor().equals(doctorId)) {
				return q;
			}
		}
		
		// not loaded yet? try to load:
		Queue queue = new QueueDao().findById(doctorId, null);
		if(queue != null) {
			_queues.add(queue);
		}
		
		return queue;
	}
	
	public Queue getQueueByOrthoptistId(Integer orthoptistId) {
		for(Queue q : _queues) {
			if(q.getOrthoptist().equals(orthoptistId)) {
				return q;
			}
		}
		
		// not loaded yet? try to load:
		Queue queue = new QueueDao().findById(null, orthoptistId);
		if(queue != null) {
			_queues.add(queue);
		}
		
		return queue;
	}
	
	public static AllQueues getInstance() {
		return _allQueues;
	}
}
