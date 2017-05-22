package com.misyn.smartintranet.controller;

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
import com.misyn.smartintranet.entity.Client;
import com.misyn.smartintranet.service.ClientService;
import com.misyn.smartintranet.service.RoleService;
import com.misyn.smartintranet.util.JsonResponse;
import com.misyn.smartintranet.util.PageList;

@Controller
public class ClientController {
	
	@Autowired
	ClientService clientService;
	
	@Autowired
	RoleService rs;
	
	@Autowired
	Gson gson;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/loadClient/{page}", method=RequestMethod.GET)
	public @ResponseBody String laodClient(@PathVariable("page") String page,Model m,HttpServletRequest req){
		Gson gson = new Gson();
		PageList pl=new PageList();
		System.out.println(page);
		
		PagedListHolder<Tuple> productList = null;
		String s=null;
		
		
		if("0".equals(page)){
			productList = new PagedListHolder<Tuple>();
			List<Tuple> clients=clientService.getAllClient("%");
			if(clients!=null){
			productList.setSource(clients);
			productList.setPageSize(10);
			req.getSession().setAttribute("clientlist", productList);
			pl.setSource(productList.getPageList());
			pl.setPages(productList.getPageCount());
			pl.setCurrent(productList.getPage()+1);
			pl.setFirstpage(productList.isFirstPage());
			pl.setLastpage(productList.isLastPage());	
			}
		}
		
		else if("next".equals(page)){
			
			productList=(PagedListHolder<Tuple>) req.getSession().getAttribute("clientlist");
			productList.nextPage();
			pl.setSource(productList.getPageList());
			pl.setPages(productList.getPageCount());
			pl.setCurrent(productList.getPage()+1);
			pl.setFirstpage(productList.isFirstPage());
			pl.setLastpage(productList.isLastPage());
		}
		
		else if("prev".equals(page)){
			
			productList=(PagedListHolder<Tuple>) req.getSession().getAttribute("clientlist");
			productList.previousPage();
			pl.setSource(productList.getPageList());
			pl.setPages(productList.getPageCount());
			pl.setCurrent(productList.getPage()+1);
			pl.setFirstpage(productList.isFirstPage());
			pl.setLastpage(productList.isLastPage());
		}
		
		else{
				int i=Integer.parseInt(page);
				productList=(PagedListHolder<Tuple>) req.getSession().getAttribute("clientlist");
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
	
	
	@RequestMapping(value="/createClient", method=RequestMethod.GET)
	public @ResponseBody JsonResponse addEmploee1(@Valid @ModelAttribute("client")  Client client,BindingResult result){
		JsonResponse js= new JsonResponse();
		if(result.hasErrors())
		{	
			
			js.setErrors(result);
			js.setSuccess(false);
			js.setStatus("Plese check and resubmit");
		}
		else
		{
			Client eclient=clientService.getClientByUsername(client.getUsername());
			if(eclient==null){
				clientService.saveClient(client);
				js.setStatus("Successfully added client "+client.getUsername());
				js.setSuccess(true);
				
			}
			
			else{
				
			
				eclient.setAddress(client.getAddress());
				eclient.setCompanyName(client.getCompanyName());
				eclient.setResponsible_person(client.getResponsible_person());
				eclient.setEmail(client.getEmail());
				eclient.setContact_no(client.getContact_no());
				eclient.setEnable(client.isEnable());
				
				clientService.updateClient(eclient);
				js.setStatus("Successfully added client "+eclient.getUsername());
				js.setSuccess(true);
			}
			
			
		
		}
		return js;
	
	
	}
	
	@RequestMapping(value="/getclient/{username}", method=RequestMethod.POST)
	public @ResponseBody Client client(@PathVariable("username") String username){
		
		System.out.println(username);
		Client e=clientService.getClientByUsername(username);
		
		
		
		return e;
		
       
		
	}
	
	@RequestMapping(value="/getClientForm", method=RequestMethod.GET)
	public @ResponseBody String addEmploee(){
		
		
		String username=clientService.getNextClientUsername();
		
		
		
		String s=gson.toJson(username);
		return s;
	}
	

	@RequestMapping(value="/searchClient/{username}", method=RequestMethod.POST)
	public @ResponseBody String searchClient(@PathVariable("username") String username){
		
		
		List<Tuple> source=clientService.getAllClient(username+"%");
		String response=gson.toJson(source);
		
		
		return response;
		
       
		
	}

}
