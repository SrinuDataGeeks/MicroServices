package com.datageeks.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datageeks.dao.DataSourceException;
import com.datageeks.dao.User;
import com.datageeks.dao.UserDAO;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO = null;

	@Transactional(value = TxType.REQUIRED,rollbackOn = ServicessException.class)
	public User update(User user) throws ServicessException {
		try {
			userDAO.update(user);
		} catch (DataSourceException e) {
			throw new ServicessException(e.getMessage());
		}
		return user;
	}
	
	@Transactional(value = TxType.REQUIRED,rollbackOn = ServicessException.class)
	public User save(User user) throws ServicessException {
		try {
			userDAO.save(user);
		} catch (DataSourceException e) {
			throw new ServicessException(e.getMessage());
		}
		return user;
	}

	public User get(String userId) throws ServicessException {
		User user = null;
		try {
			user = userDAO.get(userId);
		} catch (DataSourceException e) {
			throw new ServicessException(e.getMessage());
		}
		return user;
	}

	public List<User> getAll() throws ServicessException {
		List<User> userList = null;
		try {
			userList = userDAO.getAll();
		} catch (DataSourceException e) {
			throw new ServicessException(e.getMessage());
		}
		return userList;
	}
	
	@Transactional(value = TxType.REQUIRED,rollbackOn = ServicessException.class)	
	public void delete(String userId) throws ServicessException {
		try {
			userDAO.delete(userId);
		} catch (DataSourceException e) {
			throw new ServicessException(e.getMessage());
		}
	}

}
