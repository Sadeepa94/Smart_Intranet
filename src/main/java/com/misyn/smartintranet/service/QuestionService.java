package com.misyn.smartintranet.service;

import java.util.Date;
import java.util.List;

import javax.persistence.Tuple;

import com.misyn.smartintranet.entity.Question;

public interface QuestionService {
	
	public Question getQuestion(long id);
	public boolean updateQuestion(Question question);
	public boolean saveQuestion(Question question);
	public boolean deleteQuestion(long id);
	public List<Tuple>  getAllQuestionDetaisByUser(String username);
	public List<Tuple> getAllQuestionDetails();
	public List<Object> getAllQuestionandRepliesByquestionID(long id,String roleName);
	public Question getAllQuestionDetailByQID(long QuestionID);
	public int getNoOFquestionsbyuserIDandstatus(String status,long id);
	public int getNoOFquestionsstatus(String status);
	public String getClientemailByQID(long id);
	public int getNoOFquestionsbyclientIDandstatus(String status, long id);
	public List<Tuple> getAllQuestionDetailsBycategory(String username,String category);
	public List<Tuple> getAllQuestionDetailsByStatus(String username,String status);
	public List<Tuple> getAllQuestionDetailsByTS(String username);
	
	public List<Tuple> getCountByStatus();
	public List<Tuple> getCountByStatusAndperiod(Date from,Date to);
	public List<Tuple> getCountByCategories();
	public List<Tuple> getCountByCategoriesAndperiod(Date from,Date to);
	public List<Tuple> getCountByMonthWise();
	public Double getTSRating(int id);


}
