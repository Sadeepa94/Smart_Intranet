package com.misyn.smartintranet.serviceImp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.misyn.smartintranet.dao.QuestionDAO;
import com.misyn.smartintranet.dao.ReplyDAO;
import com.misyn.smartintranet.entity.Question;
import com.misyn.smartintranet.service.QuestionService;

@Service
@Transactional
public class QuestionServiceimp implements QuestionService {
	
	@Autowired
	QuestionDAO qDAO;
	
	@Autowired
	ReplyDAO rDAO;

	@Override
	public Question getQuestion(long id) {
		return qDAO.getQuestion(id);
	}

	@Override
	public boolean updateQuestion(Question question) {
		return qDAO.updateQuestion(question);
	}

	@Override
	public boolean saveQuestion(Question question) {
		 question.setDate(new Date());
		 question.setStatus("new");
		 
		
		return qDAO.saveQuestion(question);
	}

	@Override
	public boolean deleteQuestion(long id) {
		return qDAO.deleteQuestion(id);
	}

	@Override
	public List<Tuple> getAllQuestionDetaisByUser(String username) {
		return qDAO.getAllQuestionDetaisByUser(username);
	}

	@Override
	public List<Tuple> getAllQuestionDetails() {
		return qDAO.getAllQuestionDetails();
	}
	@Override
	public List<Object> getAllQuestionandRepliesByquestionID(long id,String roleName) {
		List<Object> question=new ArrayList<Object>();
		List<Object> fullr=new ArrayList<Object>();
		
		
		
		Tuple questiondetail=qDAO.getAllQuestionDetailByID(id);
		System.out.println(questiondetail.get(0));
		List<Tuple> questionAttchement=qDAO.getAllAttachmentByQuestion(id);
		
		List<Tuple> replies;
		if(roleName.equals("client"))
			 replies=rDAO.getAllvisibleRepliesByQuestionID(id);
		else
			replies=rDAO.getAllRepliesByQuestionID(id);
		
		
		for(Tuple rep:replies)
		{
			List<Object> reply=new ArrayList<Object>();
			long replyid=(long) rep.get(0);
			List<Tuple> replyAttchement=rDAO.getAllAttachmentByReply(replyid);
			reply.add(rep);
			reply.add(replyAttchement);
			fullr.add(reply);
		}
		
		
			
		question.add(questiondetail);
		question.add(questionAttchement);
		question.add(fullr);
		
		return question;
	}

	@Override
	public Question getAllQuestionDetailByQID(long QuestionID) {
		return qDAO.getAllQuestionDetailByQID(QuestionID);
	}

	@Override
	public int getNoOFquestionsbyuserIDandstatus(String status, long id) {
		// TODO Auto-generated method stub
		return qDAO.getNoOFquestionsbyuserIDandstatus(status, id);
	}

	@Override
	public int getNoOFquestionsstatus(String status) {
		// TODO Auto-generated method stub
		return qDAO.getNoOFquestionsstatus(status);
	}

	@Override
	public String getClientemailByQID(long id) {
		// TODO Auto-generated method stub
		return qDAO.getClientemailByQID(id);
	}

	@Override
	public int getNoOFquestionsbyclientIDandstatus(String status, long id) {
		// TODO Auto-generated method stub
		return qDAO.getNoOFquestionsbyclientIDandstatus(status, id);
	}

	@Override
	public List<Tuple> getAllQuestionDetailsBycategory(String username,String category) {
		// TODO Auto-generated method stub
		return qDAO.getAllQuestionDetailsBycategory(username,category);
	}

	@Override
	public List<Tuple> getAllQuestionDetailsByStatus(String username,String status) {
		return qDAO.getAllQuestionDetailsByStatus(username,status);
	}

	@Override
	public List<Tuple> getAllQuestionDetailsByTS(String username) {
		return qDAO.getAllQuestionDetailsByTS(username);
	}

	@Override
	public List<Tuple> getCountByStatus() {
		return qDAO.getCountByStatus();
	}

	@Override
	public List<Tuple> getCountByMonthWise() {
		return qDAO.getCountByMonthWise();
	}

	@Override
	public Double getTSRating(int id) {
		return qDAO.getTSRating(id);
	}

	@Override
	public List<Tuple> getCountByStatusAndperiod(Date from, Date to) {
		return qDAO.getCountByStatusAndperiod(from, to);
	}

	@Override
	public List<Tuple> getCountByCategories() {
		return qDAO.getCountByCategories();
	}

	@Override
	public List<Tuple> getCountByCategoriesAndperiod(Date from, Date to) {
		return qDAO.getCountByCategoriesAndperiod(from, to);
	}

	
}
