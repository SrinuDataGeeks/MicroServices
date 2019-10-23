package com.datageeks.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

	@Autowired
	private EntityManager entityManager = null;

	public User update(User user) throws DataSourceException {
		try {
			entityManager.unwrap(Session.class).update(user);
		} catch (Exception e) {
			throw new DataSourceException(e.getMessage());
		}
		return user;
	}
	
	public User save(User user) throws DataSourceException {
		try {
			entityManager.unwrap(Session.class).save(user);
		} catch (Exception e) {
			throw new DataSourceException(e.getMessage());
		}
		return user;
	}

	public User get(String userId) throws DataSourceException {
		User user = null;
		try {
			user = entityManager.unwrap(Session.class).get(User.class, userId);
		} catch (Exception e) {
			throw new DataSourceException(e.getMessage());
		}
		return user;
	}

	public List<User> getAll() throws DataSourceException {
		List<User> userList = null;
		try {
			userList = entityManager.unwrap(Session.class).createQuery("Select u From User as u", User.class)
					.getResultList();
		} catch (Exception e) {
			throw new DataSourceException(e.getMessage());
		}
		return userList;
	}

	public void delete(String userId) throws DataSourceException {
		try {
			Session session = entityManager.unwrap(Session.class);
			User user = session.get(User.class, userId);
			if (user != null)
				session.delete(user);
		} catch (Exception e) {
			throw new DataSourceException(e.getMessage());
		}
	}
}
