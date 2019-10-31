package com.datageeks.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class USERACCOUNTDAO {

	@Autowired
	private EntityManager entityManager = null;

	public UserAccount update(UserAccount user) throws DataSourceException {
		try {
			entityManager.unwrap(Session.class).update(user);
		} catch (Exception e) {
			throw new DataSourceException(e.getMessage());
		}
		return user;
	}
	
	public UserAccount save(UserAccount user) throws DataSourceException {
		try {
			entityManager.unwrap(Session.class).save(user);
		} catch (Exception e) {
			throw new DataSourceException(e.getMessage());
		}
		return user;
	}

	public UserAccount get(String userId) throws DataSourceException {
		UserAccount user = null;
		try {
			user = entityManager.unwrap(Session.class).get(UserAccount.class, userId);
		} catch (Exception e) {
			throw new DataSourceException(e.getMessage());
		}
		return user;
	}

	public List<UserAccount> getAll() throws DataSourceException {
		List<UserAccount> userList = null;
		try {
			userList = entityManager.unwrap(Session.class).createQuery("Select u From UserAccount as u", UserAccount.class)
					.getResultList();
		} catch (Exception e) {
			throw new DataSourceException(e.getMessage());
		}
		return userList;
	}

	public void delete(String userId) throws DataSourceException {
		try {
			Session session = entityManager.unwrap(Session.class);
			UserAccount user = session.get(UserAccount.class, userId);
			if (user != null)
				session.delete(user);
		} catch (Exception e) {
			throw new DataSourceException(e.getMessage());
		}
	}
}
