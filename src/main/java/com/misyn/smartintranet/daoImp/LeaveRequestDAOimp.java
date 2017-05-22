package com.misyn.smartintranet.daoImp;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.misyn.smartintranet.dao.LeaveRequestDAO;
import com.misyn.smartintranet.entity.LeaveRequest;
import com.misyn.smartintranet.entity.LeaveStatus;

@Repository
public class LeaveRequestDAOimp implements LeaveRequestDAO {
	
	@Autowired
	SessionFactory session;

	@Override
	public LeaveRequest getLeaveRequest(int id) {
		try{
			LeaveRequest leaveRequest=session.getCurrentSession().get(LeaveRequest.class,id);
			return leaveRequest;
		}
		catch(Exception ex){
			return null;
		}
	}

	@Override
	public boolean saveLeaveRequest(LeaveRequest leaveRequest) {
		try{
			session.getCurrentSession().save(leaveRequest);
			return true;
		}
		catch(Exception ex){
		return false;
		}
	}

	@Override
	public boolean deleteLeaveRequest(int id) {
		try{
			LeaveRequest leaveRequest=session.getCurrentSession().get(LeaveRequest.class,id);
			session.getCurrentSession().delete(leaveRequest);
			return true;
		}
		catch(Exception ex){
			return false;
		}
	}

	@Override
	public boolean updateLeaveRequest(LeaveRequest leaveRequest) {
		try{
			session.getCurrentSession().update(leaveRequest);
			return true;
		}
		catch(Exception ex){
		return false;
		}
	}

	@Override
	public List<LeaveRequest> getAllLeaveRequest() {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<LeaveRequest> criteria = builder.createQuery( LeaveRequest.class );
			Root<LeaveRequest> root = criteria.from( LeaveRequest.class );
			criteria.select( root );
			List<LeaveRequest> LeaveRequests = session.getCurrentSession().createQuery( criteria ).getResultList();
			return LeaveRequests;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	

	@Override
	public List<LeaveRequest> findByEmployeeId(int employeeId) {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<LeaveRequest> criteria = builder.createQuery( LeaveRequest.class );
			Root<LeaveRequest> root = criteria.from( LeaveRequest.class );
			criteria.select( root );
			criteria.where( builder.equal( root.get("employee").get("id"), employeeId));
			List<LeaveRequest> LeaveRequests = session.getCurrentSession().createQuery( criteria ).getResultList();
			return LeaveRequests;
		}
		catch(Exception ex)
		{
			return null;
		}
	}

	@Override
	public List<LeaveRequest> findByLeaveStatus(LeaveStatus leaveStatus) {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<LeaveRequest> criteria = builder.createQuery( LeaveRequest.class );
			Root<LeaveRequest> root = criteria.from( LeaveRequest.class );
			criteria.select( root );
			criteria.where( builder.equal( root.get("leaveStatus"), leaveStatus));
			List<LeaveRequest> LeaveRequests = session.getCurrentSession().createQuery( criteria ).getResultList();
			return LeaveRequests;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	

}
