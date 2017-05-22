package com.misyn.smartintranet.controller;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.Tuple;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.google.gson.Gson;
import com.misyn.smartintranet.entity.Employee;
import com.misyn.smartintranet.service.EmployeeService;
import com.misyn.smartintranet.service.RoleService;
import com.misyn.smartintranet.service.TypeService;
import com.misyn.smartintranet.util.JsonResponse;
import com.misyn.smartintranet.util.PageList;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService es;
	@Autowired
	RoleService rservice;
	
	@Autowired
	Gson gson;
	
	@Autowired
	TypeService tservice;
	
	
	

	
	@RequestMapping(value="/getEmployeeForm", method=RequestMethod.GET)
	public @ResponseBody String addEmploee(){
		
		List<Tuple> roles=rservice.getAllRolesNameId();
		List<Tuple> types=tservice.getAllTypesNameId();
		String username=es.getNextEmployeeUsername();
		
		List<Object> rolesandtypes=new ArrayList<Object>();
		rolesandtypes.add(roles);
		rolesandtypes.add(types);
		rolesandtypes.add(username);
		
		String s=gson.toJson(rolesandtypes);
		return s;
	}
	
	
	
	@RequestMapping(value="/createEmployee", method=RequestMethod.GET)
	public @ResponseBody JsonResponse addEmploee1(@Valid @ModelAttribute("user")  Employee em,BindingResult result){
		
		JsonResponse js= new JsonResponse();
		if(result.hasErrors())
		{	
			
			js.setErrors(result);
			js.setSuccess(false);
			js.setStatus("Plese check and resubmit");
			
		}
		Employee e=es.getEmployeeByUsername(em.getUsername());
		if(e==null){
			
			es.saveEmployee(em);
			js.setStatus("Successfully added employee "+em.getUsername());
			js.setSuccess(true);
			
		}
		
		else{
			
			
			e.setAddress(em.getAddress());
			e.setFirst_name(em.getFirst_name());
			e.setLast_name(em.getLast_name());
			e.setEmail(em.getEmail());
			e.setContact_no(em.getContact_no());
			e.setEnable(em.isEnable());
			e.setRole(em.getRole());
			System.out.println(e.getType().getTypeID()+"=========="+em.getType().getTypeID());
			if(e.getType().getTypeID()==em.getType().getTypeID())
				es.updateEmployee(e);
			else{
				System.out.println("re cl update");
				es.changeTypeAndUpdate(e, em.getType());
			}
			js.setStatus("Successfully updated employee "+e.getUsername());
			js.setSuccess(true);
		}
		
		return js;

	}
	/*@RequestMapping(value="/createtech", method=RequestMethod.GET)
		public @ResponseBody JsonResponse addEmploeetc(@Valid @ModelAttribute("user")  TechnicalSupport ts,BindingResult result){
			JsonResponse js= new JsonResponse();
			if(result.hasErrors())
			{	
				
				return js.getesponse(false, result,"Plese check and resubmit");
			}
			TechnicalSupport e=(TechnicalSupport) es.getEmployeeByUsername(ts.getUsername());
			if(e==null){
				es.saveEmployee(ts);
				js.setStatus("Successfully added employee "+ts.getUsername());
				js.setSuccess(true);
				
			}
			
			else{
				
				e.setAddress(ts.getAddress());
				e.setFirst_name(ts.getFirst_name());
				e.setLast_name(ts.getLast_name());
				e.setType(ts.getType());
				e.setEmail(ts.getEmail());
				e.setContact_no(ts.getContact_no());
				e.setEnable(ts.isEnable());
				e.setSpecifications(ts.getSpecifications());
				
				es.updateEmployee(e);
				js.setStatus("Successfully updated employee "+ts.getUsername());
				js.setSuccess(true);
			}
			
			return js;
		
	
	
	}*/
	
	
	//@SuppressWarnings("unchecked")
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/loadEmployee/{page}", method=RequestMethod.GET)
	public @ResponseBody String laodEmployee(@PathVariable("page") String page,Model m,HttpServletRequest req){
		
		PageList pl=new PageList();
		System.out.println(page);
		
		PagedListHolder<Tuple> productList = null;
		String s=null;
		
		
		if("0".equals(page)){
			productList = new PagedListHolder<Tuple>();
			List<Tuple> emp=es.getAllEmployeeDetails("%");
			productList.setSource(emp);
			productList.setPageSize(10);
			req.getSession().setAttribute("elist", productList);
			pl.setSource(productList.getPageList());
			pl.setPages(productList.getPageCount());
			pl.setCurrent(productList.getPage()+1);
			pl.setFirstpage(productList.isFirstPage());
			pl.setLastpage(productList.isLastPage());	
		
        
		}
		
		else if("next".equals(page)){
			
			productList=(PagedListHolder<Tuple>) req.getSession().getAttribute("elist");
			productList.nextPage();
			pl.setSource(productList.getPageList());
			pl.setPages(productList.getPageCount());
			pl.setCurrent(productList.getPage()+1);
			pl.setFirstpage(productList.isFirstPage());
			pl.setLastpage(productList.isLastPage());
		}
		
		else if("prev".equals(page)){
			
			productList=(PagedListHolder<Tuple>) req.getSession().getAttribute("elist");
			productList.previousPage();
			pl.setSource(productList.getPageList());
			pl.setPages(productList.getPageCount());
			pl.setCurrent(productList.getPage()+1);
			pl.setFirstpage(productList.isFirstPage());
			pl.setLastpage(productList.isLastPage());
		}
		
		else{
				int i=Integer.parseInt(page);
				productList=(PagedListHolder<Tuple>) req.getSession().getAttribute("elist");
				productList.setPage(i-1);
				pl.setSource(productList.getPageList());
				pl.setPages(productList.getPageCount());
				pl.setCurrent(productList.getPage()+1);
				pl.setFirstpage(productList.isFirstPage());
				pl.setLastpage(productList.isLastPage());
			}
		s=gson.toJson(pl);
		System.out.println(s);
		return s;
		
       
		
	}
	
	
	@RequestMapping(value="/getEmployee/{username}", method=RequestMethod.POST)
	public @ResponseBody Employee getEmployee(@PathVariable("username") String username,Model m,HttpServletRequest req){
		
		System.out.println(username);
		Employee e=es.getEmployeeByUsername(username);
		
		
		
		return e;
		
       
		
	}
	
	@RequestMapping(value="/employeesearch/{username}", method=RequestMethod.POST)
	public @ResponseBody String searchEmployee(@PathVariable("username") String username){
		
		List<Tuple> source=es.getAllEmployeeDetails(username+"%");
		String reponse=gson.toJson(source);
		
		
		
		return reponse;
		
       
		
	}
	
	
	
	
		
	
	
	
	
}
