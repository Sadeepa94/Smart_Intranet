package com.misyn.smartintranet.daoImp;


import java.util.List;


import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.misyn.smartintranet.dao.RoleDAO;
import com.misyn.smartintranet.entity.Role;


@Repository
public class RoleDAOimp implements RoleDAO {
	@Autowired
	SessionFactory session;
	
	@Override
	public List<Role> getAllRoles() {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Role> criteria = builder.createQuery( Role.class );
			Root<Role> root = criteria.from( Role.class );
			criteria.select( root );
			List<Role> roles = session.getCurrentSession().createQuery( criteria ).getResultList();
			return roles;
		}
		catch(Exception ex)
		{
			
			return null;
		}
	}

	@Override
	public Role getRole(int id) {
		try{
			Role role=session.getCurrentSession().get(Role.class,id);
			return role;
		}
		catch(Exception ex){
			return null;
		}
	}

	@Override
	public boolean updateRole(Role role) {
		try{
			session.getCurrentSession().update(role);
			return true;
		}
		catch(Exception ex){
			return false;
		}
	}

	@Override
	public boolean saveRole(Role role) {
		
		try{
			
			session.getCurrentSession().persist(role);
			return true;
		}
		catch(Exception ex){
			return false;
		}
	}

	@Override
	public boolean deleteRole(int id) {
		try{
			Role role=session.getCurrentSession().get(Role.class,id);
			session.getCurrentSession().delete(role);
			return true;
		}
		catch(Exception ex){
			return false;
		}
	}
	
	@Override
	public List<Tuple> getAllRolesNameId() {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteria = builder.createTupleQuery();
			Root<Role> root = criteria.from( Role.class );
			criteria.multiselect(root.get("roleID"),root.get("roleName")); 
			List<Tuple> roles = session.getCurrentSession().createQuery( criteria ).getResultList();

			
			return roles;
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			return null;
		}
		
	}
	
	@Override
	public Role getRoleByname(String roleName) {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Role> criteria = builder.createQuery(Role.class);
			Root<Role> root = criteria.from( Role.class );
			criteria.where( builder.equal( root.get("roleName"), roleName ) );
			Role role = session.getCurrentSession().createQuery( criteria ).getSingleResult();
			return role;
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			return null;
		}
		
	}

}
