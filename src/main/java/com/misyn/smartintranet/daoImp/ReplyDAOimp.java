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

import com.misyn.smartintranet.dao.ReplyDAO;
import com.misyn.smartintranet.entity.Reply;

@Repository
public class ReplyDAOimp implements ReplyDAO {
	@Autowired
	SessionFactory session;

	@Override
	public boolean updateReply(Reply reply) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveReply(Reply reply) {
		try{
			session.getCurrentSession().saveOrUpdate(reply);
			return true;
		}
		catch(Exception ex){
			return false;
		}
	}

	@Override
	public boolean deleteReply(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Tuple> getAllRepliesByQuestionID(long questionID) {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class );
			Root<Reply> root = criteria.from( Reply.class );
			Join<?, ?> user=root.join("user", JoinType.LEFT);
			Join<?, ?> role=user.join("role", JoinType.INNER);
			Join<?, ?> question=root.join("question", JoinType.LEFT);
			criteria.multiselect(root.get("replyID"), root.get("replyDescription"),root.get("date"),role.get("roleName"),user.get("username"));
			criteria.where( builder.equal( question.get("questionID"), questionID ) );
			criteria.orderBy(builder.asc(root.get("date")));
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
	public List<Tuple> getAllvisibleRepliesByQuestionID(long questionID) {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class );
			Root<Reply> root = criteria.from( Reply.class );
			Join<?, ?> user=root.join("user", JoinType.LEFT);
			Join<?, ?> role=user.join("role", JoinType.INNER);
			Join<?, ?> question=root.join("question", JoinType.LEFT);
			criteria.multiselect(root.get("replyID"), root.get("replyDescription"),root.get("date"),role.get("roleName"),user.get("username"));
			criteria.where( builder.equal( question.get("questionID"),questionID),builder.equal(root.get("hidden"), false));
			criteria.orderBy(builder.asc(root.get("date")));
			List<Tuple> result = session.getCurrentSession().createQuery( criteria ).getResultList();
			return result;
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			return null;
		}
	}
	
	public List<Tuple> getAllAttachmentByReply(long id) {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class);
			Root<Reply> root = criteria.from( Reply.class );
			Join<?, ?> attachment=root.join("attachments", JoinType.LEFT);
			criteria.multiselect( attachment.get("attachmentID") ,attachment.get("showingName"));
			criteria.where( builder.equal( root.get("replyID"), id ) );
			List<Tuple> result = session.getCurrentSession().createQuery( criteria ).getResultList();
			
			return result;
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			return null;
		}
		
	}

}
