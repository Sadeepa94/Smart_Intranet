package com.misyn.smartintranet.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.misyn.smartintranet.entity.Role;
import com.misyn.smartintranet.service.RoleService;
import com.misyn.smartintranet.util.JsonResponse;


@Controller
public class RoleController {
	
	@Autowired
	RoleService roleservice;
	
	
	
	

	
	
	@RequestMapping(value="/createrole", method=RequestMethod.GET)
	public @ResponseBody JsonResponse createRole(@ModelAttribute("role") Role role){
		
		JsonResponse jr=new JsonResponse();
		boolean result =false;
		Role r=roleservice.getRoleByname(role.getRoleName());
		if(r==null)
		{
			result=roleservice.saveRole(role);
			if (result){
				jr.setSuccess(result);
				jr.setStatus("Successfully created role");
			}
			
			else
			{
				jr.setSuccess(result);
				jr.setStatus("Some error occured");
			}
		}
		else
		{
			role.setRoleID(r.getRoleID());
			result=roleservice.updateRole(role);
			jr.setSuccess(result);
			jr.setStatus("This role is already exist and successfully updated");
		}
		
		return jr;
	}
	
	
	@RequestMapping(value="/loadRoles", method=RequestMethod.GET)
	public @ResponseBody List<Role> createRole(){
		

		List<Role> roles=roleservice.getAllRoles();
	
		return roles;
	}
	
	@RequestMapping(value="/getRole/{id}", method=RequestMethod.GET)
	public @ResponseBody Role createRole(@PathVariable("id") int id){
		

		Role role=roleservice.getRole(id);
	
		return role;
	}


}
