package com.misyn.smartintranet.controller;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.misyn.smartintranet.entity.Project;
import com.misyn.smartintranet.entity.Question;
import com.misyn.smartintranet.entity.User;
import com.misyn.smartintranet.entity.Uuu;
import com.misyn.smartintranet.service.ProjectService;
import com.misyn.smartintranet.service.QuestionService;
import com.misyn.smartintranet.service.UserService;
import com.misyn.smartintranet.serviceImp.EmailService;
import com.misyn.smartintranet.util.JsonResponse;


@Controller
public class AppController {
	
	@Autowired
	EmailService em;
	@Autowired
	SimpMessagingTemplate sm;
	@Autowired
	ProjectService ps;
	
	@Autowired
	UserService uservice;
	
	@Autowired
	Gson gson;
	
	@Autowired
	QuestionService qs;
	
	
	@Autowired
	Gson gs;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@RequestMapping(value="/maintenance", method=RequestMethod.GET)
	public String maintenance(){
		
		return "maintenance";
		
	}
	
	
	
	
	@RequestMapping(value="/askquestion", method=RequestMethod.GET)
	public String askNewQuestion(HttpServletRequest request, Model model){
		String role=(String) request.getSession().getAttribute("role");
		model.addAttribute("question", new Question());
		
		if(role.equals("client"))
			return "suggestion";
		else
			return "newquestion";
		
	}
	
	@RequestMapping(value="/project", method=RequestMethod.GET)
	public String viewprojectdetails(HttpServletRequest request, Model model){
		String role=(String) request.getSession().getAttribute("role");
		String username=(String) request.getSession().getAttribute("username");
		List<Project> projectlist=null;
		if(role.equals("client"))
		{
			projectlist=ps.getAllProjectsByUsername(username);
			model.addAttribute("projectlist", projectlist);
			
		}
		else
		{
			projectlist=ps.getAllProjects();
			model.addAttribute("projectlist", projectlist);
			
		}
		
		return "project";

	}
	
	@RequestMapping(value="/skip", method=RequestMethod.GET)
	public String skipandaskne( Model model){
		
		model.addAttribute("question", new Question());
		
			return "newquestion";
		
		
	}
	
	@RequestMapping(value="/details", method=RequestMethod.GET)
	public String detailsPage( ){
		
		
		
			return "details";
		
		
	}
	
