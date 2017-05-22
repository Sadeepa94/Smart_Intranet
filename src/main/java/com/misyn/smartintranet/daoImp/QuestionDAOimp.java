package com.misyn.smartintranet.daoImp;

import java.util.Date;
import java.util.List;


import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.misyn.smartintranet.dao.QuestionDAO;
import com.misyn.smartintranet.entity.Question;





@Repository
public class QuestionDAOimp implements QuestionDAO {
	
	@Autowired
	SessionFactory session;

	@Override
	public Question getQuestion(long id) {
		try{
			Question question=session.getCurrentSession().get(Question.class,id);
			return question;
		}
		catch(Exception ex){
			return null;
		}
	}

	@Override
	public boolean updateQuestion(Question question) {
		try{
			session.getCurrentSession().update(question);
			return true;
		}
		catch(Exception ex){
			return false;
		}
	}

	@Override
	public boolean saveQuestion(Question question) {
		try{
			session.getCurrentSession().persist(question);
			return true;
		}
		catch(Exception ex){
			return false;
		}
	}

	@Override
	public boolean deleteQuestion(long id) {
		try{
			Question question=session.getCurrentSession().get(Question.class,id);
			session.getCurrentSession().delete(question);
			return true;
		}
		catch(Exception ex){
			return false;
		}
	}

	@Override
	public List<Tuple> getAllQuestionDetaisByUser(String username) {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class);
			Root<Question> root = criteria.from( Question.class );
			Join<?, ?> tc=root.join("technicalSuporter", JoinType.LEFT);
			Join<?, ?> client=root.join("client", JoinType.LEFT);
			criteria.multiselect( root.get("topic"), root.get("status"),client.get("username"),tc.get("username"),root.get("date"),root.get("rating"),root.get("questionDescription"),root.get("questionID"),root.get("category") );
			criteria.where( builder.equal(client.get("username"), username ) );
			criteria.orderBy(builder.desc(root.get("date")));
			List<Tuple> result = session.getCurrentSession().createQuery( criteria ).getResultList();
			
