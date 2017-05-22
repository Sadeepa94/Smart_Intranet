package com.misyn.smartintranet.daoImp;

import java.util.List;


import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.misyn.smartintranet.dao.ClientDAO;
import com.misyn.smartintranet.entity.Client;




@Repository
public class ClientDAOimp implements ClientDAO {
	
	
	@Autowired
	SessionFactory session;
	
	
	@Override
	public Client getClient(String id) {
		try{
			Client client=session.getCurrentSession().get(Client.class,id);
			return client;
		}
		catch(Exception ex){
			return null;
		}
	}

	@Override
	public Client getClientByUsername(String userName) {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery<Client> criteria = builder.createQuery( Client.class );
			Root<Client> root = criteria.from( Client.class );
			criteria.select( root );
			criteria.where( builder.equal( root.get("username"), userName ) );

			Client client = session.getCurrentSession().createQuery( criteria ).getSingleResult();
		
			return client;
		}
		catch(Exception ex)
		{
			
			return null;
		}
	}

	@Override
	public boolean saveClient(Client client) {
		try{
			session.getCurrentSession().save(client);
			return true;
		}
		catch(Exception ex){
		return false;
		}
	}
	
	@Override
	public boolean updateClient(Client client) {
		try{
			session.getCurrentSession().update(client);
			return true;
		}
		catch(Exception ex){
		return false;
		}
	}

	@Override
	public boolean deleteClient(String id) {
		try{
			Client client=session.getCurrentSession().get(Client.class,id);
			session.getCurrentSession().delete(client);
			return true;
		}
		catch(Exception ex){
			return false;
		}
	}

	@Override
	public List<Tuple> getAllClient(String username) {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteria = builder.createTupleQuery();
			Root<Client> root = criteria.from( Client.class );
			criteria.multiselect(root.get("username"),root.get("email"),root.get("contact_no"),root.get("responsible_person"),root.get("companyName"));
			criteria.where( builder.like( root.get("username"), username ) );
			criteria.orderBy(builder.desc(root.get("username")));
			List<Tuple> clients = session.getCurrentSession().createQuery( criteria ).getResultList();

			
			return clients;
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			return null;
		}
	}

	@Override
	public String getLastClientUsername() {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Client> criteria = builder.createQuery( Client.class );
			Root<Client> root = criteria.from( Client.class );
			criteria.select( root );
			criteria.orderBy(builder.asc(root.get("id")));
			List<Client> clients = session.getCurrentSession().createQuery( criteria ).getResultList();
			String lastusername=clients.get(clients.size()-1).getUsername();
			return lastusername;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	

	
	
	
}
