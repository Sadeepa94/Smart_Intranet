package com.misyn.smartintranet.daoImp;

import java.util.List;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.misyn.smartintranet.dao.NotificationDAO;
import com.misyn.smartintranet.entity.Notification;

@Repository
public class NotificationDAOimp implements NotificationDAO  {
	
	@Autowired
	SessionFactory session;

	@Override
	public boolean saveNotification(Notification notification) {
		try{
			session.getCurrentSession().save(notification);
			return true;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateNotification(Notification notification) {
		try{
			session.getCurrentSession().update(notification);
			return true;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Tuple> getAllNotificationByUsername(String username) {
		try{

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class);
			Root<Notification> root = criteria.from( Notification.class );
			Join<?, ?> question=root.join("question", JoinType.LEFT);
			criteria.multiselect( root.get("message"),root.get("notificationId"),question.get("questionID") );
			criteria.orderBy(builder.desc(root.get("date")));
			criteria.where(builder.equal( root.get("to").get("username"), username ));
			List<Tuple> result = session.getCurrentSession().createQuery( criteria ).getResultList();

			return result;
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			return null;
		}
	}

	@Override
	public List<Tuple> getAllNotificationByStatusAndUsername(String status,String username) {
		try{

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class);
			Root<Notification> root = criteria.from( Notification.class );
			Join<?, ?> question=root.join("question", JoinType.LEFT);
			criteria.multiselect( root.get("message"),root.get("notificationId"),question.get("questionID") );
			criteria.orderBy(builder.desc(root.get("date")));
			criteria.where(builder.equal( root.get("to").get("username"), username ),builder.equal( root.get("status"), status )  );
			List<Tuple> result = session.getCurrentSession().createQuery( criteria ).getResultList();

			return result;
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			return null;
		}

	}

	@Override
	public Notification getNotification(long id) {
		try{
			Notification notification=session.getCurrentSession().get(Notification.class,id);
			return notification;
		}
		catch(Exception ex){
			return null;
		}
	}

}
