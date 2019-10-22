package com.datageeks.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository	
public class EmployeeDAO {

	@Autowired
	private EntityManager entityManager = null;
	
	
	public void saveOrUpdate(EmployeeBean employeeBean) throws DataSourceException {
		try {
			
			Session session = entityManager.unwrap(Session.class);
			session.saveOrUpdate(employeeBean);

		} catch (HibernateException exp) {
			throw new DataSourceException(exp.getMessage());
		}
	}

	public List<EmployeeBean> getAll() throws DataSourceException {
		List<EmployeeBean> empList = null;
		try {
			Session session = entityManager.unwrap(Session.class);
			empList = session
					.createQuery("Select emp From EmployeeBean as emp", EmployeeBean.class).getResultList();
		} catch (HibernateException exp) {
			throw new DataSourceException(exp.getMessage());
		}
		return empList;
	}

	public EmployeeBean getById(Integer empId) throws DataSourceException {
		EmployeeBean emp = null;
		try {
			Session session = entityManager.unwrap(Session.class);
			TypedQuery<EmployeeBean> query = session
					.createQuery("Select emp From EmployeeBean as emp where emp.employeeId = :id", EmployeeBean.class);
			query.setParameter("id", empId);
			emp = query.getSingleResult();

		} catch (NoResultException exp) {
			return null;
		} catch (HibernateException exp) {
			System.out.println(exp);
			throw new DataSourceException(exp.getMessage());
		}
		return emp;
	}
	
	public void delete(Integer empId) throws DataSourceException {
		try {
			
			Session session = entityManager.unwrap(Session.class);
			EmployeeBean emp = session.get(EmployeeBean.class, empId);
			session.delete(emp);

		} catch (HibernateException exp) {
			throw new DataSourceException(exp.getMessage());
		}
	}

}
