package com.datageeks.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")

public class EmployeeBean implements Serializable {

	@Id
	@Column(name = "EMP_ID")
	private Integer employeeId = null;
	
	@Column(name = "EMP_NAME")
	private String employeeName = null;
	
	@Column(name = "EMP_SAL")
	private Float employeeSal = null;

	
	public EmployeeBean() {
	}

	

	public EmployeeBean(Integer employeeId, String employeeName, Float employeeSal) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeSal = employeeSal;
	}



	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Float getEmployeeSal() {
		return employeeSal;
	}

	public void setEmployeeSal(Float employeeSal) {
		this.employeeSal = employeeSal;
	}

	@Override
	public String toString() {
		return "EmployeeBean [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeeSal="
				+ employeeSal + "]";
	}

}
