package com.misyn.smartintranet.daoImp;

import java.util.List;


import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.misyn.smartintranet.dao.ProjectDAO;
import com.misyn.smartintranet.entity.Project;

@Repository
public class ProjectDAOimp implements ProjectDAO {
	
	@Autowired
	SessionFactory session;

	@Override
	public List<Project> getAllProjects() {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Project> criteria = builder.createQuery( Project.class );
			Root<Project> root = criteria.from( Project.class );
			criteria.select( root );
			List<Project> projects = session.getCurrentSession().createQuery( criteria ).getResultList();
			return projects;
		}
		catch(Exception ex)
		{
			return null;
		}
	}

	@Override
	public Project getProject(String id) {
		try{
			Project project=session.getCurrentSession().get(Project.class,id);
			return project;
		}
		catch(Exception ex){
			return null;
		}
	}

	@Override
	public boolean updateProject(Project project) {
		try{
			session.getCurrentSession().update(project);
			return true;
		}
		catch(Exception ex){
			return false;
		}
	}

	@Override
	public boolean saveProject(Project project) {
		try{
			session.getCurrentSession().persist(project);
			return true;
		}
		catch(Exception ex){
			return false;
		}
	}

	@Override
	public boolean deleteProject(String id) {
		try{
			Project project=session.getCurrentSession().get(Project.class,id);
			session.getCurrentSession().delete(project);
			return true;
		}
		catch(Exception ex){
			return false;
		}
	}

	@Override
	public List<Project> getAllProjectsByUsername(String username) {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Project> criteria = builder.createQuery( Project.class );
			Root<Project> root = criteria.from( Project.class );
			criteria.select( root );
			criteria.where( builder.equal( root.get("client").get("username"), username ) );
			List<Project> projects = session.getCurrentSession().createQuery( criteria ).getResultList();
			return projects;
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			return null;
		}
	}
	
	@Override
	public List<Tuple> getAllProjectsIDandNameByUsername(String username) {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteria = builder.createQuery( Tuple.class );
			Root<Project> root = criteria.from( Project.class );
			criteria.multiselect( root.get("projectID"),root.get("projectName"));
			criteria.where( builder.equal( root.get("client").get("username"), username ) );
			List<Tuple> projects = session.getCurrentSession().createQuery( criteria ).getResultList();
			return projects;
		}
		catch(Exception ex)
		{
			return null;
		}
	}

	@Override
	public List<Tuple> getAllProjectsDeatils(String clinetusername) {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteria = builder.createQuery( Tuple.class );
			Root<Project> root = criteria.from( Project.class );
			criteria.multiselect( root.get("projectID"),root.get("projectName"),root.get("client").get("username"),root.get("client").get("companyName"));
			criteria.where( builder.like( root.get("client").get("username"), clinetusername ) );
			criteria.orderBy(builder.desc(root.get("projectID")));
			List<Tuple> projects = session.getCurrentSession().createQuery( criteria ).getResultList();
			return projects;
		}
		catch(Exception ex)
		{
			return null;
		}

	}

	@Override
	public String getlastprojectID() {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Project> criteria = builder.createQuery( Project.class );
			Root<Project> root = criteria.from( Project.class );
			criteria.select( root );
			criteria.orderBy(builder.asc(root.get("projectID")));
			List<Project> Projects = session.getCurrentSession().createQuery( criteria ).getResultList();
			String lastprojectID=Projects.get(Projects.size()-1).getProjectID();
			return lastprojectID;
		}
		catch(Exception ex)
		{
			return null;
		}
	}

	

}
