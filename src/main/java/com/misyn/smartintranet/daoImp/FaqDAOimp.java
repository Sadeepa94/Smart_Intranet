package com.misyn.smartintranet.daoImp;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.misyn.smartintranet.dao.FaqDAO;
import com.misyn.smartintranet.entity.FAQ;

@Repository
public class FaqDAOimp implements FaqDAO {
	@Autowired
	SessionFactory session;

	@Override
	public FAQ getFAQ(int id) {
		try{
			FAQ faq=session.getCurrentSession().get(FAQ.class,id);
			return faq;
		}
		catch(Exception ex){
			return null;
		}
	}

	@Override
	public boolean saveFAQ(FAQ faq) {
		try{
			session.getCurrentSession().saveOrUpdate(faq);
			return true;
		}
		catch(Exception ex){
			return false;
		}
	}

	

	@Override
	public boolean deleteFAQ(int id) {
		try{
			FAQ faq=session.getCurrentSession().get(FAQ.class,id);
			session.getCurrentSession().delete(faq);
			return true;
		}
		catch(Exception ex){
			return false;
		}
	}

	@Override
	public List<FAQ> getAllFAQ() {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<FAQ> criteria = builder.createQuery( FAQ.class );
			Root<FAQ> root = criteria.from( FAQ.class );
			criteria.select( root );
			List<FAQ> projects = session.getCurrentSession().createQuery( criteria ).getResultList();
			return projects;
		}
		catch(Exception ex)
		{
			return null;
		}
	}

}
