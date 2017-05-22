package com.misyn.smartintranet.daoImp;



import java.util.List;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.misyn.smartintranet.dao.UserDAO;
import com.misyn.smartintranet.entity.User;



@Repository
@Transactional
public class UserDAOimp implements UserDAO {
	
	@Autowired
	SessionFactory session;
	


	
	
	@Override
	public User getUser(int id) {
		
		try{
			User user=session.getCurrentSession().get(User.class,id);
			return user;
		}
		catch(Exception ex){
			return null;
		}
		
	}
	
	@Override
	public User getUserByUsername(String userName) {
		try{
		
			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery<User> criteria = builder.createQuery( User.class );
			Root<User> root = criteria.from( User.class );
			criteria.select( root );
			criteria.where( builder.equal( root.get("username"), userName ) );

			User user = session.getCurrentSession().createQuery( criteria ).getSingleResult();
			
			return user;
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			return null;
		}
	}


	@Override
	public boolean saveUser(User user) {
		// TODO Auto-generated method stub
		
		try{
			session.getCurrentSession().save(user);
			return true;
		}
		catch(Exception ex){
		return false;
		}
	}
	
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		
		try{
			session.getCurrentSession().update(user);
			return true;
		}
		catch(Exception ex){
		return false;
		}
	}

	@Override
	public boolean deleteUser(int id) {
		// TODO Auto-generated method stub
		try{
			User user=session.getCurrentSession().get(User.class,id);
			session.getCurrentSession().delete(user);
			return true;
		}
		catch(org.hibernate.exception.ConstraintViolationException ex){
			return false;
		}
		
	}
	
	public String getPassWordByUsername(String userName) {
		try{
		
			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery<String> criteria = builder.createQuery( String.class );
			Root<User> root = criteria.from( User.class );
			
			criteria.multiselect(root.get("password"));
			criteria.where( builder.equal( root.get("username"), userName ) );
			
			String pw = session.getCurrentSession().createQuery( criteria ).getSingleResult();
			return pw;
		}
		catch(Exception ex)
		{
			return null;
		}
		
		}

	@Override
	public Integer getIDByUsername(String username) {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery<Integer> criteria = builder.createQuery( Integer.class );
			Root<User> root = criteria.from( User.class );
			
			criteria.multiselect(root.get("id"));
			criteria.where( builder.equal( root.get("username"), username ) );
			
			Integer id = session.getCurrentSession().createQuery( criteria ).getSingleResult();
			return id;
		}
		catch(Exception ex)
		{
			return null;
		}
	}

	@Override
	public List<String> getAllusernamesByroleName(String rolename ) {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery<String> criteria = builder.createQuery( String.class );
			Root<User> root = criteria.from( User.class );
			
			criteria.multiselect(root.get("username"));
			criteria.where( builder.equal( root.get("role").get("roleName"), rolename ) );
			
			List<String> usernames = session.getCurrentSession().createQuery( criteria ).getResultList();
			return usernames;
		}
		catch(Exception ex)
		{
			return null;
		}
		
		
	}

	
	

}
