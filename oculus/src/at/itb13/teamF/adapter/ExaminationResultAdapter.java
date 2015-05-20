package at.itb13.teamF.adapter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.ExaminationProtocol;
import at.itb13.oculus.domain.ExaminationResult;
import at.itb13.oculus.domain.Orthoptist;
import at.itb13.oculus.domain.User;
import at.itb13.teamF.interfaces.IAdapter;
import at.oculus.teamf.domain.entity.interfaces.IDoctor;
import at.oculus.teamf.domain.entity.interfaces.IExaminationProtocol;
import at.oculus.teamf.domain.entity.interfaces.IExaminationResult;
import at.oculus.teamf.domain.entity.interfaces.IOrthoptist;
import at.oculus.teamf.domain.entity.interfaces.IUser;

/**
 * Implementation of IExaminationResult of Team F.
 * 
 * @author Andrew Sparr
 * @date 19.05.2015
 */
public class ExaminationResultAdapter implements IAdapter, IExaminationResult {

	private ExaminationResult _examinationResult;

	public ExaminationResultAdapter() {

	}

	public ExaminationResultAdapter(ExaminationResult examinationResult) {
		_examinationResult = examinationResult;
	}

	@Override
	public int getId() {
		return _examinationResult.getExaminationResultId();
	}

	@Override
	public void setId(int id) {
		_examinationResult.setExaminationResultId(id);
	}

	@Override
	public IExaminationProtocol getExaminationProtocol() {
		ExaminationProtocol examinationProtocol = _examinationResult.getExaminationprotocol();
		if(examinationProtocol != null){
			return new ExaminationProtocolAdapter(examinationProtocol);
		}
		return null;
	}

	@Override
	public void setExaminationProtocol(
			IExaminationProtocol examinationProtocolEntity) {
		if(examinationProtocolEntity != null){
			ExaminationProtocolAdapter examinationProtocolAdapter = (ExaminationProtocolAdapter) examinationProtocolEntity;
			ExaminationProtocol exP = (ExaminationProtocol) examinationProtocolAdapter
					.getDomainObject();
			_examinationResult.setExaminationprotocol(exP);
		}
	}

	@Override
	public IUser getUser() {
		User user = _examinationResult.getUser();
		if(user != null){
			return new UserAdapter(user);
		}
		return null;
	}

	@Override
	public void setUser(IUser user) {
		if(user != null){
			UserAdapter userAdapter = (UserAdapter) user;
			User concreteUser = (User) userAdapter.getDomainObject();
			_examinationResult.setUser(concreteUser);
		}
	}

	@Override
	public Integer getUserId() {
		return _examinationResult.getUser().getUserId();
	}

	@Override
	public void setUserId(Integer userId) {
		_examinationResult.getUser().setUserId(userId);
	}

	@Override
	public String getResult() {
		return _examinationResult.getResult();
	}

	@Override
	public void setResult(String result) {
		_examinationResult.setResult(result);
	}

	@Override
	public Date getCreateDate() {
		LocalDateTime localDateTime = _examinationResult.getCreateDate();
		Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault())
				.toInstant());
		return date;
	}

	@Override
	public String getDevice() {
		return _examinationResult.getDevice();
	}

	@Override
	public byte[] getDeviceData() {
		return _examinationResult.getDeviceData();
	}

	@Override
	public IDoctor getDoctor() {
		Doctor doctor = _examinationResult.getUser().getDoctor();
		if(doctor != null){
			return new DoctorAdapter(doctor);
		} 
		return null;
	}

	@Override
	public IOrthoptist getOrthoptist() {
		Orthoptist orthoptist = _examinationResult.getUser().getOrthoptist();
		if(orthoptist != null){
			return new OrthoptistAdapter(orthoptist);
		}
		return null;
	}

	@Override
	public Object getDomainObject() {
		return _examinationResult;
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return _examinationResult.getResult();
	}
}
