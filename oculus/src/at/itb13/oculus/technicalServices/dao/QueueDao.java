package at.itb13.oculus.technicalServices.dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.persistence.Transient;

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
 * Offers functionality for loading {@link at.itb13.oculus.domain.Queue}s with their {@link at.itb13.oculus.domain.QueueEntry}s.
 * 
 * @author Daniel Scheffknecht
 * @date 11.04.2015
 */
public class QueueDao {

	private static final Logger _logger = LogManager.getLogger(QueueDao.class.getName());

	/**
	 * Opens a new Hibernate session and looks up the Queue with the specified doctorID and orthoptistID.
	 * The conversion between the database table "Queue" and the {@link at.itb13.oculus.domain.Queue}s with 
	 * their {@link at.itb13.oculus.domain.QueueEntry}s is done automatically.
	 * <P>
	 * If both the doctorID and orthoptistID is null, the general orthoptist queue is returned.
	 * 
	 * @param doctorId the id of the queue's doctor. May be null.
	 * @param orthoptistId the id of the queue's orthoptist. May be null.
	 * @return the desired queue if found, or null otherwise
	 */
	public Queue findById(Integer doctorId, Integer orthoptistId) {
		Queue queue = new Queue();
		Criterion doctorRestriction = Restrictions.isNull("doctor.doctorId");
		Criterion orthoptistRestriction = Restrictions
				.isNull("orthoptist.orthoptistId");

		// Load doctor or orthoptist
		if (doctorId != null && orthoptistId != null) {
			throw new IllegalArgumentException();
		} else {
			if (doctorId != null) {
				doctorRestriction = Restrictions
						.eq("doctor.doctorId", doctorId);

				Doctor doctor = new DoctorDao().findById(doctorId);
				if (doctor != null) {
					queue.setDoctor(doctor);

				} else {
					_logger.warn("Failed to find doctor with doctorId '"
							+ doctorId + '"');
					return null;
				}
			} else if (orthoptistId != null) {
				orthoptistRestriction = Restrictions.eq(
						"orthoptist.orthoptistId", orthoptistId);

				Orthoptist orthoptist = new OrthoptistDao()
						.findById(orthoptistId);
				if (orthoptist != null) {
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
		List<QueueEntry> listQueueEntry = convertToQueueEntryList(listQueueEntity);

		queue.setQueueEntries(listQueueEntry);

		return queue;
	}

	/**
	 * Opens a new Hibernate session and loads all {@link at.itb13.oculus.domain.Queue}s into a list.
	 * If no Queues are found, the returned list is empty.
	 * 
	 * Alias: {@link #findAll() findAll}
	 * 
	 * @return a list of all Queues found in the database. May be empty.
	 */
	@SuppressWarnings("unchecked")
	public List<Queue> list() {
		// Load the whole table 'Queue' from the database
		List<QueueEntity> listQueueEntity = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			listQueueEntity = (List<QueueEntity>) session.createCriteria(QueueEntity.class).list();
			
			tx.commit();
		} catch (Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			_logger.error(ex);
		} finally {
			if(session != null) {
				session.close();
			}
		}
		
		// Split the list into lists representing the distinct queues
		Map<Doctor, List<QueueEntity>> mapQueueEntityDoctors = new HashMap<>();
		Map<Orthoptist, List<QueueEntity>> mapQueueEntityOrthoptists = new HashMap<>();
		List<QueueEntity> listQueueEntityGeneralOrthoptists = new LinkedList<>();
		
		for(QueueEntity entity : listQueueEntity) {
			Doctor doctor = entity.getDoctor();
			Orthoptist orthoptist = entity.getOrthoptist();
			if(doctor != null) {
				if(!mapQueueEntityDoctors.containsKey(doctor)) {
					mapQueueEntityDoctors.put(doctor, new LinkedList<>());
				}
				mapQueueEntityDoctors.get(doctor).add(entity);
			} else if(orthoptist != null) {
				if(!mapQueueEntityOrthoptists.containsKey(orthoptist)) {
					mapQueueEntityOrthoptists.put(orthoptist, new LinkedList<>());
				}
				mapQueueEntityOrthoptists.get(orthoptist).add(entity);
			} else {	// General Orthoptist Queue
				listQueueEntityGeneralOrthoptists.add(entity);
			}
		}
		
		// Convert lists to queues
		List<Queue> listAllQueues = new LinkedList<>();
		
		mapQueueEntityDoctors.values().forEach(list -> listAllQueues.add(convertToQueue(list)));
		mapQueueEntityOrthoptists.values().forEach(list -> listAllQueues.add(convertToQueue(list)));
		listAllQueues.add(convertToQueue(listQueueEntityGeneralOrthoptists));
		
		return listAllQueues;
	}

	/**
	 * Opens a new Hibernate session and loads all {@link at.itb13.oculus.domain.Queue}s into a list.
	 * If no Queues are found, the returned list is empty.
	 * 
	 * Alias: {@link #list() list}
	 * 
	 * @return a list of all Queues found in the database. May be empty.
	 */
	public List<Queue> findAll() {
		return list();
	}

	/**
	 * Opens a new Hibernate session and saves or updates the supplied entities.
	 * If there is a problem with a single entity, no entity gets saved or updated at all and false is returned.
	 * The entities are in a detached state after execution.
	 * 
	 * @param entities a list of entities that should be saved or updated
	 * @return true if all entities have been saved or updated; false if no entity has been saved or updated.
	 * @see #makePersistent(Queue... entities)
	 */
	public boolean makePersistent(List<Queue> entities) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			for (Queue queue : entities) {
				List<QueueEntity> listQueueDB = loadQueueEntitiesById(queue
						.getDoctor().getDoctorId(), queue.getOrthoptist()
						.getOrthoptistId());

				Integer prevEntryId = null; // first element has NULL as
											// queueIdParent
				for (QueueEntry entry : queue.getQueueEntries()) {
					QueueEntity entity = convertToQueueEntity(queue, entry, prevEntryId);

					prevEntryId = entity.getQueueId();

					session.saveOrUpdate(entity);

					// remove entity if it exists
					ListIterator<QueueEntity> it = listQueueDB.listIterator();
					while (it.hasNext()) {
						QueueEntity entityDB = it.next();
						if (entityDB.getQueueId().equals(entity.getQueueId())) {
							it.remove();
							break;
						}
					}

					// delete all entries that are currently in the database,
					// but
					// aren't in the queue
					listQueueDB.forEach(session::delete);
				}
			}

			tx.commit();
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			_logger.error(ex);
			return false;
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return true;
	}

	/**
	 * Opens a new Hibernate session and saves or updates the supplied entities.
	 * If there is a problem with a single entity, no entity gets saved or updated at all and false is returned.
	 * The entities are in a detached state after execution.
	 * 
	 * @param entities a list of entities that should be saved or updated
	 * @return true if all entities have been saved or updated; false if no entity has been saved or updated.
	 * @see #makePersistent(List<Queue> entities);
	 */
	public boolean makePersistent(Queue... entities) {
		if (entities == null) {
			_logger.error("No entities supplied. At least 1 entity has to be supplied!");
			return false;
		}

		return makePersistent(Arrays.asList(entities));
	}

	/**
	 * Deletes all supplied queues as identified by their doctorID or orthoptistID.
	 * Important: The whole queue is deleted, not only the QueueEntrys of the supplied Queue!
	 * 
	 * @param entities entities that should be deleted
	 * @return  true if all entities have been deleted; false if no entity has been deleted.
	 * @see #makeTransient(Queue... entities)
	 */
	public boolean makeTransient(List<Queue> entities) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			for (Queue queue : entities) {
				List<QueueEntity> listQueueDB = loadQueueEntitiesById(queue
						.getDoctor().getDoctorId(), queue.getOrthoptist()
						.getOrthoptistId());
				
				listQueueDB.forEach(session::delete);
			}

			tx.commit();
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			_logger.error(ex);
			return false;
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return true;
	}

