package com.datageeks.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datageeks.dao.DataSourceException;
import com.datageeks.dao.UserAccount;
import com.datageeks.dao.USERACCOUNTDAO;

@Service
public class UserAccountService {

	@Autowired
	private USERACCOUNTDAO userAccountDAO = null;

	@Transactional(value = TxType.REQUIRED,rollbackOn = ServicessException.class)
	public UserAccount update(UserAccount user) throws ServicessException {
		try {
			userAccountDAO.update(user);
		} catch (DataSourceException e) {
			throw new ServicessException(e.getMessage());
		}
		return user;
	}
	
	@Transactional(value = TxType.REQUIRED,rollbackOn = ServicessException.class)
	public UserAccount save(UserAccount user) throws ServicessException {
		try {
			userAccountDAO.save(user);
		} catch (DataSourceException e) {
			throw new ServicessException(e.getMessage());
		}
		return user;
	}

	public UserAccount get(String userId) throws ServicessException {
		UserAccount user = null;
		try {
			user = userAccountDAO.get(userId);
		} catch (DataSourceException e) {
			throw new ServicessException(e.getMessage());
		}
		return user;
	}

	public List<UserAccount> getAll() throws ServicessException {
		List<UserAccount> userList = null;
		try {
			userList = userAccountDAO.getAll();
		} catch (DataSourceException e) {
			throw new ServicessException(e.getMessage());
		}
		return userList;
	}
	
	@Transactional(value = TxType.REQUIRED,rollbackOn = ServicessException.class)	
	public void delete(String userId) throws ServicessException {
		try {
			userAccountDAO.delete(userId);
		} catch (DataSourceException e) {
			throw new ServicessException(e.getMessage());
		}
	}

}
