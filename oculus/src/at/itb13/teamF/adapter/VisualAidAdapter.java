package at.itb13.teamF.adapter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import at.itb13.oculus.domain.Calendar;
import at.itb13.oculus.domain.Diagnosis;
import at.itb13.oculus.domain.VisualAid;
import at.itb13.teamF.interfaces.IAdapter;
import at.oculus.teamf.domain.entity.interfaces.IDiagnosis;
import at.oculus.teamf.domain.entity.interfaces.IVisualAid;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 18.05.2015
 */
public class VisualAidAdapter implements IVisualAid, IAdapter {
	
	private VisualAid _visualAid;
	
	public VisualAidAdapter(){}
	public  VisualAidAdapter(VisualAid visualAid){
		_visualAid = visualAid;
	}
	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IDomain#getId()
	 */
	@Override
	public int getId() {
		return _visualAid.getVisualAidId();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IDomain#setId(int)
	 */
	@Override
	public void setId(int id) {
		_visualAid.setVisualAidId(id);
	}

	/*
	 * @see at.itb13.teamF.interfaces.IAdapter#getDomainObject()
	 */
	@Override
	public Object getDomainObject() {
		return _visualAid;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IVisualAid#setDescription(java.lang.String)
	 */
	@Override
	public void setDescription(String description) {
		_visualAid.setDescription(description);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IVisualAid#setLastPrint(java.util.Date)
	 */
	@Override
	public void setLastPrint(Date lastPrint) {
		LocalDateTime localDateTime = lastPrint.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		_visualAid.setLastPrintDate(localDateTime);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IVisualAid#setDiagnosis(at.oculus.teamf.domain.entity.interfaces.IDiagnosis)
	 */
	@Override
	public void setDiagnosis(IDiagnosis diagnosis) {
		DiagnosisAdapter diagnosisAdapter = (DiagnosisAdapter) diagnosis;
		Diagnosis dia = (Diagnosis) diagnosisAdapter.getDomainObject();
		_visualAid.setDiagnosis(dia);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IVisualAid#setIssueDate(java.util.Date)
	 */
	@Override
	public void setIssueDate(Date issueDate) {
		LocalDateTime localDateTime = issueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		_visualAid.setIssueDate(localDateTime);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IVisualAid#setDioptreLeft(java.lang.Float)
	 */
	@Override
	public void setDioptreLeft(Float dioptreLeft) {
		_visualAid.setDioptreLeft((double) dioptreLeft);		//TODO: is this ok to cast?
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IVisualAid#setDioptreRight(java.lang.Float)
	 */
	@Override
	public void setDioptreRight(Float dioptreRight) {
		_visualAid.setDioptreRight((double) dioptreRight);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IVisualAid#getLastPrint()
	 */
	@Override
	public Date getLastPrint() {
		LocalDateTime localDateTime = _visualAid.getLastPrintDate();
		Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());		
		return date;
	}

}
