package com.misyn.smartintranet.service;

import java.util.List;

import javax.persistence.Tuple;

import com.misyn.smartintranet.entity.Employee;
import com.misyn.smartintranet.entity.Type;

public interface EmployeeService {
	
	public Employee getEmployee(int id);
	public Employee getEmployeeByUsername(String userName);
	public boolean saveEmployee(Employee employee);
	public boolean updateEmployee(Employee employee);
	public boolean deleteEmployee(String id);
	public List<Tuple> getAllEmployeeDetails(String userName);
	public List<Employee> getAllEmployee();
	public List<Tuple>  getEmployeesByRoleName(String roleName);
	public String getNextEmployeeUsername();
	public void changeTypeAndUpdate(Employee employee , Type newtype);
}
