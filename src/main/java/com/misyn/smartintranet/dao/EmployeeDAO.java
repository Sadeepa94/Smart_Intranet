package com.misyn.smartintranet.dao;

import java.util.List;

import javax.persistence.Tuple;

import com.misyn.smartintranet.entity.Employee;

public interface EmployeeDAO {
	
	public Employee getEmployee(int id);
	public Employee getEmployeeByUsername(String userName);
	public boolean saveEmployee(Employee employee);
	public boolean updateEmployee(Employee employee);
	public boolean deleteEmployee(String id);
	public List<Tuple> getAllEmployeeDetails(String username);
	public List<Employee> getAllEmployee();
	public List<Tuple> getEmployeesByRoleName(String roleName);
	public String getLastEmployeeUsername();

}