	/**
	 * Deletes all supplied queues as identified by their doctorID or orthoptistID.
	 * Important: The whole queue is deleted, not only the QueueEntrys of the supplied Queue!
	 * 
	 * @param entities entities that should be deleted
	 * @return  true if all entities have been deleted; false if no entity has been deleted.
	 * @see #makeTransient(List<Queue> entities)
	 */
	public boolean makeTransient(Queue... entities) {
		if(entities == null) {
			_logger.error("No entities supplied. At least 1 entity has to be supplied!");
			return false;
		}
		
		return makeTransient(Arrays.asList(entities));
	}

	/**
	 * TODO
	 * @param doctorId
	 * @param orthoptistId
	 * @return
	 */
	private List<QueueEntity> loadQueueEntitiesById(Integer doctorId,
			Integer orthoptistId) {
		Criterion doctorRestriction = Restrictions.isNull("doctor.doctorId");
		Criterion orthoptistRestriction = Restrictions
				.isNull("orthoptist.orthoptistId");

		// Load doctor or orthoptist
		if (doctorId != null && orthoptistId != null) {
			throw new IllegalArgumentException();
		} else {
			if (doctorId != null) {
				doctorRestriction = Restrictions
						.eq("doctor.doctorId", doctorId);
			} else if (orthoptistId != null) {
				orthoptistRestriction = Restrictions.eq(
						"orthoptist.orthoptistId", orthoptistId);
			}
			// else: general orthoptist queue
		}

		return findByCriteria(doctorRestriction, orthoptistRestriction);
	}

