package at.itb13.oculus.technicalServices.dao;

import java.util.Collections;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.domain.Orthoptist;
import at.itb13.oculus.domain.Queue;
import at.itb13.oculus.technicalServices.GenericDao;

/**
 * This class is used to load and save {@link at.itb13.oculus.domain.Orthoptist}s, 
 * as well as load collections that have not been loaded from the database yet (in case of lazy loading).
 * 
 * @author Daniel Scheffknecht
 * @date 07.04.2015
 */
public class OrthoptistDao extends GenericDao<Orthoptist> {

	private static final Logger _logger = LogManager.getLogger(OrthoptistDao.class.getName());
	
	/**
	 * @see GenericDao#GenericDao(Class);
	 */
	public OrthoptistDao() {
		super(Orthoptist.class);
	}
	
	/**
	 * Loads the collection from the database into the entity.
	 * No changes are made to the database.
	 * <p>
	 * Example:
	 * 		loadQueues(orthoptist);
	 * 		queues = orthoptist.getQueues();
	 * 
	 * @param orthoptist The Orthoptist whose queues should be loaded. It must not be in a transient state!
	 * @return The Queues that have been loaded. Returns {@link java.util.Collections#emptySet()} in case of failure.
	 * @see GenericDao#loadCollection(T entity, Collection<?> collection)
	 */
	public Set<Queue> loadQueues(Orthoptist orthoptist) {
		try {
			loadCollection(orthoptist, orthoptist.getQueues());
		} catch (Exception e) {
			_logger.error(e);
			return Collections.emptySet();
		}
		
		return orthoptist.getQueues();
	}
	
}