	@RequestMapping(value="/imageupload", method=RequestMethod.POST)
	public @ResponseBody String uploadImage(@RequestParam("id") int id,@RequestParam("file") MultipartFile file,HttpServletRequest req) {
		System.out.println("----------------------------------------------------");
		System.out.println(id);
		String location=("C:/mytemp/");
		User user=uservice.getUser(id);
		System.out.println(file.getOriginalFilename());
		String profilepic=user.getProfilepicture();
		
		
		if(profilepic.equals("default.png")){
			
			try {
				FileCopyUtils.copy(file.getBytes(), new File( location+user.getUsername()+"proilepic."+FilenameUtils.getExtension(file.getOriginalFilename())));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			user.setProfilepicture(user.getUsername()+"proilepic."+FilenameUtils.getExtension(file.getOriginalFilename()));
		}
		else
		{
			File existfile=new File(location+user.getProfilepicture());
			File newfile=new File(location+user.getUsername()+"proilepic."+FilenameUtils.getExtension(file.getOriginalFilename()));
			try {
				FileCopyUtils.copy(file.getBytes(),newfile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!existfile.equals(newfile))
				existfile.delete();
			System.out.println(newfile.getName());
			user.setProfilepicture(newfile.getName());
		}
			
		uservice.updateUser(user);
		
		
		String result= gson.toJson(user.getProfilepicture());
		return result;
		
       
		
	}
	
	
	@RequestMapping(value="/editprofile", method=RequestMethod.POST)
	public @ResponseBody JsonResponse editprofile(@Valid @ModelAttribute("user") User user, BindingResult result){
		JsonResponse js= new JsonResponse();
		if(result.hasErrors())
		{	
			js.setErrors(result);
			js.setSuccess(false);
			js.setStatus("Plese check and resubmit");
			
		}
		else
		{
			User existuser=uservice.getUser(user.getId());
			existuser.setEmail(user.getEmail());
			existuser.setContact_no(user.getContact_no());
			existuser.setAddress(user.getAddress());
			boolean res=uservice.updateUser(existuser);
			if(res){
				js.setSuccess(true);
				js.setStatus("successfully updated");
			}
			else
			{
				js.setSuccess(false);
				js.setStatus("updating failed");
			}
			
		
		}
		return js;
	}
	
	@RequestMapping(value="/changepassword", method=RequestMethod.POST)
	public @ResponseBody JsonResponse editprofile(@RequestParam("id") int id,@RequestParam("currentpassword") String currentpassword,@RequestParam("newpassword") String newpassword){
		JsonResponse js= new JsonResponse();
		if(newpassword.length()<6)
		{
			js.setStatus("password too short, please use 6 or more characters");
		}
		else
		{
		User user=uservice.getUser(id);
		
		if(passwordEncoder.matches(currentpassword, user.getPassword())){
			user.setPassword(passwordEncoder.encode(newpassword));
			uservice.updateUser(user);
			js.setStatus("successfully changed password");
		}
		else
			js.setStatus("current password is wrong, please check and resubmit");
			
		
		}
		return js;
	}

	
	@RequestMapping(value="/forgetpassword/page", method=RequestMethod.GET)
	public String forgetPassword(){
		
		return "forgetpassword";
		
	}
	
	@RequestMapping(value="/forgetpassword/reset", method=RequestMethod.GET)
	public String getResetusername(@RequestParam("username") String username,Model model, HttpServletRequest req){
		
		User user=uservice.getUserByUsername(username);
		if(user==null)
		{
			model.addAttribute("message", "username not found in our database, please recheck or contact us");
		}
		else
		{
			String link=req.getRequestURL()+"/"+username;
			
			
			try{
				em.sendPasswordResetMail(user.getEmail(), link);
			}
			catch(Exception ex)
			{
				System.out.println("connction fault");
			}
		}
		
		return "forgetpassword";
		
	}
	
	
	@RequestMapping(value="/forgetpassword/reset/{username}", method=RequestMethod.GET)
	public String showResetpage(@PathVariable("username") String username,Model model, HttpServletRequest req){
		
		
		model.addAttribute("username", username);
		return "resetpassword";
		
	}
	
	@RequestMapping(value="/forgetpassword/resetpassword", method=RequestMethod.POST)
	public String reset(@RequestParam("username") String username,@RequestParam("email") String email,@RequestParam("newpassword") String newpassword, RedirectAttributes model){
		if(newpassword.length()<6)
		{
			model.addFlashAttribute("message", "password too short, please use 6 or more characters");
			model.addFlashAttribute("username", username);
			
		}
		else
		{
			User user=uservice.getUserByUsername(username);
			
			if(email.equals(user.getEmail())){
				user.setPassword(passwordEncoder.encode(newpassword));
				uservice.updateUser(user);
				return "redirect:/home";
			}
			else{
				model.addFlashAttribute("message", "incorrect email address, enter correct email and retry");
				model.addFlashAttribute("username", username);
			}
				
			
		
		}
		

		
		
		return "redirect:/forgetpassword/fail";
		
	}
	
	@RequestMapping(value="/forgetpassword/fail", method=RequestMethod.GET)
	public String resetfail(RedirectAttributes model ){
		@SuppressWarnings("unchecked")
		Map<String,String> map=(Map<String, String>) model.getFlashAttributes();
		model.addAllAttributes(map);
		
		
		return "resetpassword";
		
	}

	
	@RequestMapping(value="/restcontroller", method=RequestMethod.POST)
	public @ResponseBody String restcon(@RequestBody Uuu u,HttpServletRequest req){
		System.out.println("xxxx");
		//System.out.println(user.getUsername()+" "+user.getEmail());
		//String name=user.getEmail();
		System.out.println(req.getContentLength());
		System.out.println(req.getContentType());
		//System.out.println(req.getCharacterEncoding());
		
		
		
		String name=u.getUsername();
		//u.getUsername();
		return gson.toJson(name);
		
	}


}
