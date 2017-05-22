package com.misyn.smartintranet.daoImp;

import java.util.List;


import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.misyn.smartintranet.dao.EmployeeDAO;
import com.misyn.smartintranet.entity.Employee;



@Repository
public class EmployeeDAOimp implements EmployeeDAO{
	
	@Autowired
	SessionFactory session;
	
	@Override
	public Employee getEmployee(int id) {
		try{
			Employee employee=session.getCurrentSession().get(Employee.class,id);
			return employee;
		}
		catch(Exception ex){
			return null;
		}
	}

	@Override
	public Employee getEmployeeByUsername(String userName) {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery<Employee> criteria = builder.createQuery( Employee.class );
			Root<Employee> root = criteria.from( Employee.class );
			criteria.select( root );
			criteria.where( builder.equal( root.get("username"), userName ) );

			Employee employee = session.getCurrentSession().createQuery( criteria ).getSingleResult();
			return employee;
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			return null;
		}

	}

	@Override
	public boolean saveEmployee(Employee employee) {
		try{
			session.getCurrentSession().save(employee);
			return true;
		}
		catch(Exception ex){
		return false;
		}
	}
	
	@Override
	public boolean updateEmployee(Employee employee) {
		try{
			session.getCurrentSession().update("Employee", employee);;
			return true;
		}
		catch(Exception ex){
		return false;
		}
	}

	@Override
	public boolean deleteEmployee(String id) {
		try{
			Employee employee=session.getCurrentSession().get(Employee.class,id);
			session.getCurrentSession().delete(employee);
			return true;
		}
		catch(Exception ex){
			return false;
		}
		
	}

	@Override
	public List<Tuple> getAllEmployeeDetails(String username) {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteria = builder.createTupleQuery();
			Root<Employee> root = criteria.from( Employee.class );
			criteria.multiselect(root.get("username"),root.get("email"),root.get("first_name"),root.get("role").get("roleName"),root.get("contact_no"));
			criteria.where( builder.like( root.get("username"), username ) );
			criteria.orderBy(builder.desc(root.get("username")));
			List<Tuple> roles = session.getCurrentSession().createQuery( criteria ).getResultList();

			
			return roles;
		}
		catch(Exception ex)
		{
			
			return null;
		}
	}
	
	@Override
	public List<Tuple> getEmployeesByRoleName(String roleName) {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery<Tuple> criteria = builder.createQuery( Tuple.class );
			Root<Employee> root = criteria.from( Employee.class );
			
			criteria.multiselect(root.get("id"),root.get("username"),root.get("first_name"),root.get("profilepicture"));
			criteria.where( builder.like( root.get("role").get("roleName"),roleName));
			
			List<Tuple> usernamelist = session.getCurrentSession().createQuery( criteria ).getResultList();
			return usernamelist;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		

		}
	}

	@Override
	public List<Employee> getAllEmployee() {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Employee> criteria = builder.createQuery( Employee.class );
			Root<Employee> root = criteria.from( Employee.class );
			criteria.select( root );
			List<Employee> Employees = session.getCurrentSession().createQuery( criteria ).getResultList();
			return Employees;
		}
		catch(Exception ex)
		{
			return null;
		}
	}

	@Override
	public String getLastEmployeeUsername() {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Employee> criteria = builder.createQuery( Employee.class );
			Root<Employee> root = criteria.from( Employee.class );
			criteria.select( root );
			criteria.orderBy(builder.asc(root.get("id")));
			List<Employee> employees = session.getCurrentSession().createQuery( criteria ).getResultList();
			String lastusername=employees.get(employees.size()-1).getUsername();
			return lastusername;
		}
		catch(Exception ex)
		{
			return null;
		}
	}

	
	
}
