package at.itb13.teamF.adapter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import at.itb13.oculus.domain.Calendar;
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
 * TODO: Insert description here.
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
		ExaminationProtocol examinationProtocol = _examinationResult
				.getExaminationprotocol();
		return new ExaminationProtocolAdapter(examinationProtocol);
	}

	@Override
	public void setExaminationProtocol(
			IExaminationProtocol examinationProtocolEntity) {
		ExaminationProtocolAdapter examinationProtocolAdapter = (ExaminationProtocolAdapter) examinationProtocolEntity;
		ExaminationProtocol exP = (ExaminationProtocol) examinationProtocolAdapter
				.getDomainObject();
		_examinationResult.setExaminationprotocol(exP);
	}

	@Override
	public IUser getUser() {
		User user = _examinationResult.getUser();
		return new UserAdapter(user);
	}

	@Override
	public void setUser(IUser user) {
		UserAdapter userAdapter = (UserAdapter) user;
		User concreteUser = (User) userAdapter.getDomainObject();
		_examinationResult.setUser(concreteUser);
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
		return new DoctorAdapter(doctor);
	}

	@Override
	public IOrthoptist getOrthoptist() {
		Orthoptist orthoptist = _examinationResult.getUser().getOrthoptist();
		return new OrthoptistAdapter(orthoptist);
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
