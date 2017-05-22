package com.misyn.smartintranet.daoImp;

import java.util.List;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.misyn.smartintranet.dao.TypeDAO;
import com.misyn.smartintranet.entity.Type;
import com.misyn.smartintranet.entity.Type.TypeName;


@Repository
public class TypeDAOimp implements TypeDAO {

	@Autowired
	SessionFactory session;
	
	
	@Override
	public List<Type> getAllTypes() {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Type> criteria = builder.createQuery( Type.class );
			Root<Type> root = criteria.from( Type.class );
			criteria.select( root );
			List<Type> types = session.getCurrentSession().createQuery( criteria ).getResultList();
			return types;
		}
		catch(Exception ex)
		{
			
			return null;
		}
	}

	@Override
	public Type getType(int id) {
		try{
			Type type=session.getCurrentSession().get(Type.class,id);
			return type;
		}
		catch(Exception ex){
			return null;
		}
	}

	@Override
	public boolean updateType(Type type) {
		try{
			session.getCurrentSession().update(type);
			return true;
		}
		catch(Exception ex){
			return false;
		}
	}

	@Override
	public boolean saveType(Type type) {
		try{
			session.getCurrentSession().persist(type);
			return true;
		}
		catch(Exception ex){
			return false;
		}
	}

	@Override
	public boolean deleteType(int id) {
		try{
			Type type=session.getCurrentSession().get(Type.class,id);
			session.getCurrentSession().delete(type);
			return true;
		}
		catch(Exception ex){
			return false;
		}
	}
	@Override
	public List<Tuple> getAllTypesNameId() {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteria = builder.createTupleQuery();
			Root<Type> root = criteria.from( Type.class );
			criteria.multiselect(root.get("typeID"),root.get("typeName")); 
			List<Tuple> Types = session.getCurrentSession().createQuery( criteria ).getResultList();

			
			return Types;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
		
	}

	@Override
	public Type getTypeByTypeName(TypeName typeName) {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Type> criteria = builder.createQuery(Type.class);
			Root<Type> root = criteria.from( Type.class );
			criteria.where( builder.equal( root.get("typeName"), typeName ) );
			Type type = session.getCurrentSession().createQuery( criteria ).getSingleResult();
			return type;
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			return null;
		}
	}
	
	

}
