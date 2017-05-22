package com.misyn.smartintranet.controller;

import java.util.Date;
import java.util.List;

import javax.persistence.Tuple;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.misyn.smartintranet.entity.Client;
import com.misyn.smartintranet.entity.Employee;
import com.misyn.smartintranet.entity.Message;
import com.misyn.smartintranet.entity.Notification;
import com.misyn.smartintranet.entity.Question;
import com.misyn.smartintranet.entity.Reply;
import com.misyn.smartintranet.service.ClientService;
import com.misyn.smartintranet.service.EmployeeService;
import com.misyn.smartintranet.service.NotificationService;
import com.misyn.smartintranet.service.QuestionService;
import com.misyn.smartintranet.service.ReplyService;
import com.misyn.smartintranet.service.UserService;
import com.misyn.smartintranet.serviceImp.EmailService;
import com.misyn.smartintranet.util.PageList;
import com.misyn.smartintranet.util.Uploading;

@Controller
public class QuestionController {
	@Autowired
	SimpMessagingTemplate sm;
	
	@Autowired
	QuestionService qs;
	
	@Autowired
	UserService us;
	
	@Autowired
	EmailService mailservice;
	
	@Autowired
	ClientService cs;
	
	@Autowired
	EmployeeService es;
	
	@Autowired
	ReplyService rs;
	
	@Autowired
	Gson gson;
	
	@Autowired
	Uploading upload;
	
	@Autowired
	NotificationService notificationService;
	
	@RequestMapping(value="/askquestion", method=RequestMethod.POST)
	public String askQuestion(@Valid @ModelAttribute("question") Question question, BindingResult result,HttpServletRequest request){
		
		
		if(result.hasErrors()){
			return "newquestion";
		}
		
		
		else
		{
			
			List<MultipartFile> uploadings=question.getUploadings();
			if(!(uploadings.size()==1&&uploadings.get(0).getContentType().equals("application/octet-stream")))
				question.setAttachment(upload.uploadfile(question.getUploadings(), request));
			
			Client client=question.getClient();
			client.setId(us.getIDByUsername(client.getUsername()));
			
			if(question.getModule().getModuleID().equals("General"))
				question.setModule(null);
			if(question.getProject().getProjectID().equals("General"))
				question.setProject(null);
			qs.saveQuestion(question);
						
			Message message=new Message(1);
			List<String> usernames=us.getAllusernamesByroleName("gateway controller");
			for( String username:usernames)
				sm.convertAndSendToUser(username,"/queue/qnotification",message);

		
		
		}
		
		return "redirect:/success";
		
	}
	
	
	
	
	
