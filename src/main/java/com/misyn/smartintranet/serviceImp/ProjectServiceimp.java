package com.misyn.smartintranet.serviceImp;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.misyn.smartintranet.dao.ProjectDAO;
import com.misyn.smartintranet.entity.Project;
import com.misyn.smartintranet.service.ProjectService;
import com.misyn.smartintranet.util.NextValueGen;

@Service
@Transactional
public class ProjectServiceimp implements ProjectService {
	@Autowired
	ProjectDAO projectDAO;
	

	@Override
	public List<Project> getAllProjects() {
		return projectDAO.getAllProjects();
	}

	@Override
	public Project getProject(String id) {
		return projectDAO.getProject(id);
	}

	@Override
	public boolean updateProject(Project project) {
		return projectDAO.updateProject(project);
	}

	@Override
	public boolean saveProject(Project project) {
		return projectDAO.saveProject(project);
	}

	@Override
	public boolean deleteProject(String id) {
		return projectDAO.deleteProject(id);
	}

	@Override
	public List<Project> getAllProjectsByUsername(String username) {
		return projectDAO.getAllProjectsByUsername(username);
	}

	@Override
	public List<Tuple> getAllProjectsIDandNameByUsername(String username) {
		return projectDAO.getAllProjectsIDandNameByUsername(username);
	}

	@Override
	public List<Tuple> getAllProjectsDeatils(String clientusername) {
		return projectDAO.getAllProjectsDeatils(clientusername);
				
	}

	@Override
	public String getNextProjectID() {
		String projectID=projectDAO.getlastprojectID();
		System.out.println(projectID);
		if(projectID==null)
			return "prj0001";
		else
			return NextValueGen.getNextValue(projectID);

	}

	

}
