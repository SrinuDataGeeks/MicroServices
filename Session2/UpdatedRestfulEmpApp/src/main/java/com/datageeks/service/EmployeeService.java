package com.datageeks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.datageeks.dao.DataSourceException;
import com.datageeks.dao.EmployeeBean;
import com.datageeks.dao.EmployeeDAO;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO = null;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServicesException.class)
	public void saveOrUpdate(EmployeeBean employeeBean) throws ServicesException {
		try {
			employeeDAO.saveOrUpdate(employeeBean);
		} catch (DataSourceException exp) {
			throw new ServicesException(exp.getMessage());
		}
	}

	public List<EmployeeBean> getAll() throws ServicesException {
		List<EmployeeBean> empList = null;
		try {
			empList = employeeDAO.getAll();
		} catch (DataSourceException exp) {
			throw new ServicesException(exp.getMessage());
		}

		return empList;
	}

	public EmployeeBean getById(Integer empId) throws ServicesException {
		EmployeeBean emp = null;
		try {
			emp = employeeDAO.getById(empId);
		} catch (DataSourceException exp) {
			throw new ServicesException(exp.getMessage());
		}

		return emp;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServicesException.class)
	public void delete(Integer empId) throws ServicesException {
		try {
			employeeDAO.delete(empId);
		} catch (DataSourceException exp) {
			throw new ServicesException(exp.getMessage());
		}
	}

}
