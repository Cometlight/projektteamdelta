package at.itb13.oculus.technicalServices.dao;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.Orthoptist;
import at.itb13.oculus.domain.Queue;
import at.itb13.oculus.domain.QueueEntry;
import at.itb13.oculus.technicalServices.DAO;
import at.itb13.oculus.technicalServices.GenericDao;
import at.itb13.oculus.technicalServices.HibernateUtil;
import at.itb13.oculus.technicalServices.entities.QueueEntity;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 11.04.2015
 */
public class QueueDao implements DAO<Queue> {

	private static final Logger _logger = LogManager.getLogger(QueueDao.class.getName());
	
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
	
	/*
	 * @see at.itb13.oculus.technicalServices.DAO#findById(java.lang.Integer)
	 */
	@Override
	public Queue findById(Integer id) {
		throw new UnsupportedOperationException();
	}

	/*
	 * @see at.itb13.oculus.technicalServices.DAO#list()
	 */
	@Override
	public List<Queue> list() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see at.itb13.oculus.technicalServices.DAO#findAll()
	 */
	@Override
	public List<Queue> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see at.itb13.oculus.technicalServices.DAO#makePersistent(java.util.List)
	 */
	@Override
	public boolean makePersistent(List<Queue> entities) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			
			for(Queue queue: entities) {
				List<QueueEntity> listQueueDB = loadQueueEntitiesById(queue.getDoctor().getDoctorId(), queue.getOrthoptist().getOrthoptistId());
				
				Integer prevEntryId = null;	// first element has NULL as queueIdParent
				for(QueueEntry entry : queue.getQueueEntries()) {
					QueueEntity entity = new QueueEntity();
					entity.setQueueId(entry.getQueueEntryId());
					entity.setDoctor(queue.getDoctor());
					entity.setOrthoptist(queue.getOrthoptist());
					entity.setPatient(entry.getPatient());
					entity.setArrivalTime(entry.getArrivalTime());
					entity.setQueueIdParent(prevEntryId);
					
					prevEntryId = entity.getQueueId();
					
					session.saveOrUpdate(entity);
					
					// remove entity if it exists
					ListIterator<QueueEntity> it = listQueueDB.listIterator();
					while(it.hasNext()) {
						QueueEntity entityDB = it.next();
						if(entityDB.getQueueId().equals(entity.getQueueId())) {
							it.remove();
							break;
						}
					}
					
					// delete all entries that are currently in the database, but 
					// aren't in the queue
					listQueueDB.forEach(session::delete);
				}
			}
			
			tx.commit();
		} catch (Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			_logger.error(ex);
			return false;
		} finally {
			if(session != null) {
				session.close();
			}
		}
		
		return true;
	}

	/*
	 * @see at.itb13.oculus.technicalServices.DAO#makePersistent(java.lang.Object[])
	 */
	@Override
	public boolean makePersistent(Queue... entities) {
		if(entities == null) {
			_logger.error("No entities supplied. At least 1 entity has to be supplied!");
			return false;
		}
		
		return makePersistent(Arrays.asList(entities));
	}

	/*
	 * @see at.itb13.oculus.technicalServices.DAO#makeTransient(java.util.List)
	 */
	@Override
	public boolean makeTransient(List<Queue> entities) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * @see at.itb13.oculus.technicalServices.DAO#makeTransient(java.lang.Object[])
	 */
	@Override
	public boolean makeTransient(Queue... entitites) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private List<QueueEntity> loadQueueEntitiesById(Integer doctorId, Integer orthoptistId) {
		Criterion doctorRestriction = Restrictions.isNull("doctor.doctorId");
		Criterion orthoptistRestriction = Restrictions.isNull("orthoptist.orthoptistId");
		
		// Load doctor or orthoptist
		if(doctorId != null && orthoptistId != null) {
			throw new IllegalArgumentException();
		} else {
			if(doctorId != null) {
				doctorRestriction = Restrictions.eq("doctor.doctorId", doctorId);
			} else if (orthoptistId != null) {
				orthoptistRestriction = Restrictions.eq("orthoptist.orthoptistId", orthoptistId);
			}
			// else: general orthoptist queue
		}
		
		return findByCriteria(doctorRestriction, orthoptistRestriction);
	}
	
	@SuppressWarnings("unchecked")
	private List<QueueEntity> findByCriteria(Criterion... criterion) {
		List<QueueEntity> list = null;
		
		Session session = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = session.createCriteria(QueueEntity.class);
			for(Criterion c : criterion) {
				crit.add(c);
			}
			list = crit.list();
		} catch (Exception ex) {
			_logger.error(ex);
		} finally {
			if(session != null) {
				session.close();
			}
		}
		
		if(list == null) {
			list = new LinkedList<>();	// rather return an empty list than NULL
		}
		
		return list;
	}
}
