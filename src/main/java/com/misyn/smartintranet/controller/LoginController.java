package com.misyn.smartintranet.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.misyn.smartintranet.entity.User;
import com.misyn.smartintranet.service.QuestionService;
import com.misyn.smartintranet.service.UserService;


@Controller
public class LoginController {
	
	@Autowired
	UserService uservice;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	QuestionService qservice;
	
	@RequestMapping(value = "/login" )
	public String login(){
			
	return "login";
	}
	
	
	@RequestMapping(value = "/login/{error}" )
	public String login(@PathVariable("error") String error, Model model){
	String message=null;
	if(error.equals("nouser"))
		message="Invalid username";
	else if(error.equals("disabled"))
		message="Account is disable";
	else
		message="Wrong password";
		
	model.addAttribute("error", message);
	return "login";
	}
	
	
	

	
	
	
	@RequestMapping(value={"/","home"}, method=RequestMethod.GET)
	public String home(HttpServletRequest request,Model model){
		int id=(int) request.getSession().getAttribute("userId");
		String role=(String) request.getSession().getAttribute("role");
		if(id==0)
			return "home";
		else{
		User u=uservice.getUser(id);
		model.addAttribute("user", u);
		int newq;
		int close;
		int inprogress;
		int furtherimplements;
		
		if(role.equals("tech supporter")){
			 newq=qservice.getNoOFquestionsbyuserIDandstatus("new", id);
			 close=qservice.getNoOFquestionsbyuserIDandstatus("close", id);
			 inprogress=qservice.getNoOFquestionsbyuserIDandstatus("inprogress", id);
			 furtherimplements=qservice.getNoOFquestionsbyuserIDandstatus("further implementation", id);
		}
		
		else if(role.equals("client")){
			
			newq=qservice.getNoOFquestionsbyclientIDandstatus("new", id);
			close=qservice.getNoOFquestionsbyclientIDandstatus("close", id);
			inprogress=qservice.getNoOFquestionsbyclientIDandstatus("inprogress", id);
			furtherimplements=qservice.getNoOFquestionsbyclientIDandstatus("further implementation", id);
			
		}
		
		else{
			
			 newq=qservice.getNoOFquestionsstatus("new");
			 close=qservice.getNoOFquestionsstatus("close");
			 inprogress=qservice.getNoOFquestionsstatus("inprogress");
			 furtherimplements=qservice.getNoOFquestionsstatus("further implementation");
			
		}
		
		model.addAttribute("newq", newq);
		model.addAttribute("close", close);
		model.addAttribute("inprogress", inprogress);
		model.addAttribute("furtherimplements", furtherimplements);
		
		return "home";
		}
	}
	
	
	
	
	@RequestMapping(value="/firstlogin", method=RequestMethod.GET)
	public String firstlogin(){
		
		
		return "firstlogin";
	}
	
	
	@RequestMapping(value="/passwordchange", method=RequestMethod.POST )
	public String passwordChange(@ModelAttribute("user") User user,HttpServletRequest request,Model model){
		boolean r=false;
		if(user.getPassword().length()<6)
		{
			model.addAttribute("message", "Password is too short, need more than 6 characters");
			return "firstlogin";
		}
		else
		{
			int id=(int) request.getSession().getAttribute("userId");
			
				if(user.getId()==id)
				{
					User existinguser=uservice.getUser(id);
					existinguser.setPassword(passwordEncoder.encode(user.getPassword()));
					r=uservice.updateUser(existinguser);
					
					if(r)
						return "redirect:home";
					else
						return "firstlogin";
					
				}
				
				else
					return "firstlogin";
		
		
		}
		
		
	}
	
	
}
