package at.oculus.teamf.persistence;

import java.util.Collection;

import at.oculus.teamf.databaseconnection.session.exception.ClassNotMappedException;
import at.oculus.teamf.domain.entity.interfaces.IDomain;
import at.oculus.teamf.persistence.exception.BadConnectionException;
import at.oculus.teamf.persistence.exception.DatabaseOperationException;
import at.oculus.teamf.persistence.exception.FacadeException;
import at.oculus.teamf.persistence.exception.NoBrokerMappedException;
import at.oculus.teamf.persistence.exception.search.InvalidSearchParameterException;
import at.oculus.teamf.persistence.exception.search.SearchInterfaceNotImplementedException;

/**
 * TODO: Insert description here.
 * 
 * @author Andrew Sparr
 * @date 18.05.2015
 */
public class PersistenceFacade implements IFacade {

	@Override
	public <T> T getById(Class clazz, int id) throws BadConnectionException,
			NoBrokerMappedException, DatabaseOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Collection<T> getAll(Class clazz) throws BadConnectionException,
			NoBrokerMappedException, DatabaseOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(IDomain obj) throws BadConnectionException,
			NoBrokerMappedException, DatabaseOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveAll(Collection<IDomain> obj)
			throws BadConnectionException, NoBrokerMappedException,
			DatabaseOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(IDomain obj) throws BadConnectionException,
			NoBrokerMappedException, InvalidSearchParameterException,
			DatabaseOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAll(Collection<IDomain> obj) throws FacadeException,
			ClassNotMappedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> Collection<T> search(Class clazz, String... search)
			throws SearchInterfaceNotImplementedException,
			BadConnectionException, NoBrokerMappedException,
			InvalidSearchParameterException, DatabaseOperationException {
		// TODO Auto-generated method stub
		return null;
	}

}