	/**
	 * TODO
	 * @param criterion
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<QueueEntity> findByCriteria(Criterion... criterion) {
		List<QueueEntity> list = null;

		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = session.createCriteria(QueueEntity.class);
			for (Criterion c : criterion) {
				crit.add(c);
			}
			list = crit.list();
		} catch (Exception ex) {
			_logger.error(ex);
		} finally {
			if (session != null) {
				session.close();
			}
		}

		if (list == null) {
			list = new LinkedList<>(); // rather return an empty list than NULL
		}

		return list;
	}
	
	private Queue convertToQueue(List<QueueEntity> entities) {
		if(entities == null || entities.isEmpty()) {
			throw new IllegalArgumentException();
		}
		Doctor doctor = entities.get(0).getDoctor();
		Orthoptist orthoptist = entities.get(0).getOrthoptist();
		Queue queue = new Queue(doctor, orthoptist);
		
		queue.setQueueEntries(convertToQueueEntryList(entities));
		
		return queue;
	}
	
	private List<QueueEntry> convertToQueueEntryList(List<QueueEntity> entities) {
		LinkedList<QueueEntry> listQueueEntry = new LinkedList<>();
		
		for (QueueEntity queueEntity : entities) {
			QueueEntry queueEntry = convertToQueueEntry(queueEntity);

			// TODO: Optimize algorithm
			if (queueEntity.getQueueIdParent() == null) { // front of queue
				listQueueEntry.addFirst(queueEntry);
			} else {
				ListIterator<QueueEntry> it = listQueueEntry.listIterator();
				while (it.hasNext()) {
					QueueEntry curEntry = it.next();
					if (curEntry.getQueueEntryId().equals(
							queueEntity.getQueueIdParent())) {
						it.add(queueEntry);
					}
				}
			}
		}
		
		return listQueueEntry;
	}
	
	private QueueEntry convertToQueueEntry(QueueEntity entity) {
		return new QueueEntry(entity.getQueueId(), entity.getPatient(), entity.getArrivalTime());
	}
	
	private QueueEntity convertToQueueEntity(Queue queue, QueueEntry entry, Integer queueIdParent) {
		QueueEntity entity = new QueueEntity();
		
		entity.setQueueId(entry.getQueueEntryId());
		entity.setDoctor(queue.getDoctor());
		entity.setOrthoptist(queue.getOrthoptist());
		entity.setPatient(entry.getPatient());
		entity.setArrivalTime(entry.getArrivalTime());
		entity.setQueueIdParent(queueIdParent);
		
		return entity;
	}
}