			return result;
			
		}
		catch(Exception ex)
		{
			return null;
		}
	}

	@Override
	public List<Tuple> getAllQuestionDetails() {
			
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class);
			Root<Question> root = criteria.from( Question.class );
			Join<?, ?> tc=root.join("technicalSuporter", JoinType.LEFT);
			Join<?, ?> client=root.join("client", JoinType.LEFT);
			criteria.multiselect( root.get("topic"), root.get("status"),client.get("username"),tc.get("username"),root.get("date"),root.get("rating"),root.get("questionDescription"),root.get("questionID"),root.get("category") );
			criteria.orderBy(builder.desc(root.get("date")));
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
	public Tuple getAllQuestionDetailByID(long QuestionID) {
		
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class);
			Root<Question> root = criteria.from( Question.class );
			Join<?, ?> tc=root.join("technicalSuporter", JoinType.LEFT);
			Join<?, ?> client=root.join("client", JoinType.LEFT);
			Join<?, ?> project=root.join("project", JoinType.LEFT);
			Join<?, ?> module=root.join("module", JoinType.LEFT);
			criteria.multiselect( root.get("topic"), root.get("status"),client.get("username"),tc.get("username"),root.get("date"),root.get("rating"),root.get("questionDescription"),root.get("questionID"),project.get("projectID"),module.get("moduleID"),root.get("category") );
			criteria.where( builder.equal( root.get("questionID"), QuestionID ) );
			Tuple result = session.getCurrentSession().createQuery( criteria ).getSingleResult();
			System.out.println(result.get(5));
			return result;
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			return null;
		}
	}
	
	public List<Tuple> getAllAttachmentByQuestion(long id) {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class);
			Root<Question> root = criteria.from( Question.class );
			Join<?, ?> attachment=root.join("attachment", JoinType.LEFT);
			criteria.multiselect( attachment.get("attachmentID") ,attachment.get("showingName"));
			criteria.where( builder.equal( root.get("questionID"), id ) );
			List<Tuple> result = session.getCurrentSession().createQuery( criteria ).getResultList();
			
			return result;
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			return null;
		}
		
	}
	
		public Question getAllQuestionDetailByQID(long QuestionID) {
		
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Question> criteria = builder.createQuery(Question.class);
			Root<Question> root = criteria.from( Question.class );
			Join<?, ?> tc=root.join("technicalSuporter", JoinType.LEFT);
			Join<?, ?> client=root.join("client", JoinType.LEFT);
			criteria.multiselect( root.get("topic"), root.get("status"),client.get("username"),tc.get("username"),root.get("date"),root.get("rating"),root.get("questionDescription"),root.get("questionID") );
			criteria.where( builder.equal( root.get("questionID"), QuestionID ) );
			Question result = session.getCurrentSession().createQuery( criteria ).getSingleResult();
			
			return result;
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			return null;
		}
	}

		@Override
		public int getNoOFquestionsbyuserIDandstatus(String status, long id) {
			try{
				
				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class);
				Root<Question> root = criteria.from( Question.class );
				
				criteria.multiselect(root.get("questionID"));
				criteria.where( builder.equal( root.get("technicalSuporter").get("id"), id ),builder.like( root.get("status"), status ) );
				int result = session.getCurrentSession().createQuery( criteria ).getResultList().size();
				
				return result;
			}
			catch(Exception ex)
			{
				System.out.println(ex);
				return -1;
			}
		}
		@Override
		public int getNoOFquestionsbyclientIDandstatus(String status, long id) {
			try{
				
				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class);
				Root<Question> root = criteria.from( Question.class );
				
				criteria.multiselect(root.get("questionID"));
				criteria.where( builder.equal( root.get("client").get("id"), id ),builder.equal( root.get("status"), status ) );
				int result = session.getCurrentSession().createQuery( criteria ).getResultList().size();
				
				return result;
			}
			catch(Exception ex)
			{
				System.out.println(ex);
				return -1;
			}
		}

		@Override
		public int getNoOFquestionsstatus(String status) {
try{
				
				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class);
				Root<Question> root = criteria.from( Question.class );
				
				criteria.multiselect(root.get("questionID"));
				criteria.where(builder.equal( root.get("status"), status ) );
				int result = session.getCurrentSession().createQuery( criteria ).getResultList().size();
				
				return result;
			}
			catch(Exception ex)
			{
				System.out.println(ex);
				return -1;
			}
		}
		
		

		@Override
		public String getClientemailByQID(long id) {
try{
				
				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<String> criteria = builder.createQuery(String.class);
				Root<Question> root = criteria.from( Question.class );
				
				criteria.multiselect(root.get("client").get("email"));
				criteria.where(builder.equal( root.get("questionID"), id ) );
				String result = session.getCurrentSession().createQuery( criteria ).getSingleResult();
				
				return result;
			}
			catch(Exception ex)
			{
				System.out.println(ex);
				return null;
			}
		}
		
		public List<Tuple> getAllQuestionDetailsBycategory(String username,String ctaegory)
		{
			try{

				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class);
				Root<Question> root = criteria.from( Question.class );
				Join<?, ?> tc=root.join("technicalSuporter", JoinType.LEFT);
				Join<?, ?> client=root.join("client", JoinType.LEFT);
				criteria.multiselect( root.get("topic"), root.get("status"),client.get("username"),tc.get("username"),root.get("date"),root.get("rating"),root.get("questionDescription"),root.get("questionID"),root.get("category") );
				criteria.orderBy(builder.desc(root.get("date")));
				criteria.where(builder.equal( root.get("category"), ctaegory ), builder.like( client.get("username"), username ));
				List<Tuple> result = session.getCurrentSession().createQuery( criteria ).getResultList();

				return result;
			}
			catch(Exception ex)
			{
				System.out.println(ex);
				return null;
			}
		}

		public List<Tuple> getAllQuestionDetailsByStatus(String username,String status)
		{
			try{

				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class);
				Root<Question> root = criteria.from( Question.class );
				Join<?, ?> tc=root.join("technicalSuporter", JoinType.LEFT);
				Join<?, ?> client=root.join("client", JoinType.LEFT);
				criteria.multiselect( root.get("topic"), root.get("status"),client.get("username"),tc.get("username"),root.get("date"),root.get("rating"),root.get("questionDescription"),root.get("questionID"),root.get("category") );
				criteria.orderBy(builder.desc(root.get("date")));
				criteria.where(builder.equal( root.get("status"), status ),builder.like( client.get("username"), username ) );
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
		public List<Tuple> getAllQuestionDetailsByTS(String username) {
			try{

				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class);
				Root<Question> root = criteria.from( Question.class );
				Join<?, ?> tc=root.join("technicalSuporter", JoinType.LEFT);
				Join<?, ?> client=root.join("client", JoinType.LEFT);
				criteria.multiselect( root.get("topic"), root.get("status"),client.get("username"),tc.get("username"),root.get("date"),root.get("rating"),root.get("questionDescription"),root.get("questionID"),root.get("category") );
				criteria.orderBy(builder.desc(root.get("date")));
				criteria.where(builder.equal( tc.get("username"), username ) );
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
		public List<Tuple> getCountByStatus() {
			try{
				
				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class);
				Root<Question> root = criteria.from( Question.class );
				
				criteria.multiselect( root.get("status"), builder.count(root.get("questionID")));
				
				criteria.groupBy(root.get("status"));
				
				List<Tuple> result = session.getCurrentSession().createQuery( criteria ).getResultList();
				
				return result;
				
			}
			catch(Exception ex)
			{
				return null;
			}

		}
		@Override
		public List<Tuple> getCountByMonthWise() {
			try{
				
				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class);
				Root<Question> root = criteria.from( Question.class );
				Expression<Date> date = root.get("date");
				Expression<Integer> year = builder.function("year", Integer.class, date);
				Expression<Integer> month = builder.function("month", Integer.class, date);
				
				criteria.multiselect( year,month, builder.count(root.get("questionID")));
				
				criteria.groupBy(year,month);
				criteria.orderBy(builder.asc(year),builder.asc(month));
				
				List<Tuple> result = session.getCurrentSession().createQuery( criteria ).getResultList();
				
				return result;
				
			}
			catch(Exception ex)
			{
				return null;
			}

		}

		@Override
		public Double getTSRating(int id) {
			try{

				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<Double> criteria = builder.createQuery(Double.class);
				Root<Question> root = criteria.from( Question.class );
				Join<?, ?> tc=root.join("technicalSuporter", JoinType.LEFT);
				
				criteria.multiselect(builder.avg( root.get("rating")));
				
				criteria.where(builder.equal( tc.get("id"), id ),builder.equal( root.get("status"), "close" ) );
				Double result = session.getCurrentSession().createQuery( criteria ).getSingleResult();

				return result;
			}
			catch(Exception ex)
			{
				System.out.println(ex);
				return -1.0;
			}
		}

		@Override
		public List<Tuple> getCountByStatusAndperiod(Date from, Date to) {
			try{
				
				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class);
				Root<Question> root = criteria.from( Question.class );
				
				criteria.multiselect( root.get("status"), builder.count(root.get("questionID")));
				
				criteria.groupBy(root.get("status"));
				Predicate p = builder.between(root.get("date").as(java.sql.Date.class),from,to);
				criteria.where(p);
				
				List<Tuple> result = session.getCurrentSession().createQuery( criteria ).getResultList();
				
				return result;
				
			}
			catch(Exception ex)
			{
				return null;
			}
		}

		@Override
		public List<Tuple> getCountByCategories() {
			try{
				
				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class);
				Root<Question> root = criteria.from( Question.class );
				
				criteria.multiselect( root.get("category"), builder.count(root.get("questionID")));
				
				criteria.groupBy(root.get("category"));
				
				List<Tuple> result = session.getCurrentSession().createQuery( criteria ).getResultList();
				
				return result;
				
			}
			catch(Exception ex)
			{
				return null;
			}
		}

		@Override
		public List<Tuple> getCountByCategoriesAndperiod(Date from, Date to) {
			try{
				
				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class);
				Root<Question> root = criteria.from( Question.class );
				
				criteria.multiselect( root.get("category"), builder.count(root.get("questionID")));
				
				criteria.groupBy(root.get("category"));
				Predicate p = builder.between(root.get("date").as(java.sql.Date.class),from,to);
				criteria.where(p);
				
				List<Tuple> result = session.getCurrentSession().createQuery( criteria ).getResultList();
				
				return result;
				
			}
			catch(Exception ex)
			{
				return null;
			}
		}
		
		
		
		

		
		
		


}