	@RequestMapping(value="/success", method=RequestMethod.GET)
	public String sucessredirect(HttpServletRequest request, Model model){
		model.addAttribute("message", "Successfully post your question");
		model.addAttribute("question", new Question());
		
		return "newquestion";
		
	}
	
	
	@RequestMapping(value="/question", method=RequestMethod.GET)
	public String question(HttpServletRequest req,Model model){
		
		String username=(String) req.getSession().getAttribute("username");;
		List<Tuple> notifications=notificationService.getAllNotificationByStatusAndUsername("unread", username);
		
		model.addAttribute("notifications", notifications);
		 return "question";
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/loadQuestion/{page}", method=RequestMethod.GET)
	public @ResponseBody String loadQuestion(@PathVariable("page") String page,HttpServletRequest req){
	
		
		PageList pl=new PageList();
		
		
		PagedListHolder<Tuple> qList = null;
		String s=null;
		
		
		if("0".equals(page)){
			qList = new PagedListHolder<Tuple>();
			String role=(String) req.getSession().getAttribute("role");
			String username=(String) req.getSession().getAttribute("username");
			List<Tuple> questionlist;
			if(role.equals("client"))
				questionlist=qs.getAllQuestionDetaisByUser(username);
			else
				questionlist=qs.getAllQuestionDetails();
			qList.setSource(questionlist);
			qList.setPageSize(10);
			req.getSession().setAttribute("questionlist", qList);
			pl.setSource(qList.getPageList());
			pl.setPages(qList.getPageCount());
			pl.setCurrent(qList.getPage()+1);
			pl.setFirstpage(qList.isFirstPage());
			pl.setLastpage(qList.isLastPage());	
        
		}
		
		else if("next".equals(page)){
			
			qList=(PagedListHolder<Tuple>) req.getSession().getAttribute("questionlist");
			qList.nextPage();
			pl.setSource(qList.getPageList());
			pl.setPages(qList.getPageCount());
			pl.setCurrent(qList.getPage()+1);
			pl.setFirstpage(qList.isFirstPage());
			pl.setLastpage(qList.isLastPage());
		}
		
		else if("prev".equals(page)){
			
			qList=(PagedListHolder<Tuple>) req.getSession().getAttribute("questionlist");
			qList.previousPage();
			pl.setSource(qList.getPageList());
			pl.setPages(qList.getPageCount());
			pl.setCurrent(qList.getPage()+1);
			pl.setFirstpage(qList.isFirstPage());
			pl.setLastpage(qList.isLastPage());
		}
		
		else{
				int i=Integer.parseInt(page);
				qList=(PagedListHolder<Tuple>) req.getSession().getAttribute("questionlist");
				qList.setPage(i-1);
				pl.setSource(qList.getPageList());
				pl.setPages(qList.getPageCount());
				pl.setCurrent(qList.getPage()+1);
				pl.setFirstpage(qList.isFirstPage());
				pl.setLastpage(qList.isLastPage());
			}
		s=gson.toJson(pl);
		System.out.println(s);
		return s;

		
		
		
	}
	
	@RequestMapping(value="/viewquestion/{questionID}", method=RequestMethod.GET)
	public @ResponseBody String viewQuestion(@PathVariable("questionID") String questionID,HttpServletRequest req){
		String role=(String) req.getSession().getAttribute("role");
		System.out.println(questionID+"questionID");
		List<Object> result=qs.getAllQuestionandRepliesByquestionID(Long.parseLong(questionID),role);
		
		
		if(!role.equals("client"))
		{
			result.add(es.getEmployeesByRoleName("%"));
		}
		
		if(role.equals("gateway controller"))
		{
			result.add(es.getEmployeesByRoleName("tech supporter"));
		}
		
		String s=gson.toJson(result);
		
		return s;
		
		
		
	}
	
	@RequestMapping(value="/reply", method=RequestMethod.POST)
	public String getReply(@Valid @ModelAttribute("reply") Reply reply, BindingResult result,HttpServletRequest request){
		System.out.println(reply.getQuestion().getQuestionID()+"questionID");
		System.out.println(reply.getReplyDescription()+"questionID");
		if(result.hasErrors())
			System.out.println(result.getAllErrors().size());
		else{
			if(reply.getUploadings()!=null)
				reply.setAttachment(upload.uploadfile(reply.getUploadings(), request));
			reply.setHidden(false);
			rs.saveReply(reply);
			
		}
		
		
		String email=qs.getClientemailByQID(reply.getQuestion().getQuestionID());
		String role=(String) request.getSession().getAttribute("role");
		Question question=qs.getQuestion(reply.getQuestion().getQuestionID());
		if(role.equals("client")){
			
			if(question.getStatus().equals("inprogress")){
				Notification notification=new Notification();
				
				notification.setQuestion(question);
				notification.setTo(question.getTechnicalSuporter());
				notification.setFrom(reply.getUser());
				notificationService.saveNotification(notification);
				
				sm.convertAndSendToUser(notification.getTo().getUsername(),"/queue/notification",notification);
				
			
			}
			
			if(question.getStatus().equals("new")){
				Notification notification=new Notification();
				List<String> usernames=us.getAllusernamesByroleName("gateway controller");
				for( String username:usernames){
				notification.setQuestion(question);
				notification.setTo(us.getUserByUsername(username));
				notification.setFrom(reply.getUser());
				notificationService.saveNotification(notification);
				
				sm.convertAndSendToUser(notification.getTo().getUsername(),"/queue/notification",notification);
				}
			}
			return "redirect:/question";
			
		}
		else{
			try{
			mailservice.sendMail(email,"Upadate on your question","Upadate on your question");
			}
			catch(Exception ex)
			{
				System.out.println("connction fault");
			}
			finally{
			Notification notification=new Notification();
		
			notification.setQuestion(question);
			notification.setTo(question.getClient());
			notification.setFrom(reply.getUser());
			notificationService.saveNotification(notification);
			
			sm.convertAndSendToUser(notification.getTo().getUsername(),"/queue/notification",notification);
			
			}
			return "redirect:/question";
			
		}
		
		
		
		
		
		
		
	}
	
	
	
	
	@RequestMapping(value="/privatereply", method=RequestMethod.POST)
	public String getprivateReply(@Valid @ModelAttribute("reply") Reply reply, BindingResult result, @RequestParam("targetUsername") String to,HttpServletRequest request){
		
		if(result.hasErrors())
			System.out.println(result.getAllErrors().size());
		else{
			Question question=qs.getQuestion(reply.getQuestion().getQuestionID());
			System.out.println(to);
			if(reply.getUploadings()!=null)
				reply.setAttachment(upload.uploadfile(reply.getUploadings(), request));
			reply.setHidden(true);
			rs.saveReply(reply);
			
			Employee emp=es.getEmployeeByUsername(to);
			if(emp!=null){
			Notification notification=new Notification();
			
			notification.setQuestion(question);
			notification.setTo(emp);
			notification.setFrom(reply.getUser());
			notificationService.saveNotification(notification);
			
			sm.convertAndSendToUser(notification.getTo().getUsername(),"/queue/notification",notification);
			}
		}
		
		
		return "question";
		
		
	}
	
	@RequestMapping(value="/specialreply", method=RequestMethod.GET)
	public  String getSpecialReply(@ModelAttribute("reply") Reply reply){
		
			System.out.println(reply.getQuestion().getQuestionID());
			Question q=qs.getQuestion(reply.getQuestion().getQuestionID());
			q.setStatus(reply.getQuestion().getStatus());
			qs.updateQuestion(q);
			rs.saveReply(reply);
			
			return "question";
			
		
		
		
		
		
	}
	
	@RequestMapping(value="/assignquestion", method=RequestMethod.GET)
	public  String assignQuestion(@ModelAttribute("Question") Question question){
		
			
			Question q=qs.getQuestion(question.getQuestionID());
			q.setStatus(question.getStatus());
			q.setTechnicalSuporter(question.getTechnicalSuporter());
			q.setCategory(question.getCategory());
			qs.updateQuestion(q);
			
		
				Message message=new Message(1);
				String username=us.getUser(question.getTechnicalSuporter().getId()).getUsername();
				sm.convertAndSendToUser(username,"/queue/showResult",message);
				return "question";
			
			
			
		
		
		
		
		
	}
	
	@RequestMapping(value="/filterquestion", method=RequestMethod.GET)
	public @ResponseBody String filterQuestion(@RequestParam("query") String query,@RequestParam("selected") String parameter,HttpSession session){
		System.out.println(query+parameter);
		List<Tuple> questionlist=null;
		String uname=null;
		 String role=(String) session.getAttribute("role");
		if(role.equals("client"))
		{
			 uname=(String)session.getAttribute("username");
			 
			 
		}
		else
			uname="%";


		if(query.equals("username"))
			questionlist=qs.getAllQuestionDetailsByTS(parameter);
		else if(query.equals("category"))
			questionlist=qs.getAllQuestionDetailsBycategory(uname,parameter);
		else if(query.equals("status"))
			questionlist=qs.getAllQuestionDetailsByStatus(uname,parameter);
			

		String s=gson.toJson(questionlist);
		System.out.println(s);
		return s;




	}
	
	
	
	@RequestMapping(value="/loadQuestionforfilter", method=RequestMethod.GET)
	public @ResponseBody String loadQuestionforfilter(HttpServletRequest req){
		
		String username=(String) req.getSession().getAttribute("username");
		List<Tuple> questionlist;
		
		questionlist=qs.getAllQuestionDetaisByUser(username);
		
		
		
		String s=gson.toJson(questionlist);
		System.out.println(s);
		return s;

		
		
		
	}
	
	@RequestMapping(value="/rateandclose", method=RequestMethod.GET)
	public  String rateAndClose(@RequestParam("rating") int rating, @RequestParam("questionID") long qid){
		Question q=qs.getQuestion(qid);
		q.setRating(rating);
		q.setStatus("close");
		qs.updateQuestion(q);
		
		
		return "question";

		
		
		
	}
	
	
	@RequestMapping(value="/reopen", method=RequestMethod.GET)
	public  String reopen( @RequestParam("questionID") long qid){
		Question q=qs.getQuestion(qid);
		q.setDate(new Date());
		q.setStatus("new");
		qs.updateQuestion(q);
		
		
		return "question";

		
		
		
	}
	
	
	
}
