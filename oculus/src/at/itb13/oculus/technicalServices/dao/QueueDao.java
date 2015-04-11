package at.itb13.oculus.technicalServices.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.Orthoptist;
import at.itb13.oculus.domain.Queue;
import at.itb13.oculus.domain.QueueEntry;
import at.itb13.oculus.technicalServices.GenericDao;
import at.itb13.oculus.technicalServices.entities.QueueEntity;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 11.04.2015
 */
public class QueueDao extends GenericDao<QueueEntity> {

	private static final Logger _logger = LogManager.getLogger(QueueDao.class.getName());
	
	public QueueDao() {
		super(QueueEntity.class);
	}

	/*
	 * @see at.itb13.oculus.technicalServices.GenericDao#findById(java.lang.Integer)
	 */
	@Override
	public QueueEntity findById(Integer id) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * 
	 * @param doctorId
	 * @param orthoptistId
	 * @return null if no doctorid or orid exists
	 */
	public Queue findById(Integer doctorId, Integer orthoptistId) {
		Queue queue = new Queue();
		Criterion doctorRestriction = Restrictions.isNull("doctor.doctorId");
		Criterion orthoptistRestriction = Restrictions.isNull("orthoptist.orthoptistId");
		
		// Load doctor or orthoptist
		if(doctorId != null && orthoptistId != null) {
			throw new IllegalArgumentException();
		} else {
			if(doctorId != null) {
				doctorRestriction = Restrictions.eq("doctor.doctorId", doctorId);
				
				Doctor doctor = new DoctorDao().findById(doctorId);
				if(doctor != null) {
					queue.setDoctor(doctor);
					
				} else {
					_logger.warn("Failed to find doctor with doctorId '" + doctorId + '"');
					return null;
				}
			} else if (orthoptistId != null) {
				orthoptistRestriction = Restrictions.eq("orthoptist.orthoptistId", orthoptistId);
				
				Orthoptist orthoptist = new OrthoptistDao().findById(orthoptistId);
				if(orthoptist != null) {
					queue.setOrthoptist(orthoptist);
				} else {
					_logger.warn("Failed to find orthoptist with orthoptistId '" + orthoptistId + '"');
					return null;
				}
			}
			// else: general orthoptist queue
		}
		
		// Load queue entries
		List<QueueEntity> listQueueEntity = findByCriteria(doctorRestriction, orthoptistRestriction);
		LinkedList<QueueEntry> listQueueEntry = new LinkedList<>();
		
		for(QueueEntity queueEntity : listQueueEntity) {
			QueueEntry queueEntry = new QueueEntry(queueEntity.getQueueId(), queueEntity.getPatient(), queueEntity.getArrivalTime());
			
			// TODO: Optimize algorithm
			if(queueEntity.getQueueIdParent() == null) {	// front of queue
				listQueueEntry.addFirst(queueEntry);
			} else {
				ListIterator<QueueEntry> it = listQueueEntry.listIterator();
				while(it.hasNext()) {
					QueueEntry curEntry = it.next();
					if(curEntry.getQueueEntryId().equals(queueEntity.getQueueIdParent())) {
						it.add(queueEntry);
					}
				}
			}
		}
		
		queue.setQueueEntries(listQueueEntry);
		
		return queue;
	}
}
