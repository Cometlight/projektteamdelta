package at.itb13.teamF.adapter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import at.itb13.oculus.domain.User;
import at.itb13.teamF.interfaces.IAdapter;
import at.oculus.teamf.domain.entity.interfaces.IUser;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 18.05.2015
 */
public class UserAdapter implements IAdapter, IUser{
	private User _user;
	
	public UserAdapter(){}
	
	public UserAdapter(User user){
		_user = user;
	}
	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#getUserId()
	 */
	@Override
	public int getUserId() {
		return _user.getUserId();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#setUserId(int)
	 */
	@Override
	public void setUserId(int id) {
		_user.setUserId(id);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#getUserGroupId()
	 */
	@Override
	public Integer getUserGroupId() {
		return _user.getUsergroup().getUserGroupId();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#setUserGroupId(java.lang.Integer)
	 */
	@Override
	public void setUserGroupId(Integer userGroupId) {
		_user.getUsergroup().setUserGroupId(userGroupId);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#getUserName()
	 */
	@Override
	public String getUserName() {
		return _user.getUserName();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#setUserName(java.lang.String)
	 */
	@Override
	public void setUserName(String userName) {
		_user.setUserName(userName);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#getPassword()
	 */
	@Override
	public String getPassword() {
		return _user.getPassword();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#setPassword(java.lang.String)
	 */
	@Override
	public void setPassword(String password) {
		_user.setPassword(password);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#getTitle()
	 */
	@Override
	public String getTitle() {
		return _user.getTitle();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#setTitle(java.lang.String)
	 */
	@Override
	public void setTitle(String title) {
		_user.setTitle(title);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#getFirstName()
	 */
	@Override
	public String getFirstName() {
		return _user.getFirstName();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#setFirstName(java.lang.String)
	 */
	@Override
	public void setFirstName(String firstName) {
		_user.setFirstName(firstName);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#getLastName()
	 */
	@Override
	public String getLastName() {
		return _user.getLastName();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#setLastName(java.lang.String)
	 */
	@Override
	public void setLastName(String lastName) {
		_user.setLastName(lastName);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#getEmail()
	 */
	@Override
	public String getEmail() {
		return _user.getEmail();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#setEmail(java.lang.String)
	 */
	@Override
	public void setEmail(String email) {
		_user.setEmail(email);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#getCreateDate()
	 */
	@Override
	public Date getCreateDate() {
		LocalDateTime localDateTime = _user.getCreateDate();
		Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());		
		return date;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#setCreateDate(java.util.Date)
	 */
	@Override
	public void setCreateDate(Date createDate) {
		LocalDateTime localDateTime = createDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		_user.setCreateDate(localDateTime);		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#getIdleDate()
	 */
	@Override
	public Date getIdleDate() {
		LocalDateTime localDateTime = _user.getIdleDate();
		Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());		
		return date;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IUser#setIdleDate(java.util.Date)
	 */
	@Override
	public void setIdleDate(Date idleDate) {
		LocalDateTime localDateTime = idleDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		_user.setCreateDate(localDateTime);	
		
	}

	/*
	 * @see at.itb13.teamF.interfaces.IAdapter#getDomainObject()
	 */
	@Override
	public Object getDomainObject() {
		// TODO Auto-generated method stub
		return null;
	}

}
