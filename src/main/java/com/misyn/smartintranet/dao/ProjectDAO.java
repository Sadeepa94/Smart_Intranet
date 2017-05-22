package com.misyn.smartintranet.dao;

import java.util.List;

import javax.persistence.Tuple;

import com.misyn.smartintranet.entity.Project;


public interface ProjectDAO {
	
	public List<Project>  getAllProjects();
	public Project getProject(String id);
	public boolean updateProject(Project project);
	public boolean saveProject(Project project);
	public boolean deleteProject(String id);
	public List<Project>  getAllProjectsByUsername(String username);
	public List<Tuple> getAllProjectsIDandNameByUsername(String username);
	public List<Tuple>  getAllProjectsDeatils(String clientusername);
	public String getlastprojectID();
	

}
