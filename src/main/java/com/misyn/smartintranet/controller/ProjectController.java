package com.misyn.smartintranet.controller;

import java.util.List;

import javax.persistence.Tuple;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.misyn.smartintranet.entity.Client;
import com.misyn.smartintranet.entity.Project;
import com.misyn.smartintranet.service.ProjectService;
import com.misyn.smartintranet.service.UserService;
import com.misyn.smartintranet.util.JsonResponse;
import com.misyn.smartintranet.util.PageList;

@Controller
public class ProjectController {
	@Autowired
	ProjectService prjService;
	@Autowired
	UserService cService;
	@Autowired
	Gson gson;
	
	@RequestMapping(value="/createProject", method=RequestMethod.GET)
	public @ResponseBody JsonResponse createProject(@Valid @ModelAttribute("project") Project project,BindingResult result){
		JsonResponse js= new JsonResponse();
		
		
		Client client=(Client) cService.getUserByUsername(project.getClient().getUsername());
		if(result.hasErrors()||client==null)
		{	
			
			js.setErrors(result);
			js.setSuccess(false);
			js.setStatus("Plese check and resubmit");
		}
		else
		{
			Project existproject=prjService.getProject(project.getProjectID());
			if(existproject==null){
				
				project.setClient(client);
				prjService.saveProject(project);
				js.setStatus("Successfully added project "+project.getProjectID());
				js.setSuccess(true);
			}
				
			else
			{
				project.setClient(client);
				prjService.updateProject(project);
				js.setStatus("Successfully updated project "+project.getProjectID());
				js.setSuccess(true);
			}
			

			
		
		}
		return js;
	}
	
	@RequestMapping(value="/loadSystems/{username}", method=RequestMethod.GET)
	public @ResponseBody List<Project> getSystemByUsername(@PathVariable("username") String username){
		System.out.println(username+"----------------------------------------");
		List<Project> projects=prjService.getAllProjectsByUsername(username);
		System.out.println(projects.size());
		return projects;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/loadprojects/{page}", method=RequestMethod.GET)
	public @ResponseBody String getAllprojects(@PathVariable("page") String page, HttpServletRequest req){
		
		
		
		PageList pl=new PageList();
		
		PagedListHolder<Tuple> projectList = null;
		
		
		
		if("0".equals(page)){
			projectList = new PagedListHolder<Tuple>();
			List<Tuple> projects=prjService.getAllProjectsDeatils("%");
			projectList.setSource(projects);
			projectList.setPageSize(10);
			req.getSession().setAttribute("plist", projectList);
			pl.setSource(projectList.getPageList());
			pl.setPages(projectList.getPageCount());
			pl.setCurrent(projectList.getPage()+1);
			pl.setFirstpage(projectList.isFirstPage());
			pl.setLastpage(projectList.isLastPage());
			
        
		}
		
		else if("next".equals(page)){
			
			projectList=(PagedListHolder<Tuple>) req.getSession().getAttribute("plist");
			projectList.nextPage();
			pl.setSource(projectList.getPageList());
			pl.setPages(projectList.getPageCount());
			pl.setCurrent(projectList.getPage()+1);
			pl.setFirstpage(projectList.isFirstPage());
			pl.setLastpage(projectList.isLastPage());
			
		}
		
		else if("prev".equals(page)){
			
			projectList=(PagedListHolder<Tuple>) req.getSession().getAttribute("plist");
			projectList.previousPage();
			pl.setSource(projectList.getPageList());
			pl.setPages(projectList.getPageCount());
			pl.setCurrent(projectList.getPage()+1);
			pl.setFirstpage(projectList.isFirstPage());
			pl.setLastpage(projectList.isLastPage());
			
		}
		
		else{
				int i=Integer.parseInt(page);

				projectList=(PagedListHolder<Tuple>) req.getSession().getAttribute("plist");

				projectList.setPage(i-1);
				pl.setSource(projectList.getPageList());
				pl.setPages(projectList.getPageCount());
				pl.setCurrent(projectList.getPage()+1);
				pl.setFirstpage(projectList.isFirstPage());
				pl.setLastpage(projectList.isLastPage());
				
			}
		
		String result=gson.toJson(pl);
		return result;
	}
	
	
	@RequestMapping(value="/getproject/{projectID}", method=RequestMethod.POST)
	public @ResponseBody Project getProject(@PathVariable("projectID") String projectID,HttpServletRequest r){
		
		Project project=prjService.getProject(projectID);
		
		return project;
	}
	
	@RequestMapping(value="/getProjectForm", method=RequestMethod.GET)
	public @ResponseBody String getProjectForm(){
		
		String projectID=prjService.getNextProjectID();
		String response=gson.toJson(projectID);
		return response;
	}
	
	
	@RequestMapping(value="/searchProject/{username}", method=RequestMethod.POST)
	public @ResponseBody String searchProject(@PathVariable("username") String username){
		
		List<Tuple> result=prjService.getAllProjectsDeatils(username+"%");
		String response=gson.toJson(result);
		
		return response;
	}



}
